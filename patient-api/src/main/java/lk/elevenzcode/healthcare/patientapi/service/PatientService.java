package lk.elevenzcode.healthcare.patientapi.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.GenericService;
import lk.elevenzcode.healthcare.patientapi.domain.Patient;
import lk.elevenzcode.healthcare.patientapi.web.dto.PatientRegisterDto;
import lk.elevenzcode.healthcare.patientapi.web.dto.PatientUpdateDto;

import java.util.List;


public interface PatientService extends GenericService<Patient> {
  int register(PatientRegisterDto registerDto) throws ServiceException;


   Iterable<Patient> findAll();


  public Patient getPatientById(Integer id) throws ServiceException;
}
