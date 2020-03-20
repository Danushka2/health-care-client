/**
 * 
 */
package lk.elevenzcode.healthcare.appointmentapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lk.elevenzcode.healthcare.appointmentapi.domain.Appointment;

/**
 * @author Yasas Alwis
 *
 */
@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long>{
	List<Appointment> findByPatientId(Long patientId);
	List<Appointment> findByDoctorId(Long doctorId);
}
