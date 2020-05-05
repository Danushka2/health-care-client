package lk.elevenzcode.healthcare.hospitalapi.web.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.web.service.BaseRestService;
import lk.elevenzcode.healthcare.commons.web.service.dto.ServiceResponse;
import lk.elevenzcode.healthcare.commons.web.util.RESTfulUtil;
import lk.elevenzcode.healthcare.hospitalapi.domain.Hospital;
import lk.elevenzcode.healthcare.hospitalapi.domain.HospitalRoom;
import lk.elevenzcode.healthcare.hospitalapi.service.HospitalRoomService;
import lk.elevenzcode.healthcare.hospitalapi.service.HospitalService;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.AppointmentIntegrationService;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.DoctorIntegrationService;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.dto.DoctorInfo;
import lk.elevenzcode.healthcare.hospitalapi.web.dto.HospitalInfoResp;
import lk.elevenzcode.healthcare.hospitalapi.web.dto.RoomInfoRequest;
import lk.elevenzcode.healthcare.hospitalapi.web.dto.RoomInfoResp;
import lk.elevenzcode.healthcare.hospitalapi.web.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

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
    return heartbeatMsg.toString();
  }

  @POST
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response createHospital(HospitalInfoResp hospitalDto) {
    final ServiceResponse<Hospital> serviceResponse = new ServiceResponse<>();
    try {
      Integer userId = hospitalService.registerUser(hospitalDto.getUsername(),
          hospitalDto.getPassword());
      Hospital hospital = new Hospital(hospitalDto.getId(), hospitalDto.getName(),
          hospitalDto.getAddress(), hospitalDto.getEmail(), hospitalDto.getType(),
          hospitalDto.getFax(),
          hospitalDto.getTel(), hospitalDto.getStatus(), userId);
      hospitalService.insert(hospital);
      serviceResponse.setBody(hospital);
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      setError(e, serviceResponse);
    }
    return RESTfulUtil.getOk(serviceResponse);
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
    Hospital hospital;
    try {
      hospital = hospitalService.get(id);
      if (hospital != null) {
        response = RESTfulUtil.getOk(hospital);
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


  @DELETE
  @Path("/{id}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response deleteById(@PathParam("id") int id) {
    Response response;
    JSONObject json = new JSONObject();
    try {
      hospitalService.deleteHospital(id);

      json.put("hasError", false);
      json.put("body", "deleted");

      response = RESTfulUtil.getOk(json.toString());
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      response = RESTfulUtil.getNotFound();
    }
    return response;
  }


  @PUT
  @Path("/{id}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response updateHospital(@PathParam("id") int id, HospitalInfoResp hospitalDto) {
    Response response;
    JSONObject json = new JSONObject();
    try {
      Hospital hospital = new Hospital(id, hospitalDto.getName(),
          hospitalDto.getAddress(), hospitalDto.getEmail(), hospitalDto.getType(),
          hospitalDto.getFax(),
          hospitalDto.getTel(), hospitalDto.getStatus(), null);
      hospitalService.update(hospital);

      json.put("hasError", false);
      json.put("body", "updated");

      response = RESTfulUtil.getOk(json.toString());
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      response = RESTfulUtil.getNotFound();
    }
    return response;
  }


  @POST
  @Path("/rooms")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response createRoom(RoomInfoRequest room) {
    Response response = null;
    try {
      Hospital hospital = hospitalService.get(room.getHospitalId());
      HospitalRoom hospRoom = new HospitalRoom();

      if (hospital != null) {
        hospRoom.setHospital(hospital);
        hospRoom.setRoomNo(room.getRoomNo());
        hospRoom.setLocation(room.getLocation());
        hospRoom.setFee(room.getRoomFee());
        hospRoom.setStatus(room.getStatus());

        hospitalRoomService.insert(hospRoom);
        response = RESTfulUtil.getOk(hospRoom);
      } else {
        response = RESTfulUtil.getNotFound();
      }

    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      response = RESTfulUtil.getInternalServerError();
    }
    return response;
  }


  @GET
  @Path("/rooms")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getAllRooms() {
    Response response;
    try {
      response = RESTfulUtil.getOk(hospitalRoomService.getAll());
    } catch (ServiceException e) {
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
            hospital.getHospitalEmail(), hospital.getHospitalFax(), hospital.getHospitalTell(),
            hospital.getHospitalType(), hospital.getHospitalStatus(), null, null));
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


  @PUT
  @Path("/rooms/{id}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response updateHospitalRoom(@PathParam("id") int id, RoomInfoRequest room) {
    Response response = null;
    try {
      Hospital hospital = hospitalService.get(room.getHospitalId());
      HospitalRoom hospRoom = new HospitalRoom();

      if (hospital != null) {
        hospRoom.setId(id);
        hospRoom.setHospital(hospital);
        hospRoom.setRoomNo(room.getRoomNo());
        hospRoom.setLocation(room.getLocation());
        hospRoom.setFee(room.getRoomFee());
        hospRoom.setStatus(room.getStatus());

        hospitalRoomService.update(hospRoom);
        response = RESTfulUtil.getOk(hospRoom);
      } else {
        response = RESTfulUtil.getNotFound();
      }

    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      response = RESTfulUtil.getInternalServerError();
    }
    return response;
  }


  @DELETE
  @Path("/rooms/{id}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response deleteRoomById(@PathParam("id") int id) {
    Response response;
    try {
      HospitalRoom hospitalRoom = hospitalRoomService.get(id);
      hospitalRoom.setStatus((short) 3);
      hospitalRoomService.update(hospitalRoom);
      response = RESTfulUtil.getOk("deleted");
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      response = RESTfulUtil.getNotFound();

    }
    return response;
  }


  @GET
  @Path("/doctors/{hospId}")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getDoctorsByHospId(@PathParam("hospId") int hospId) {
    Response response;
    Hospital hospital;
    List<DoctorInfo> doctors;
    System.out.println("Testing");
    try {
      hospital = hospitalService.get(hospId);
      if (hospital != null) {
        doctors = doctorIntegrationService.getByHospId(hospId);
        response = RESTfulUtil.getOk(doctors);
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
