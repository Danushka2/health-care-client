package lk.elevenzcode.healthcare.paymentapi.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.GenericService;
import lk.elevenzcode.healthcare.paymentapi.domain.Payment;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:35 PM
 */
public interface PaymentService extends GenericService<Payment> {
  Payment getByAppointmentId(int apptId) throws ServiceException;
}
