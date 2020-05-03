package lk.elevenzcode.healthcare.paymentapi.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.GenericService;
import lk.elevenzcode.healthcare.paymentapi.domain.RefundPayment;

/**
 * Created by හShaන් සNදීප on 4/16/2020 8:32 PM
 */
public interface RefundPaymentService extends GenericService<RefundPayment> {
  RefundPayment getByPaymentId(int payId) throws ServiceException;
}
