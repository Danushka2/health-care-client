package lk.elevenzcode.healthcare.paymentapi.repository;

import lk.elevenzcode.healthcare.commons.repository.GenericRepository;
import lk.elevenzcode.healthcare.paymentapi.domain.RefundPayment;

/**
 * Created by හShaන් සNදීප on 4/16/2020 8:34 PM
 */
public interface RefundPaymentRepository extends GenericRepository<RefundPayment> {
  RefundPayment findFirstByPaymentIdIs(int payId);
}
