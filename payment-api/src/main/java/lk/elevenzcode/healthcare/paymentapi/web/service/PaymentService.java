package lk.elevenzcode.healthcare.paymentapi.web.service;

import lk.elevenzcode.healthcare.commons.web.service.BaseRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by හShaන් සNදීප on 3/9/2020 2:46 PM
 */
@Component
@Path(Constant.API_VER + "/" + Constant.API_PATH)
public class PaymentService extends BaseRestService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

  @GET
  @Produces(value = MediaType.TEXT_PLAIN)
  public String test() {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@test...");
    }
    return "Payment API is online";
  }
}
