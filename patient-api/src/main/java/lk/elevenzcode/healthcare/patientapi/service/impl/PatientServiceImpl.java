package lk.elevenzcode.healthcare.patientapi.service.impl;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.patientapi.domain.Patient;
import lk.elevenzcode.healthcare.patientapi.repository.PatientRepository;
import lk.elevenzcode.healthcare.patientapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:25 PM
 */
@Service
public class PatientServiceImpl extends GenericServiceImpl<Patient> implements PatientService {
  @Autowired
  private PatientRepository patientRepository;

  @PostConstruct
  void init() {
    init(patientRepository);
  }

  // TODO: 3/21/2020 these are mock methods, remove once you implement the data layer ==============
  @Override
  public Patient get(Integer id) throws ServiceException {
    if (id == 1) {
      return new Patient(1, "Dilshan");
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE,
          "label.patient.err.invalid.id");
    }
  }

  @Override
  public List<Patient> getAll() throws ServiceException {
    return Arrays.asList(new Patient(1, "Dilshan"),
        new Patient(2, "Danushka"));
  }
  //================================================================================================
}
