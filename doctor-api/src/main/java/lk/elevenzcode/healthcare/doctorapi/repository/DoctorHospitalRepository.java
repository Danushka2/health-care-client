package lk.elevenzcode.healthcare.doctorapi.repository;

import lk.elevenzcode.healthcare.commons.repository.GenericRepository;
import lk.elevenzcode.healthcare.doctorapi.domain.DoctorHospital;

public interface DoctorHospitalRepository extends GenericRepository<DoctorHospital> {
  DoctorHospital findByDoctorIdAndHospitalId(int doctorId, int hospitalId);
}
