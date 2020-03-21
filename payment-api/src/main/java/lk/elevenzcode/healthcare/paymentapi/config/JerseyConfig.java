package lk.elevenzcode.healthcare.paymentapi.config;

import lk.elevenzcode.healthcare.paymentapi.web.service.PaymentRestService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Created by හShaන් සNදීප on 3/12/2020 1:07 AM
 */
@Component
public class JerseyConfig extends ResourceConfig {
  public JerseyConfig() {
    register(PaymentRestService.class);
  }
}
