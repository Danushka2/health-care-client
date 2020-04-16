package lk.elevenzcode.healthcare.paymentapi.service.impl;

import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.paymentapi.domain.RefundPayment;
import lk.elevenzcode.healthcare.paymentapi.repository.RefundPaymentRepository;
import lk.elevenzcode.healthcare.paymentapi.service.RefundPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by හShaන් සNදීප on 4/16/2020 8:33 PM
 */
@Service
public class RefundPaymentServiceImpl extends GenericServiceImpl<RefundPayment> implements RefundPaymentService {
  @Autowired
  private RefundPaymentRepository refundPaymentRepository;

  @PostConstruct
  private void init() {
    init(refundPaymentRepository);
  }
}
