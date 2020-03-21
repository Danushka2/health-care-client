package lk.elevenzcode.healthcare.appointmentapi.web.service;

import lk.elevenzcode.healthcare.appointmentapi.service.AppointmentService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.DoctorIntegrationService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.HospitalIntegrationService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.PatientIntegrationService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.PaymentIntegrationService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.DoctorInfo;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.HospitalInfo;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.PatientInfo;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.PaymentInfo;
import lk.elevenzcode.healthcare.appointmentapi.web.util.Constant;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.web.service.BaseRestService;
import lk.elevenzcode.healthcare.commons.web.util.RESTfulUtil;
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

/**
 * Created by හShaන් සNදීප on 3/9/2020 8:46 PM
 */
@Component
@Path(Constant.API_VER + "/" + Constant.API_PATH)
public class AppointmentRestService extends BaseRestService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentRestService.class);
  @Autowired
  private AppointmentService appointmentService;

  @Autowired
  private DoctorIntegrationService doctorIntegService;

  @Autowired
  private HospitalIntegrationService hospitalIntegrationService;

  @Autowired
  private PatientIntegrationService patientIntegrationService;

  @Autowired
  private PaymentIntegrationService paymentIntegrationService;

  @GET
  @Path("/heartbeat")
  @Produces(value = MediaType.TEXT_PLAIN)
  public String heartbeat() {
    final StringBuffer heartbeatMsg = new StringBuffer("Appointment API is online");
    final HospitalInfo hospital = hospitalIntegrationService.getById(1);
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("hospital:1 : {}", hospital);
    }
    final List<HospitalInfo> hospitals = hospitalIntegrationService.getAll();
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("hospitals : {}", hospitals);
    }
    heartbeatMsg.append("\nIntegration with Hospital API : ");
    if (hospital != null) {
      heartbeatMsg.append("Success");
    } else {
      heartbeatMsg.append("Fail");
    }
    final DoctorInfo doctor = doctorIntegService.getById(1);
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("doctor:1 : {}", doctor);
    }
    final List<DoctorInfo> doctors = doctorIntegService.getAll();
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("doctors : {}", doctors);
    }
    heartbeatMsg.append("\nIntegration with Doctor API : ");
    if (doctor != null) {
      heartbeatMsg.append("Success");
    } else {
      heartbeatMsg.append("Fail");
    }
    final PatientInfo patient = patientIntegrationService.getById(1);
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("patient:1 : {}", patient);
    }
    final List<PatientInfo> patients = patientIntegrationService.getAll();
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("patients : {}", patients);
    }
    heartbeatMsg.append("\nIntegration with Patient API : ");
    if (patient != null) {
      heartbeatMsg.append("Success");
    } else {
      heartbeatMsg.append("Fail");
    }
    final PaymentInfo payment = paymentIntegrationService.getByAppointmentId(1);
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("payment:1 : {}", payment);
    }
    heartbeatMsg.append("\nIntegration with Payment API : ");
    if (payment != null) {
      heartbeatMsg.append("Success");
    } else {
      heartbeatMsg.append("Fail");
    }
    return heartbeatMsg.toString();
  }

  @GET
  @Path("/doctors/{id}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getByDocId(@PathParam("id") int docId) {
    Response response;
    try {
      response = RESTfulUtil.getOk(appointmentService.findByDoctor(docId));
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
      // TODO: 3/22/2020 set appointments which make on given hospital
      response = RESTfulUtil.getOk(appointmentService.getAll());
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
  @Path("/patients/{id}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getByPtId(@PathParam("id") int ptId) {
    Response response;
    try {
      // TODO: 3/22/2020 set appointments which made by given patient
      response = RESTfulUtil.getOk(appointmentService.getAll());
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
