/**
 * 
 */
package lk.elevenzcode.healthcare.appointmentapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.elevenzcode.healthcare.appointmentapi.domain.Appointment;
import lk.elevenzcode.healthcare.appointmentapi.repository.AppointmentRepository;

/**
 * @author Yasas Alwis
 *
 */
@Service
public class AppointmentService implements IAppointmentService{
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public void createAppointment(Appointment appointment) {
		appointmentRepository.save(appointment);
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		appointmentRepository.save(appointment);
	}

	@Override
	public void deleteAppointment(Appointment appointment) {
		if(appointmentRepository.existsById(appointment.getAppointmentid())) {
			appointmentRepository.delete(appointment);
		}
	}

	@Override
	public Iterable<Appointment> findAll() {
		Iterable<Appointment> appointments = appointmentRepository.findAll();
		return appointments;
	}
	
	@Override
	public List<Appointment> findByPatient(Long patientId) {
		List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
		return appointments;
	}
	
	@Override
	public List<Appointment> findByDoctor(Long doctorId) {
		List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
		return appointments;
	}
}
