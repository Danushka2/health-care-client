package lk.elevenzcode.healthcare.doctorapi.repository;

import lk.elevenzcode.healthcare.commons.repository.GenericRepository;
import lk.elevenzcode.healthcare.doctorapi.domain.DoctorHospital;

/**
 * Created by Aravinda on 4/15/2020 9:09 PM
 */
public interface DoctorHospitalRepository extends GenericRepository<DoctorHospital> {
  DoctorHospital findByDoctorIdAndHospitalId(int doctorId, int hospitalId);
}
