package lk.elevenzcode.healthcare.patientapi.repository;

import lk.elevenzcode.healthcare.commons.repository.GenericRepository;
import lk.elevenzcode.healthcare.patientapi.domain.Patient;

import java.util.List;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:30 PM
 */
public interface PatientRepository extends GenericRepository<Patient> {
  List<Patient> findAllByStatusIsNot(int status);

  public Patient findAllById(Integer id);
}
