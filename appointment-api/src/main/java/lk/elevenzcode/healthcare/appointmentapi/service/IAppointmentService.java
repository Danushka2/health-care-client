package lk.elevenzcode.healthcare.appointmentapi.service;

import java.util.List;

import lk.elevenzcode.healthcare.appointmentapi.domain.Appointment;

/**
 * @author Yasas Alwis
 *
 */
public interface IAppointmentService {
	void createAppointment(Appointment appointment);
	void updateAppointment(Appointment appointment);
	void deleteAppointment(Appointment appointment);
	Iterable<Appointment> findAll();
	List<Appointment> findByDoctor(Long Doctor);
	List<Appointment> findByPatient(Long patient);
}
