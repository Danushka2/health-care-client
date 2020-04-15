package lk.elevenzcode.healthcare.patientapi.service.impl;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.patientapi.domain.Patient;
import lk.elevenzcode.healthcare.patientapi.domain.PatientStatus;
import lk.elevenzcode.healthcare.patientapi.repository.PatientRepository;
import lk.elevenzcode.healthcare.patientapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;


@Service
public class PatientServiceImpl extends GenericServiceImpl<Patient> implements PatientService {
  @Autowired
  private PatientRepository patientRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @PostConstruct
  void init() {
    init(patientRepository);
  }

  /*@Override
  public Patient get(Integer id) throws ServiceException {
    Patient patient = patientRepository.
    if (patient != null) {
      return patient;
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE,
          "label.patient.err.invalid.id");
    }
  }*/

  @Override
  public List<Patient> getAll() throws ServiceException {
    return patientRepository.findAllByStatusIsNot(PatientStatus.STATUS_DELETED);
  }
}
