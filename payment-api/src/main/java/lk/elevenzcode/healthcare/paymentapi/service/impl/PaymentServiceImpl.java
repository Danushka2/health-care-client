package lk.elevenzcode.healthcare.paymentapi.service.impl;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.paymentapi.domain.Payment;
import lk.elevenzcode.healthcare.paymentapi.repository.PaymentRepository;
import lk.elevenzcode.healthcare.paymentapi.service.PaymentService;
import lk.elevenzcode.healthcare.paymentapi.service.integration.AppointmentIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:35 PM
 */
@Service
public class PaymentServiceImpl extends GenericServiceImpl<Payment> implements PaymentService {
  @Autowired
  private PaymentRepository paymentRepository;

  @Autowired
  private AppointmentIntegrationService appointmentIntegrationService;

  @PostConstruct
  void init() {
    init(paymentRepository);
  }

  @Override
  public Payment getByAppointmentId(int apptId) throws ServiceException {
    if (apptId == 1) {
      return new Payment(1, new BigDecimal(1500.00), LocalDateTime.now());
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE,
          "label.payment.err.not.found");
    }
  }
}
