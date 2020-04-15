package lk.elevenzcode.healthcare.doctorapi.web.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.web.service.BaseRestService;
import lk.elevenzcode.healthcare.commons.web.util.RESTfulUtil;
import lk.elevenzcode.healthcare.doctorapi.service.DoctorService;
import lk.elevenzcode.healthcare.doctorapi.service.integration.AppointmentIntegrationService;
import lk.elevenzcode.healthcare.doctorapi.service.integration.HospitalIntegrationService;
import lk.elevenzcode.healthcare.doctorapi.service.integration.dto.AppointmentInfo;
import lk.elevenzcode.healthcare.doctorapi.service.integration.dto.HospitalInfo;
import lk.elevenzcode.healthcare.doctorapi.web.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Component
@Path(Constant.API_VER + "/" + Constant.API_PATH)
public class DoctorRestService extends BaseRestService {
  private static final Logger LOGGER = LoggerFactory.getLogger(DoctorRestService.class);

  @Autowired
  private DoctorService doctorService;

  @Autowired
  private HospitalIntegrationService hospitalIntegrationService;

  @Autowired
  private AppointmentIntegrationService appointmentIntegrationService;

  @GET
  @Path("/heartbeat")
  @Produces(value = MediaType.TEXT_PLAIN)
  public String heartbeat() {
    final StringBuffer heartbeatMsg = new StringBuffer("Doctor API is online");
    final HospitalInfo hospital = hospitalIntegrationService.getById(1);
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("hospital:1 : {}", hospital);
    }
    heartbeatMsg.append("\nIntegration with Hospital API : ");
    if (hospital != null) {
      heartbeatMsg.append("Success");
    } else {
      heartbeatMsg.append("Fail");
    }
    final List<AppointmentInfo> appointments = appointmentIntegrationService.getByDocId(1);
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("appointments : {}", appointments);
    }
    heartbeatMsg.append("\nIntegration with Appointment API : ");
    if (appointments != null) {
      heartbeatMsg.append("Success");
    } else {
      heartbeatMsg.append("Fail");
    }
    return heartbeatMsg.toString();
  }

  @GET
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getAll() {
    Response response;
    try {
      response = RESTfulUtil.getOk(doctorService.getAll());
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      response = RESTfulUtil.getInternalServerError();
    }
    return response;
  }

  @GET
  @Path("/{id}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getById(@PathParam("id") int id) {
    Response response;
    try {
      response = RESTfulUtil.getOk(doctorService.get(id));
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

  @GET
  @Path("/hospitals/{id}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getByHospId(@PathParam("id") int hospId) {
    Response response;
    try {
      // TODO: 3/22/2020 set doctors who works in given hospital
      response = RESTfulUtil.getOk(doctorService.getAll());
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
