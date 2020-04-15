package lk.elevenzcode.healthcare.paymentapi.service.integration;

import com.stripe.model.PaymentIntent;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;

/**
 * Created by හShaන් සNදීප on 4/15/2020 4:12 PM
 */
public interface StripeIntegrationService {
  PaymentIntent initPayment(String description, long amount) throws ServiceException;

  PaymentIntent getById(String id) throws ServiceException;

  String refundPayment(String id) throws ServiceException;
}
