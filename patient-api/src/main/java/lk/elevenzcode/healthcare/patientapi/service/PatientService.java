package lk.elevenzcode.healthcare.patientapi.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.GenericService;
import lk.elevenzcode.healthcare.patientapi.domain.Patient;
import lk.elevenzcode.healthcare.patientapi.web.dto.PatientRegisterDto;

import java.util.List;


public interface PatientService extends GenericService<Patient> {
  int register(PatientRegisterDto registerDto) throws ServiceException;

   Iterable<Patient> findAll();

 // List<Patient> findBypatientId(int patientID) throws ServiceException;

  public Patient getPatientById(Integer id) throws ServiceException;
}
