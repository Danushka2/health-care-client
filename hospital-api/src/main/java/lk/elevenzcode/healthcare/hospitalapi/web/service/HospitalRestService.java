package lk.elevenzcode.healthcare.hospitalapi.web.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.web.service.BaseRestService;
import lk.elevenzcode.healthcare.commons.web.util.RESTfulUtil;
import lk.elevenzcode.healthcare.hospitalapi.domain.Hospital;
import lk.elevenzcode.healthcare.hospitalapi.domain.HospitalRoom;
import lk.elevenzcode.healthcare.hospitalapi.service.HospitalRoomService;
import lk.elevenzcode.healthcare.hospitalapi.service.HospitalService;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.AppointmentIntegrationService;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.DoctorIntegrationService;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.dto.AppointmentInfo;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.dto.DoctorInfo;
import lk.elevenzcode.healthcare.hospitalapi.web.dto.HospitalInfoResp;
import lk.elevenzcode.healthcare.hospitalapi.web.dto.RoomInfoResp;
import lk.elevenzcode.healthcare.hospitalapi.web.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


@Component
@Path(Constant.API_VER + "/" + Constant.API_PATH)
public class HospitalRestService extends BaseRestService {
  private static final Logger LOGGER = LoggerFactory.getLogger(HospitalRestService.class);

  @Autowired
  private HospitalService hospitalService;

  @Autowired
  private DoctorIntegrationService doctorIntegrationService;

  @Autowired
  private AppointmentIntegrationService appointmentIntegrationService;

  @Autowired
  private HospitalRoomService hospitalRoomService;

  @GET
  @Path("/heartbeat")
  @Produces(value = MediaType.TEXT_PLAIN)
  public String heartbeat() {
    final StringBuffer heartbeatMsg = new StringBuffer("Hospital API is online");
    final List<DoctorInfo> doctors = new ArrayList<>()/*doctorIntegrationService.getByHospId(1)*/;
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("doctors : {}", doctors);
    }
    heartbeatMsg.append("\nIntegration with Doctor API : ");
    if (doctors != null) {
      heartbeatMsg.append("Success");
    } else {
      heartbeatMsg.append("Fail");
    }
    final List<AppointmentInfo> appointments = new ArrayList<>()/*appointmentIntegrationService
    .getByHospId(1)*/;
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
  public Response createHospital(Hospital hospital) {
    Response response = null;
    try {
      hospitalService.insert(hospital);
      response = RESTfulUtil.getOk(hospital);
    } catch (Exception e) {
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

  @DELETE
  @Path("/{id}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response deleteById(@PathParam("id") int id) {
    Response response;
    try {
      hospitalService.deleteHospital(id);
      response = RESTfulUtil.getOk("deleted");
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      response = RESTfulUtil.getNotFound();

    }
    return response;
  }

  @PUT
  @Path("/{id}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response updateHospital(@PathParam("id") int id, Hospital hospital) {
    Response response;
    try {
      hospital.setId(id);
      hospitalService.update(hospital);
      response = RESTfulUtil.getOk("updated" + hospital.getId());
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      response = RESTfulUtil.getNotFound();
    }
    return response;
  }

  @POST
  @Path("/rooms")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response createRoom(HospitalRoom room) {
    Response response = null;
    try{
      Hospital hospital = hospitalService.get(2);
      room.setHospital(hospitalService.get(2));
    }catch (Exception e){
      System.out.println("testing e");
    }


    try {
      hospitalRoomService.insert(room);
      response = RESTfulUtil.getOk(room);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      response = RESTfulUtil.getInternalServerError();
    }
    return response;
  }

  @GET
  @Path("/rooms/{roomId}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getRoomById(@PathParam("roomId") int roomId) {
    Response response;
    try {
      final HospitalRoom room = hospitalRoomService.get(roomId);
      if (room != null) {
        RoomInfoResp roomInfoResp = new RoomInfoResp();
        roomInfoResp.setId(room.getId());
        final Hospital hospital = room.getHospital();
        roomInfoResp.setHospital(new HospitalInfoResp(hospital.getId(),
            hospital.getHospitalName(), hospital.getHospitalAddress(),
            hospital.getHospitalEmail(), hospital.getHospitalFax(), hospital.getHospitalTell()));
        roomInfoResp.setRoomNo(room.getRoomNo());
        roomInfoResp.setLocation(room.getLocation());
        roomInfoResp.setRoomFee(room.getFee());
        response = RESTfulUtil.getOk(roomInfoResp);
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
