/**
 *
 */
package lk.elevenzcode.healthcare.appointmentapi.service.impl;

import lk.elevenzcode.healthcare.appointmentapi.domain.Appointment;
import lk.elevenzcode.healthcare.appointmentapi.repository.AppointmentRepository;
import lk.elevenzcode.healthcare.appointmentapi.service.AppointmentService;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.annotation.PostConstruct;

/**
 * @author Yasas Alwis
 *
 */
@Service
public class AppointmentServiceImpl extends GenericServiceImpl<Appointment> implements AppointmentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceImpl.class);

	@Autowired
	private AppointmentRepository appointmentRepository;

	@PostConstruct
	void init() {
		init(appointmentRepository);
	}

	@Override
	public void deleteAppointment(Appointment appointment) {
		if (appointmentRepository.existsById(appointment.getId())) {
			appointmentRepository.delete(appointment);
		}
	}

	@Override
	public Iterable<Appointment> findAll() {
		Iterable<Appointment> appointments = appointmentRepository.findAll();
		return appointments;
	}

	@Override
	public List<Appointment> findByPatient(int patientId) throws ServiceException {
		try {
			return appointmentRepository.findByPatientId(patientId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(ServiceException.PROCESSING_FAILURE, e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<Appointment> findBySessionId(int sessionId) throws ServiceException {
		try {
			return appointmentRepository.findBySessionId(sessionId);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(ServiceException.PROCESSING_FAILURE, e.getMessage(), e.getCause());
		}
	}
}
