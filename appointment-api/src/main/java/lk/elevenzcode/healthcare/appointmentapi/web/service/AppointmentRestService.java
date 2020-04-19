package lk.elevenzcode.healthcare.appointmentapi.web.service;

import lk.elevenzcode.healthcare.appointmentapi.domain.Appointment;
import lk.elevenzcode.healthcare.appointmentapi.domain.AppointmentStatus;
import lk.elevenzcode.healthcare.appointmentapi.service.AppointmentService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.DoctorIntegrationService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.HospitalIntegrationService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.PatientIntegrationService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.PaymentIntegrationService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.DoctorInfo;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.HospitalInfo;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.PatientInfo;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.PaymentInfo;
import lk.elevenzcode.healthcare.appointmentapi.web.dto.AppointmentInfoResp;
import lk.elevenzcode.healthcare.appointmentapi.web.dto.AppointmentUpdateReq;
import lk.elevenzcode.healthcare.appointmentapi.web.util.Constant;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.web.service.BaseRestService;
import lk.elevenzcode.healthcare.commons.web.util.RESTfulUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

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

/**
 * @author Yasas Alwis
 */
@Component
@Path(Constant.API_VER + "/" + Constant.API_PATH)
public class AppointmentRestService extends BaseRestService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentRestService.class);

	// Spring boot automatically wire the objects to following parameters when using
	// @autowired annotation

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

	// This method uses to check the availability of the services
	@SuppressWarnings("unused")
	@GET
	@Path("/heartbeat")
	@Produces(value = MediaType.TEXT_PLAIN)
	public String heartbeat() {
		final StringBuffer heartbeatMsg = new StringBuffer("Appointment API is online");
		/*
		 * final HospitalInfo hospital = hospitalIntegrationService.getById(1); if
		 * (LOGGER.isDebugEnabled()) { LOGGER.debug("hospital:1 : {}", hospital); }
		 */
		final List<HospitalInfo> hospitals = hospitalIntegrationService.getAll();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("hospitals : {}", hospitals);
		}
		heartbeatMsg.append("\nIntegration with Hospital API : ");
		if (hospitals != null) {
			heartbeatMsg.append("Success");
		} else {
			heartbeatMsg.append("Fail");
		}
		/*
		 * final DoctorInfo doctor = doctorIntegService.getById(1); if
		 * (LOGGER.isDebugEnabled()) { LOGGER.debug("doctor:1 : {}", doctor); }
		 */
		final List<DoctorInfo> doctors = doctorIntegService.getAll();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doctors : {}", doctors);
		}
		heartbeatMsg.append("\nIntegration with Doctor API : ");
		if (doctors != null) {
			heartbeatMsg.append("Success");
		} else {
			heartbeatMsg.append("Fail");
		}
		/*
		 * final PatientInfo patient = patientIntegrationService.getById(1); if
		 * (LOGGER.isDebugEnabled()) { LOGGER.debug("patient:1 : {}", patient); }
		 */
		final List<PatientInfo> patients = patientIntegrationService.getAll();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("patients : {}", patients);
		}
		heartbeatMsg.append("\nIntegration with Patient API : ");
		if (patients != null) {
			heartbeatMsg.append("Success");
		} else {
			heartbeatMsg.append("Fail");
		}
		final PaymentInfo payment = new PaymentInfo()/*
														 * paymentIntegrationService.getByAppointmentId (1)
														 */;
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

	// Returns appointments by given session id
	@GET
	@Path("/sessions/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBySessionId(@PathParam("id") int id) {
		Response response;
		try {
			response = RESTfulUtil.getOk(appointmentService.findBySessionId(id));
		} catch (ServiceException e) {
			if (e.getCode() == ServiceException.PROCESSING_FAILURE) {
				response = RESTfulUtil.getNotFound();
			} else {
				response = RESTfulUtil.getInternalServerError();
			}
		}
		return response;
	}

	// Returns appointments with by patient id
	@GET
	@Path("/patients/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getByPtId(@PathParam("id") int ptId) {
		Response response;
		try {
			response = RESTfulUtil.getOk(appointmentService.findByPatient(ptId));
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

	// Returns all appointments as JSON array
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAppointment() throws ServiceException {
		return RESTfulUtil.getOk(appointmentService.findAll());
	}

	// Returns all appointments as JSON array
	@GET
	@Path("/status/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAppointmentByResponse(@PathParam("id") int id) throws ServiceException {
		return RESTfulUtil.getOk(appointmentService.findByStatus(new AppointmentStatus(id)));
	}

	// Returns appointments by given id
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAppointmentById(@PathParam("id") int id) {
		Response response;
		try {
			final Appointment appointment = appointmentService.get(id);
			if (appointment != null) {
				final AppointmentInfoResp appointmentInfoResp = new AppointmentInfoResp();
				appointmentInfoResp.setId(appointment.getId());
				appointmentInfoResp.setPatient(patientIntegrationService.getById(appointment.getPatientId()));
				appointmentInfoResp.setSession(doctorIntegService.getSessionInfo(appointment.getSessionId()));
				appointmentInfoResp.setAppointmentDate(appointment.getAppointmentDate());
				appointmentInfoResp.setCreateDate(appointment.getCreateDate());
				appointmentInfoResp.setStatus(appointment.getStatus().getId());
				response = RESTfulUtil.getOk(appointmentInfoResp);
			} else {
				response = RESTfulUtil.getNotFound();
			}
		} catch (ServiceException e) {
			if (e.getCode() == ServiceException.PROCESSING_FAILURE) {
				response = RESTfulUtil.getNotFound();
			} else {
				response = RESTfulUtil.getBadRequest();
			}
		}
		return response;
	}

	// Creates new appointment using JSON object
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAppointment(@RequestBody Appointment appointment) {
		Response response = null;
		try {
			if (appointment.getSessionId() != null) {
				if (patientIntegrationService.getById(appointment.getPatientId()) != null) {
					if (doctorIntegService.getSessionInfo(appointment.getSessionId()) != null) {
						appointmentService.insert(appointment);
						response = RESTfulUtil.getCreated(appointment);
					}
				}
			}
		} catch (Exception e) {
			response = RESTfulUtil.getBadRequest();
		}
		return response;
	}

	// Update Existing appointment
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putAppointment(@PathParam("id") int id, AppointmentUpdateReq updateReq) {
		Response response = null;
		try {
			if (updateReq != null && updateReq.getStatus() != null) {
				final Appointment existinOb = appointmentService.get(id);
				if (existinOb.getStatus().getId().intValue() != updateReq.getStatus().intValue()) {
					existinOb.setStatus(new AppointmentStatus(updateReq.getStatus()));
					appointmentService.update(existinOb);
					response = RESTfulUtil.getOk(existinOb);
				}else {
					response = RESTfulUtil.getBadRequest();
				}
			} else {
				response = RESTfulUtil.getBadRequest();
			}
		} catch (Exception e) {
			response = RESTfulUtil.getBadRequest();
		}
		return response;
	}

	// Delete existing appointment
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAppointment(@PathParam("id") int id) throws ServiceException {
		if (appointmentService.get(id) != null) {
			final Appointment appointment = appointmentService.get(id);
			if (appointment.getStatus().getId().intValue() != 3) {
				appointment.setStatus(new AppointmentStatus(3));
				appointmentService.update(appointment);
				return RESTfulUtil.getOk(appointment);
			}
		}
		return RESTfulUtil.getBadRequest();
	}
}
