package lk.elevenzcode.healthcare.doctorapi.web.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.web.service.BaseRestService;
import lk.elevenzcode.healthcare.commons.web.service.dto.ServiceResponse;
import lk.elevenzcode.healthcare.commons.web.util.RESTfulUtil;
import lk.elevenzcode.healthcare.doctorapi.domain.Doctor;
import lk.elevenzcode.healthcare.doctorapi.domain.DoctorHospital;
import lk.elevenzcode.healthcare.doctorapi.domain.DoctorSession;
import lk.elevenzcode.healthcare.doctorapi.repository.DoctorRepository;
import lk.elevenzcode.healthcare.doctorapi.service.DoctorHospitalService;
import lk.elevenzcode.healthcare.doctorapi.service.DoctorService;
import lk.elevenzcode.healthcare.doctorapi.service.DoctorSessionService;
import lk.elevenzcode.healthcare.doctorapi.service.impl.DoctorServiceImpl;
import lk.elevenzcode.healthcare.doctorapi.service.integration.AppointmentIntegrationService;
import lk.elevenzcode.healthcare.doctorapi.service.integration.HospitalIntegrationService;
import lk.elevenzcode.healthcare.doctorapi.service.integration.dto.AppointmentInfo;
import lk.elevenzcode.healthcare.doctorapi.service.integration.dto.HospitalInfo;
import lk.elevenzcode.healthcare.doctorapi.web.dto.AssignHospitalReq;
import lk.elevenzcode.healthcare.doctorapi.web.dto.DoctorRegistration;
import lk.elevenzcode.healthcare.doctorapi.web.dto.DoctorSessionResp;
import lk.elevenzcode.healthcare.doctorapi.web.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
public class DoctorRestService extends BaseRestService {
  private static final Logger LOGGER = LoggerFactory.getLogger(DoctorRestService.class);

  @Autowired
  private DoctorService doctorService;


  @Autowired
  private DoctorRepository doctorRepository;

  @Autowired
  private DoctorServiceImpl doctorServiceimpl;

  @Autowired
  private HospitalIntegrationService hospitalIntegrationService;

  @Autowired
  private AppointmentIntegrationService appointmentIntegrationService;

  @Autowired
  private DoctorHospitalService doctorHospitalService;

  @Autowired
  private DoctorSessionService doctorSessionService;


  @GET
  @Path("/heartbeat")
  @Produces(value = MediaType.TEXT_PLAIN)
  public String heartbeat() {
    final StringBuffer heartbeatMsg = new StringBuffer("Doctor API is online");
    final HospitalInfo hospital = new HospitalInfo()/*hospitalIntegrationService.getById(1)*/;
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("hospital:1 : {}", hospital);
    }
    heartbeatMsg.append("\nIntegration with Hospital API : ");
    if (hospital != null) {
      heartbeatMsg.append("Success");
    } else {
      heartbeatMsg.append("Fail");
    }
    final List<AppointmentInfo> appointments = new ArrayList<>()/*appointmentIntegrationService
    .getByDocId(1)*/;
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


//  //insert doctor
////  @POST
////  public Response insert(Doctor domain) throws ServiceException {
////    Response response;
////    try {
////      doctorService.insert(domain);
////      response = RESTfulUtil.getOk(domain.getId());
////    } catch (ServiceException e) {
////      LOGGER.error(e.getMessage(), e);
////      response = RESTfulUtil.getInternalServerError();
////    }
////    return response;
////  }


  //create new patient
  @POST
  @Produces(value = MediaType.APPLICATION_JSON)
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response registerPatient(DoctorRegistration registerDto) {
    final ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
    try {
      serviceResponse.setBody(doctorService.register(registerDto));
      return RESTfulUtil.getCreated(serviceResponse);
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      setError(e, serviceResponse);
    }
    return RESTfulUtil.getOk(serviceResponse);
  }


  //get all doctor sevice
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


  //get one doctor sevice
  @GET
  @Path("/{doctorId}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getById(@PathParam("doctorId") int id) {
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


  //update one doctor
  @PUT
  @Path("/{doctorId}")
  public void update(@PathParam("doctorId") Integer id, Doctor domain) throws ServiceException {
    Doctor Doc = doctorRepository.findById(id).get();

    if (Doc != null) {

      doctorServiceimpl.update(domain, Doc);
    }
  }


  //delete one doctor
  @DELETE
  @Path("/{doctorId}")
  public void delete(@PathParam("doctorId") Integer id) throws ServiceException {
    doctorServiceimpl.delete(id);
  }


  //insert doctor hospital
  @POST
  @Path("/{doctorId}/hospitals/{hostpitleId}")
  public Response doctorHospitalInsert(@PathParam("doctorId") Integer doctorId,
                                       @PathParam("hostpitleId") Integer hostpitleId,
                                       AssignHospitalReq req) {
    Response response;
    try {
      doctorHospitalService.insert(new DoctorHospital(new Doctor(doctorId), hostpitleId,
          req.getDoctorFee()));
      response = RESTfulUtil.getOk();
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      response = RESTfulUtil.getInternalServerError();
    }
    return response;
  }


  //get doctor's hospital
  @GET
  @Path("/{doctorId}/hospitals")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response doctorhospitalgetById(@PathParam("doctorId") int id) {
    Response response;
    try {
      response = RESTfulUtil.getOk(doctorHospitalService.get(id));
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


  //post doctor session
  @POST
  @Path("/{doctorId}/hospitals/sessions")
  public Response doctorsession(@PathParam("doctorId") Integer doctorId, DoctorSession ds) {
    Response response;
    try {


      doctorSessionService.insert(new DoctorSession(new Doctor(doctorId), ds.getRoomId(),
          ds.getFrom(), ds.getTo(), ds.getStatus()));

      response = RESTfulUtil.getOk();
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      response = RESTfulUtil.getInternalServerError();
    }
    return response;
  }

  //get doctor's session
  @GET
  @Path("/{doctorId}/hospitals/sessions")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response doctorsessiongetById(@PathParam("doctorId") int id) {
    Response response;
    try {
      response = RESTfulUtil.getOk(doctorSessionService.get(id));
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

  @GET
  @Path("/hospitals/sessions/{sessionId}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getSessionDetails(@PathParam("sessionId") int sessionId) {
    Response response;
    try {
      final DoctorSession doctorSession = doctorSessionService.get(sessionId);
      if (doctorSession != null) {
        DoctorSessionResp doctorSessionResp = new DoctorSessionResp();
        doctorSessionResp.setRoom(hospitalIntegrationService.getByRoomId(doctorSession.getRoomId()));
        doctorSessionResp.setDoctor(doctorSession.getDoctor());
        doctorSessionResp.setFrom(doctorSession.getFrom());
        doctorSessionResp.setTo(doctorSession.getTo());
        final DoctorHospital doctorHospital = doctorHospitalService.get(doctorSession.getDoctor()
            .getId(), doctorSessionResp.getRoom().getHospital().getId());
        doctorSessionResp.setDocFee(doctorHospital.getDoctorFee());
        doctorSessionResp.setStatus(doctorSession.getStatus());
        response = RESTfulUtil.getOk(doctorSessionResp);
      } else {
        response = RESTfulUtil.getNotFound();
      }
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
