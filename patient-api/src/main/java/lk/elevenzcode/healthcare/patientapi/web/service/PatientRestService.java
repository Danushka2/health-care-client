package lk.elevenzcode.healthcare.patientapi.web.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.web.service.BaseRestService;
import lk.elevenzcode.healthcare.commons.web.util.RESTfulUtil;
import lk.elevenzcode.healthcare.patientapi.domain.Patient;
import lk.elevenzcode.healthcare.patientapi.domain.PatientStatus;
import lk.elevenzcode.healthcare.patientapi.service.PatientService;
import lk.elevenzcode.healthcare.patientapi.service.integration.AppointmentIntegrationService;
import lk.elevenzcode.healthcare.patientapi.service.integration.dto.AppointmentInfo;
import lk.elevenzcode.healthcare.patientapi.web.dto.PatientRegisterDto;
import lk.elevenzcode.healthcare.patientapi.web.dto.PatientUpdateDto;
import lk.elevenzcode.healthcare.patientapi.web.util.Constant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Component
@Path(Constant.API_VER + "/" + Constant.API_PATH)
public class PatientRestService extends BaseRestService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PatientRestService.class);

  @Autowired
  private PatientService patientService;

  @Autowired
  private AppointmentIntegrationService appointmentIntegrationService;

  @GET
  @Path("/heartbeat")
  @Produces(value = MediaType.TEXT_PLAIN)
  public String heartbeat() {
    final StringBuffer heartbeatMsg = new StringBuffer("Patient API is online");
    final List<AppointmentInfo> appointments = appointmentIntegrationService.getByPtId(1);
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

  @POST
  @Produces(value = MediaType.APPLICATION_JSON)
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response registerPatient(PatientRegisterDto registerDto) {
    Response response;
    try {
      final Patient patient = new Patient();
      patient.setName(registerDto.getName());
      patient.setEmail(registerDto.getEmail());
      patient.setPhoneNumber(registerDto.getPhoneNo());
      patient.setAge(registerDto.getAge());
      patient.setAge(registerDto.getAge());
      patient.setStatus(new PatientStatus(PatientStatus.STATUS_ACTIVE));
      response = RESTfulUtil.getCreated(patientService.insert(patient));
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      response = RESTfulUtil.getInternalServerError();
    }
    return response;
  }

  @GET
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getAll() {
    Response response;
    try {
      response = RESTfulUtil.getOk(patientService.getAll());
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
      response = RESTfulUtil.getOk(patientService.get(id));
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

  @PUT
  @Path("/{id}")
  @Produces(value = MediaType.APPLICATION_JSON)
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response updatePatient(@PathParam(value = "id") int id, PatientUpdateDto updateDto) {
    Response response;
    try {
      if (StringUtils.isNotEmpty(updateDto.getEmail()) || StringUtils.isNotEmpty(updateDto
          .getPhoneNo()) || (updateDto.getStatus() != null && (updateDto.getStatus()
          == PatientStatus.STATUS_INACTIVE || updateDto.getStatus() == PatientStatus.STATUS_ACTIVE
          || updateDto.getStatus() == PatientStatus.STATUS_DELETED))) {
        final Patient patient = patientService.get(id);
        if (patient != null) {
          if (StringUtils.isNotEmpty(updateDto.getEmail())) {
            patient.setEmail(updateDto.getEmail());
          }
          if (StringUtils.isNotEmpty(updateDto.getPhoneNo())) {
            patient.setPhoneNumber(updateDto.getPhoneNo());
          }
          if (updateDto.getStatus() == PatientStatus.STATUS_INACTIVE || updateDto.getStatus()
              == PatientStatus.STATUS_ACTIVE || updateDto.getStatus() == PatientStatus.STATUS_DELETED) {
            patient.setStatus(new PatientStatus(updateDto.getStatus()));
          }
          patientService.update(patient);
          response = RESTfulUtil.getOk();
        } else {
          response = RESTfulUtil.getBadRequest();
        }
      } else {
        response = RESTfulUtil.getBadRequest();
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      response = RESTfulUtil.getInternalServerError();
    }
    return response;
  }

  @DELETE
  @Path("/{id}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response deletePatient(@PathParam(value = "id") int id) {
    Response response;
    try {
      final Patient patient = patientService.get(id);
      if (patient != null) {
        patient.setStatus(new PatientStatus(PatientStatus.STATUS_DELETED));
        patientService.update(patient);
        response = RESTfulUtil.getOk();
      } else {
        response = RESTfulUtil.getBadRequest();
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      response = RESTfulUtil.getInternalServerError();
    }
    return response;
  }
}
