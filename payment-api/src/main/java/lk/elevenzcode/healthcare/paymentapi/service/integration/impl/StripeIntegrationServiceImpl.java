package lk.elevenzcode.healthcare.paymentapi.service.integration.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.RefundCreateParams;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.paymentapi.service.integration.StripeIntegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by හShaන් සNදීප on 4/15/2020 4:12 PM
 */
@Service
public class StripeIntegrationServiceImpl implements StripeIntegrationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(StripeIntegrationServiceImpl.class);

  @Value("${stripe.api.secret.key}")
  private String apiKey;

  @Value("${default.payment.currency}")
  private String currency;

  @PostConstruct
  private void init() {
    Stripe.apiKey = apiKey;
  }

  @Override
  public PaymentIntent initPayment(String description, long amount) throws ServiceException {
    final PaymentIntentCreateParams createParams = PaymentIntentCreateParams.builder()
        .setCurrency(currency)
        .setAmount(amount)
        .setDescription(description)
        // Verify your integration in this guide by including this parameter
        .putMetadata("integration_check", "accept_a_payment")
        .build();

    try {
      return PaymentIntent.create(createParams);
    } catch (StripeException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE,
          "label.err.stripe.integration.failed");
    }
  }

  @Override
  public PaymentIntent getById(String id) throws ServiceException {
    try {
      return PaymentIntent.retrieve(id);
    } catch (StripeException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE,
          "label.err.stripe.integration.failed");
    }
  }

  @Override
  public String refundPayment(String id) throws ServiceException {
    final RefundCreateParams createParams = RefundCreateParams.builder()
        .setPaymentIntent(id)
        .build();
    try {
      return Refund.create(createParams).getId();
    } catch (StripeException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE,
          "label.err.stripe.integration.failed");
    }
  }
}
