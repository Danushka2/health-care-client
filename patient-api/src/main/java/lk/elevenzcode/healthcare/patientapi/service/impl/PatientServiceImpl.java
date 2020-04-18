package lk.elevenzcode.healthcare.patientapi.service.impl;

import lk.elevenzcode.healthcare.commons.enums.UserType;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.commons.util.Constant;
import lk.elevenzcode.healthcare.patientapi.domain.Patient;
import lk.elevenzcode.healthcare.patientapi.domain.PatientStatus;
import lk.elevenzcode.healthcare.patientapi.repository.PatientRepository;
import lk.elevenzcode.healthcare.patientapi.service.PatientService;
import lk.elevenzcode.healthcare.patientapi.service.integration.AuthIntegrationService;
import lk.elevenzcode.healthcare.patientapi.service.integration.dto.UserRegDto;
import lk.elevenzcode.healthcare.patientapi.web.dto.PatientRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.annotation.PostConstruct;


@Service
public class PatientServiceImpl extends GenericServiceImpl<Patient> implements PatientService {
  @Autowired
  private PatientRepository patientRepository;

  @Autowired
  private AuthIntegrationService authIntegrationService;

  @PostConstruct
  void init() {
    init(patientRepository);
  }


  @Override
  public Iterable<Patient> findAll() {
    Iterable<Patient> patients = patientRepository.findAll();
    return patients;
  }


  @Override
  public Patient getPatientById(Integer id) throws ServiceException {
    Patient patient = patientRepository.findByIdAndStatusIdIsNot(id, PatientStatus.STATUS_DELETED);
    if (patient != null) {
      return patient;
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE,
          "label.patient.err.invalid.id");
    }
  }

  @Override
  public List<Patient> getAll() throws ServiceException {
    return patientRepository.findAllByStatusIdIsNot(PatientStatus.STATUS_DELETED);
  }

  @Override
  @Transactional(value = Constant.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
      rollbackFor = ServiceException.class)
  public int register(PatientRegisterDto registerDto) throws ServiceException {
    //create user account
    final Integer userId = authIntegrationService.registerUser(new UserRegDto(registerDto
        .getUser_name(), registerDto.getPassword(), UserType.PATIENT));

    final Patient patient = new Patient();
    patient.setName(registerDto.getName());
    patient.setEmail(registerDto.getEmail());
    patient.setPhoneNumber(registerDto.getPhoneNo());
    patient.setAge(registerDto.getAge());
    patient.setGender(registerDto.getGender());
    patient.setStatus(new PatientStatus(PatientStatus.STATUS_ACTIVE));
    patient.setUser_id(userId);
    insert(patient);

    return patient.getId();
  }


}
