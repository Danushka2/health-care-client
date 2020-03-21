package lk.elevenzcode.healthcare.appointmentapi.service;

import lk.elevenzcode.healthcare.appointmentapi.domain.Appointment;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.GenericService;

import java.util.List;

/**
 * @author Yasas Alwis
 */
public interface AppointmentService extends GenericService<Appointment> {
  void createAppointment(Appointment appointment);

  void updateAppointment(Appointment appointment);

  void deleteAppointment(Appointment appointment);

  Iterable<Appointment> findAll();

  List<Appointment> findByDoctor(int doctorId) throws ServiceException;

  List<Appointment> findByPatient(int patientId) throws ServiceException;
}
