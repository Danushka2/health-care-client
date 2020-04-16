package lk.elevenzcode.healthcare.doctorapi.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.GenericService;
import lk.elevenzcode.healthcare.doctorapi.domain.DoctorHospital;

public interface DoctorHospitalService extends GenericService<DoctorHospital> {
  DoctorHospital get(int doctorId, int hospitalId) throws ServiceException;
}
