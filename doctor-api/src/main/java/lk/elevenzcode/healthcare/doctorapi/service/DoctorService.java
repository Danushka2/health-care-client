package lk.elevenzcode.healthcare.doctorapi.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.GenericService;
import lk.elevenzcode.healthcare.doctorapi.domain.Doctor;
import lk.elevenzcode.healthcare.doctorapi.web.dto.DoctorRegistration;


public interface DoctorService extends GenericService<Doctor> {
  int register(DoctorRegistration registerDto) throws ServiceException;
}
