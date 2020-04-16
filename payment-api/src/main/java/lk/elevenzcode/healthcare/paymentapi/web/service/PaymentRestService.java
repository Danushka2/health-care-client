package lk.elevenzcode.healthcare.paymentapi.web.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.web.service.BaseRestService;
import lk.elevenzcode.healthcare.commons.web.util.RESTfulUtil;
import lk.elevenzcode.healthcare.paymentapi.service.PaymentService;
import lk.elevenzcode.healthcare.paymentapi.web.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by හShaන් සNදීප on 3/9/2020 2:46 PM
 */
@Component
@Path(Constant.API_VER + "/" + Constant.API_PATH)
public class PaymentRestService extends BaseRestService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PaymentRestService.class);

  @Autowired
  private PaymentService paymentService;

  @GET
  @Path("/heartbeat")
  @Produces(value = MediaType.TEXT_PLAIN)
  public String heartbeat() {
    final StringBuffer heartbeatMsg = new StringBuffer("Payment API is online");
    return heartbeatMsg.toString();
  }

  @POST
  @Path("/init")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response initPayment() {
    final String username = getAuthUser();
    Response response;
    response = RESTfulUtil.getNotFound();
    return response;
  }

  @GET
  @Path("/appointments/{id}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getAppointmentId(@PathParam("id") int apptId) {
    Response response;
    try {
      response = RESTfulUtil.getOk(paymentService.getByAppointmentId(apptId));
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      if (e.getCode() == ServiceException.VALIDATION_FAILURE) {
        response = RESTfulUtil.getNotFound();
      } else {
        response = RESTfulUtil.getInternalServerError();
      }
    }
    return response;
  }
}
