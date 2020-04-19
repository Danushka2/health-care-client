package lk.elevenzcode.healthcare.paymentapi.web.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.util.ConversionUtil;
import lk.elevenzcode.healthcare.commons.web.service.BaseRestService;
import lk.elevenzcode.healthcare.commons.web.service.dto.ServiceResponse;
import lk.elevenzcode.healthcare.commons.web.util.RESTfulUtil;
import lk.elevenzcode.healthcare.paymentapi.domain.Payment;
import lk.elevenzcode.healthcare.paymentapi.service.PaymentService;
import lk.elevenzcode.healthcare.paymentapi.service.integration.AppointmentIntegrationService;
import lk.elevenzcode.healthcare.paymentapi.service.integration.StripeIntegrationService;
import lk.elevenzcode.healthcare.paymentapi.service.integration.dto.AppointmentInfo;
import lk.elevenzcode.healthcare.paymentapi.web.dto.PaymentCompleteReq;
import lk.elevenzcode.healthcare.paymentapi.web.dto.PaymentInfoResp;
import lk.elevenzcode.healthcare.paymentapi.web.dto.PaymentInitReq;
import lk.elevenzcode.healthcare.paymentapi.web.dto.PaymentInitResp;
import lk.elevenzcode.healthcare.paymentapi.web.dto.PaymentRefundReq;
import lk.elevenzcode.healthcare.paymentapi.web.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

  @Autowired
  private StripeIntegrationService stripeIntegrationService;

  @Autowired
  private AppointmentIntegrationService appointmentIntegrationService;

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
  public Response initPayment(PaymentInitReq req) {
    final ServiceResponse<PaymentInitResp> serviceResponse = new ServiceResponse<>();
    try {
      //validate request
      if (req != null && req.getAppointmentId() > 0) {
        final AppointmentInfo appointmentInfo = appointmentIntegrationService.getByApptId(req
            .getAppointmentId());
        //validate appointment & it's status
        if (appointmentInfo != null && appointmentInfo.getStatus() == AppointmentInfo.Status.PENDING
            .getId()) {
          //calculate appointment fee
          final BigDecimal appointmentFee = appointmentInfo.getSession().getDocFee()
              .add(appointmentInfo.getSession().getRoom().getRoomFee());
          //generate client secret
          serviceResponse.setBody(new PaymentInitResp(stripeIntegrationService
              .initPayment(ConversionUtil.getMoneyInCents(appointmentFee)).getClientSecret()));
        } else {
          throw new ServiceException(ServiceException.VALIDATION_FAILURE,
              "label.payment.err.appointment.not.available");
        }
      } else {
        return RESTfulUtil.getBadRequest();
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      setError(e, serviceResponse);
    }
    return RESTfulUtil.getCreated(serviceResponse);
  }

  @PUT
  @Path("/complete")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response comletePayment(PaymentCompleteReq req) {
    final ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
    try {
      serviceResponse.setBody(paymentService.save(req.getAppointmentId(),
          req.getPaymentIntentId()));
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      setError(e, serviceResponse);
    }
    return RESTfulUtil.getOk(serviceResponse);
  }

  @GET
  @Path("/appointments/{apptId}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getAppointmentId(@PathParam("apptId") int apptId) {
    final ServiceResponse<PaymentInfoResp> serviceResponse = new ServiceResponse<>();
    try {
      final Payment payment = paymentService.getByAppointmentId(apptId);
      //validate whether the payment exist for the appointment
      if (payment != null) {
        serviceResponse.setBody(new PaymentInfoResp(payment.getId(),
            payment.getReference(), payment.getAmount(), payment.getPaidOn(),
            payment.getStatus().getName()));
      } else {
        throw new ServiceException(ServiceException.VALIDATION_FAILURE,
            "label.payment.err.not.found");
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      setError(e, serviceResponse);
    }
    return RESTfulUtil.getOk(serviceResponse);
  }

  @GET
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getAll() {
    final ServiceResponse<List<PaymentInfoResp>> serviceResponse = new ServiceResponse<>();
    try {
      final List<PaymentInfoResp> list = new ArrayList<>();
      for (Payment payment : paymentService.getAll()) {
        list.add(new PaymentInfoResp(payment.getId(),
            payment.getReference(), payment.getAmount(), payment.getPaidOn(),
            payment.getStatus().getName()));
      }
      serviceResponse.setBody(list);
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      setError(e, serviceResponse);
    }
    return RESTfulUtil.getOk(serviceResponse);
  }

  @DELETE
  @Path("/appointments/{apptId}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response refundPaymentByAppointment(@PathParam("apptId") int apptId,
                                             PaymentRefundReq refundReq) {
    final ServiceResponse<Void> serviceResponse = new ServiceResponse<>();
    try {
      paymentService.refundByAppt(apptId, refundReq.getReason());
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      setError(e, serviceResponse);
    }
    return RESTfulUtil.getOk(serviceResponse);
  }

  @DELETE
  @Path("/{id}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response refundPayment(@PathParam("id") int id, PaymentRefundReq refundReq) {
    final ServiceResponse<Void> serviceResponse = new ServiceResponse<>();
    try {
      paymentService.refund(id, refundReq.getReason());
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      setError(e, serviceResponse);
    }
    return RESTfulUtil.getOk(serviceResponse);
  }

  @GET
  @Path("/{id}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getById(@PathParam("id") int id) {
    final ServiceResponse<PaymentInfoResp> serviceResponse = new ServiceResponse<>();
    try {
      final Payment payment = paymentService.get(id);
      //validate whether the payment exist for given id
      if (payment != null) {
        serviceResponse.setBody(new PaymentInfoResp(payment.getId(),
            payment.getReference(), payment.getAmount(), payment.getPaidOn(),
            payment.getStatus().getName()));
      } else {
        throw new ServiceException(ServiceException.VALIDATION_FAILURE,
            "label.payment.err.not.found");
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      setError(e, serviceResponse);
    }
    return RESTfulUtil.getOk(serviceResponse);
  }
}
