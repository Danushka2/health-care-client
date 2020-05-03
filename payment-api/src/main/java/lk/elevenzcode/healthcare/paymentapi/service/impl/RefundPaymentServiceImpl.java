package lk.elevenzcode.healthcare.paymentapi.service.impl;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.paymentapi.domain.RefundPayment;
import lk.elevenzcode.healthcare.paymentapi.repository.RefundPaymentRepository;
import lk.elevenzcode.healthcare.paymentapi.service.RefundPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by හShaන් සNදීප on 4/16/2020 8:33 PM
 */
@Service
public class RefundPaymentServiceImpl extends GenericServiceImpl<RefundPayment> implements RefundPaymentService {
  private static final Logger LOGGER = LoggerFactory.getLogger(RefundPaymentServiceImpl.class);

  @Autowired
  private RefundPaymentRepository refundPaymentRepository;

  @PostConstruct
  private void init() {
    init(refundPaymentRepository);
  }

  @Override
  public RefundPayment getByPaymentId(int payId) throws ServiceException {
    try {
      return refundPaymentRepository.findFirstByPaymentIdIs(payId);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE, e.getMessage(), e.getCause());
    }
  }
}
