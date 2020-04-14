package lk.elevenzcode.healthcare.hospitalapi.web.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.web.service.BaseRestService;
import lk.elevenzcode.healthcare.commons.web.util.RESTfulUtil;
import lk.elevenzcode.healthcare.hospitalapi.domain.Hospital;
import lk.elevenzcode.healthcare.hospitalapi.service.HospitalService;
import lk.elevenzcode.healthcare.hospitalapi.service.impl.HospitalServiceImpl;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.AppointmentIntegrationService;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.DoctorIntegrationService;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.dto.AppointmentInfo;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.dto.DoctorInfo;
import lk.elevenzcode.healthcare.hospitalapi.web.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by හShaන් සNදීප on 3/9/2020 8:45 PM
 */
@Component
@Path(Constant.API_VER + "/" + Constant.API_PATH)
public class HospitalRestService extends BaseRestService {
  private static final Logger LOGGER = LoggerFactory.getLogger(HospitalRestService.class);

  @Autowired
  private HospitalService hospitalService;
  private HospitalServiceImpl hospitalServiceImp;

  @Autowired
  private DoctorIntegrationService doctorIntegrationService;

  @Autowired
  private AppointmentIntegrationService appointmentIntegrationService;

  
  
  @POST
  @Path("/create")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response createHospital() {
    Response response = null;
    Hospital hospital = null;
    hospital.setHospitalName("Asiri Hospital");
    hospital.setHospitalAddress("colombo 12");
    hospital.setHospitalContact("+94 772261647");
    hospital.setHospitalDetails("good");
    try {
      System.out.println("inside the create method");
      hospitalServiceImp.createHospital(hospital);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
        response = RESTfulUtil.getInternalServerError();
    }
    return response;
  }
  

  
  @GET
  @Path("/heartbeat")
  @Produces(value = MediaType.TEXT_PLAIN)
  public String heartbeat() {
    final StringBuffer heartbeatMsg = new StringBuffer("Hospital API is online");
    final List<DoctorInfo> doctors = doctorIntegrationService.getByHospId(1);
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("doctors : {}", doctors);
    }
    heartbeatMsg.append("\nIntegration with Doctor API : ");
    if (doctors != null) {
      heartbeatMsg.append("Success");
    } else {
      heartbeatMsg.append("Fail");
    }
    final List<AppointmentInfo> appointments = appointmentIntegrationService.getByHospId(1);
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
      response = RESTfulUtil.getOk(hospitalService.getAll());
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
      response = RESTfulUtil.getOk(hospitalService.get(id));
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
