package lk.elevenzcode.healthcare.appointmentapi.service;

import lk.elevenzcode.healthcare.appointmentapi.domain.Appointment;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.GenericService;

import java.util.List;

/**
 * @author Yasas Alwis
 */
public interface AppointmentService extends GenericService<Appointment> {

  void deleteAppointment(Appointment appointment);

  Iterable<Appointment> findAll();

  List<Appointment> findByPatient(int patientId) throws ServiceException;
  
  List<Appointment> findBySessionId(int sessionId) throws ServiceException;
}
