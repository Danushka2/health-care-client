package lk.elevenzcode.healthcare.paymentapi.service.impl;

import com.stripe.model.PaymentIntent;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.commons.util.Constant;
import lk.elevenzcode.healthcare.commons.util.ConversionUtil;
import lk.elevenzcode.healthcare.commons.util.RandomIdUtil;
import lk.elevenzcode.healthcare.paymentapi.domain.Payment;
import lk.elevenzcode.healthcare.paymentapi.domain.PaymentStatus;
import lk.elevenzcode.healthcare.paymentapi.domain.RefundPayment;
import lk.elevenzcode.healthcare.paymentapi.repository.PaymentRepository;
import lk.elevenzcode.healthcare.paymentapi.service.PaymentService;
import lk.elevenzcode.healthcare.paymentapi.service.RefundPaymentService;
import lk.elevenzcode.healthcare.paymentapi.service.integration.AppointmentIntegrationService;
import lk.elevenzcode.healthcare.paymentapi.service.integration.StripeIntegrationService;
import lk.elevenzcode.healthcare.paymentapi.service.integration.dto.AppointmentInfo;
import lk.elevenzcode.healthcare.paymentapi.service.integration.dto.AppointmentUpdateReq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:35 PM
 */
@Service
public class PaymentServiceImpl extends GenericServiceImpl<Payment> implements PaymentService {
  private static final String INTENT_STATUS_SUCCEEDED = "succeeded";
  @Autowired
  private PaymentRepository paymentRepository;

  @Autowired
  private AppointmentIntegrationService appointmentIntegrationService;

  @Autowired
  private StripeIntegrationService stripeIntegrationService;

  @Autowired
  private RefundPaymentService refundPaymentService;

  @PostConstruct
  void init() {
    init(paymentRepository);
  }

  private void refund(Payment payment, String reason) throws ServiceException {
    if (payment != null) {
      if (payment.getStatus().getId().intValue() == PaymentStatus.STATUS_SUCCESS) {
        final String refundRef = stripeIntegrationService.refundPayment(payment
            .getStripeIntentId());
        payment.setStatus(new PaymentStatus(PaymentStatus.STATUS_REFUND));
        update(payment);
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

  @Override
  public Payment getByAppointmentId(int apptId) throws ServiceException {
    return paymentRepository.findByAppointmentId(apptId);
  }

  @Override
  @Transactional(value = Constant.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
      rollbackFor = ServiceException.class)
  public int save(int apptId, String paymentIntentId) throws ServiceException {
    final AppointmentInfo appointmentInfo = appointmentIntegrationService.getByApptId(apptId);
    if (appointmentInfo != null) {
      final PaymentIntent paymentIntent = stripeIntegrationService.getById(paymentIntentId);
      if (paymentIntent != null && StringUtils.equals(INTENT_STATUS_SUCCEEDED,
          paymentIntent.getStatus())) {
        final Payment payment = new Payment();
        payment.setAppointmentId(apptId);
        payment.setAmount(ConversionUtil.parseMoney(paymentIntent.getAmount()));
        payment.setReference(RandomIdUtil.getReference());
        payment.setStripeIntentId(paymentIntentId);
        payment.setStatus(new PaymentStatus(PaymentStatus.STATUS_SUCCESS));
        insert(payment);

        appointmentIntegrationService.updateStatus(apptId, new AppointmentUpdateReq(AppointmentInfo
            .Status.COMPLETED.getId()));
        return payment.getId();
      } else {
        throw new ServiceException(ServiceException.VALIDATION_FAILURE, "label.payment.err.failed");
      }
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE,
          "label.payment.err.appointment.not.available");
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
    refund(get(id), reason);
  }
}
