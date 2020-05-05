package lk.elevenzcode.healthcare.paymentapi.service.impl;

import com.stripe.model.PaymentIntent;
import lk.elevenzcode.healthcare.commons.dto.ResultAdditionalData;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.commons.util.Constant;
import lk.elevenzcode.healthcare.commons.util.ConversionUtil;
import lk.elevenzcode.healthcare.commons.util.DateUtil;
import lk.elevenzcode.healthcare.commons.util.RandomIdUtil;
import lk.elevenzcode.healthcare.paymentapi.domain.Payment;
import lk.elevenzcode.healthcare.paymentapi.domain.PaymentStatus;
import lk.elevenzcode.healthcare.paymentapi.domain.RefundPayment;
import lk.elevenzcode.healthcare.paymentapi.repository.PaymentRepository;
import lk.elevenzcode.healthcare.paymentapi.service.PaymentMailService;
import lk.elevenzcode.healthcare.paymentapi.service.PaymentService;
import lk.elevenzcode.healthcare.paymentapi.service.RefundPaymentService;
import lk.elevenzcode.healthcare.paymentapi.service.integration.AppointmentIntegrationService;
import lk.elevenzcode.healthcare.paymentapi.service.integration.StripeIntegrationService;
import lk.elevenzcode.healthcare.paymentapi.service.integration.dto.AppointmentInfo;
import lk.elevenzcode.healthcare.paymentapi.service.integration.dto.AppointmentUpdateReq;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:35 PM
 */
@Service
public class PaymentServiceImpl extends GenericServiceImpl<Payment> implements PaymentService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);
  private static final String INTENT_STATUS_SUCCEEDED = "succeeded";

  @Autowired
  private PaymentRepository paymentRepository;

  @Autowired
  private AppointmentIntegrationService appointmentIntegrationService;

  @Autowired
  private StripeIntegrationService stripeIntegrationService;

  @Autowired
  private RefundPaymentService refundPaymentService;

  @Autowired
  private PaymentMailService paymentMailService;

  @PostConstruct
  void init() {
    init(paymentRepository);
  }

  private void refund(Payment payment, String reason) throws ServiceException {
    if (payment != null) {
      //validate whether payment has success or not
      if (payment.getStatus().getId().intValue() == PaymentStatus.STATUS_SUCCESS) {
        //refund the payment by Stripe
        final String refundRef = stripeIntegrationService.refundPayment(payment
            .getStripeIntentId());
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("Payment {} refunded..", payment.getStripeIntentId());
        }
        //make payment as refunded
        payment.setStatus(new PaymentStatus(PaymentStatus.STATUS_REFUND));
        update(payment);
        //save addition refund details
        final RefundPayment refundPayment = new RefundPayment();
        refundPayment.setPayment(payment);
        refundPayment.setRefundRef(refundRef);
        refundPayment.setReason(reason);
        refundPaymentService.insert(refundPayment);
      }
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE,
          "label.payment.err.not.found");
    }
  }

  private void sendAcknowledgementEmail(Payment payment, AppointmentInfo appointmentInfo) throws ServiceException {
    final String subject = getMessage("label.payment.success.email.subject");
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("subject:{}", subject);
    }
    //set appointment details
    final Map<String, Object> parameters = new HashMap<>();
    parameters.put("ref", payment.getReference());
    parameters.put("apptDate", DateUtil.formatDate(appointmentInfo.getAppointmentDate()));
    parameters.put("apptTime", DateUtil.formatTime(appointmentInfo.getSession().getFrom()));
    parameters.put("docFee",
        ConversionUtil.getMoneyWithThousandSeparator(appointmentInfo.getSession().getDocFee()));
    parameters.put("hospFee", ConversionUtil.getMoneyWithThousandSeparator(appointmentInfo
        .getSession().getRoom().getRoomFee()));
    parameters.put("totFee", ConversionUtil.getMoneyWithThousandSeparator(payment.getAmount()));
    parameters.put("ptName", appointmentInfo.getPatient().getName());
    parameters.put("ptAge", String.valueOf(appointmentInfo.getPatient().getAge()));
    parameters.put("docName", appointmentInfo.getSession().getDoctor().getName());
    parameters.put("docSpec", appointmentInfo.getSession().getDoctor().getSpecialization());
    parameters.put("hospName", appointmentInfo.getSession().getRoom().getHospital().getName());
    parameters.put("hospAddress",
        appointmentInfo.getSession().getRoom().getHospital().getAddress());
    parameters.put("hospContactNo", appointmentInfo.getSession().getRoom().getHospital().getTel());

    //send email to patient email
    paymentMailService.send(appointmentInfo.getPatient().getEmail(), subject,
        "appointment-success", parameters);
  }

  @Override
  public Payment getByAppointmentId(int apptId) throws ServiceException {
    return paymentRepository.findByAppointmentId(apptId);
  }

  @Override
  @Transactional(value = Constant.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
      rollbackFor = ServiceException.class)
  public int save(int apptId, String paymentIntentId) throws ServiceException {
    try {
      final AppointmentInfo appointmentInfo = appointmentIntegrationService.getByApptId(apptId);
      //validate appoinment is exist
      if (appointmentInfo != null) {
        //fetch & validate whether payment success or not by integrating with Stripe
        final PaymentIntent paymentIntent = stripeIntegrationService.getById(paymentIntentId);
        if (paymentIntent != null && StringUtils.equals(INTENT_STATUS_SUCCEEDED,
            paymentIntent.getStatus())) {

          //save payment details
          final Payment payment = new Payment();
          payment.setAppointmentId(apptId);
          payment.setAmount(ConversionUtil.parseMoney(paymentIntent.getAmount()));
          payment.setReference(RandomIdUtil.getReference());
          payment.setStripeIntentId(paymentIntentId);
          payment.setStatus(new PaymentStatus(PaymentStatus.STATUS_SUCCESS));
          insert(payment);

          //as of the payment success, update the appointment status from PENDING to COMPLETED
          appointmentIntegrationService.updateStatus(apptId,
              new AppointmentUpdateReq(AppointmentInfo
                  .Status.COMPLETED.getId()));

          //send acknowlegement email to the patient
          sendAcknowledgementEmail(payment, appointmentInfo);
          return payment.getId();
        } else {
          throw new ServiceException(ServiceException.VALIDATION_FAILURE,
              "label.payment.err.failed");
        }
      } else {
        throw new ServiceException(ServiceException.VALIDATION_FAILURE,
            "label.payment.err.appointment.not.available");
      }
    } catch (ServiceException e) {
      //refund payment if occurs any error while saving or sending email
      stripeIntegrationService.refundPayment(paymentIntentId);
      throw e;
    }
  }

  @Override
  @Transactional(value = Constant.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
      rollbackFor = ServiceException.class)
  public void refundByAppt(int apptId, String reason) throws ServiceException {
    refund(getByAppointmentId(apptId), reason);
  }

  @Override
  @Transactional(value = Constant.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
      rollbackFor = ServiceException.class)
  public void refund(int id, String reason) throws ServiceException {
    final Payment payment = get(id);
    refund(payment, reason);

    //as of the payment refund, update the appointment status to CANCELED
    appointmentIntegrationService.updateStatus(payment.getAppointmentId(),
        new AppointmentUpdateReq(AppointmentInfo.Status.CANCELED.getId()));
  }

  @Override
  public List<Payment> getList(Integer offset, Integer limit, String sort, String order,
                               ResultAdditionalData additionalData, String searchCriteria) throws ServiceException {
    Pageable pageable = PageRequest.of(offset, limit, Sort.by(Sort.Direction.fromString(order),
        sort));
    try {
      if (StringUtils.isNotEmpty(searchCriteria)) {
        additionalData.setCount(paymentRepository
            .countAllByStatus_NameContainingIgnoreCaseOrReferenceContainingIgnoreCase(searchCriteria,
                searchCriteria));
        return paymentRepository
            .getAllByStatus_NameContainingIgnoreCaseOrReferenceContainingIgnoreCase(searchCriteria,
                searchCriteria, pageable);
      } else {
        additionalData.setCount(paymentRepository.count());
        return paymentRepository.findAll(pageable).toList();
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE, e.getMessage(), e.getCause());
    }
  }

  @Override
  public void update(int id, String paymentIntentId) throws ServiceException {
    final Payment payment = get(id);
    try {
      if (payment != null) {
        //fetch & validate whether payment success or not by integrating with Stripe
        final PaymentIntent paymentIntent = stripeIntegrationService.getById(paymentIntentId);
        if (paymentIntent != null && StringUtils.equals(INTENT_STATUS_SUCCEEDED,
            paymentIntent.getStatus())) {
          //refund existing payment
          stripeIntegrationService.refundPayment(payment.getStripeIntentId());
          if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Payment {} refunded..", payment.getStripeIntentId());
          }
          //update payment fields
          payment.setAmount(ConversionUtil.parseMoney(paymentIntent.getAmount()));
          payment.setStripeIntentId(paymentIntentId);
          payment.setPaidOn(LocalDateTime.now());
          update(payment);

          final AppointmentInfo appointmentInfo = appointmentIntegrationService.getByApptId(payment
              .getAppointmentId());

          //send acknowlegement email to the patient
          sendAcknowledgementEmail(payment, appointmentInfo);
        } else {
          throw new ServiceException(ServiceException.VALIDATION_FAILURE,
              "label.payment.err.failed");
        }
      } else {
        throw new ServiceException(ServiceException.VALIDATION_FAILURE,
            "label.payment.err.not.found");
      }
    } catch (ServiceException e) {
      //refund payment if occurs any error while saving or sending email
      stripeIntegrationService.refundPayment(paymentIntentId);
      throw e;
    }
  }
}
