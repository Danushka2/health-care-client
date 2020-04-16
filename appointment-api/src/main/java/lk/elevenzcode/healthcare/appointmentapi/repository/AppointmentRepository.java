package lk.elevenzcode.healthcare.appointmentapi.repository;

import lk.elevenzcode.healthcare.appointmentapi.domain.Appointment;
import lk.elevenzcode.healthcare.appointmentapi.domain.AppointmentStatus;
import lk.elevenzcode.healthcare.commons.repository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yasas Alwis
 */
@Repository
public interface AppointmentRepository extends GenericRepository<Appointment> {
  List<Appointment> findByPatientId(int patientId);
  List<Appointment> findBySessionId(int sessionId);
  List<Appointment> findByStatus(AppointmentStatus status);
}
