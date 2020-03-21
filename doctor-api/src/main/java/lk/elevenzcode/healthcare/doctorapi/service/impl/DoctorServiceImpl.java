package lk.elevenzcode.healthcare.doctorapi.service.impl;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.doctorapi.domain.Doctor;
import lk.elevenzcode.healthcare.doctorapi.repository.DoctorRepository;
import lk.elevenzcode.healthcare.doctorapi.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 * Created by හShaන් සNදීප on 3/21/2020 9:10 PM
 */
@Service
public class DoctorServiceImpl extends GenericServiceImpl<Doctor> implements DoctorService {
  @Autowired
  private DoctorRepository doctorRepository;

  @PostConstruct
  void init() {
    init(doctorRepository);
  }

  // TODO: 3/21/2020 these are mock methods, remove once you implement the data layer ==============
  @Override
  public Doctor get(Integer id) throws ServiceException {
    if (id == 1) {
      return new Doctor(1, "Aravinda");
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE,
          "label.doctor.err.invalid.id");
    }
  }

  @Override
  public List<Doctor> getAll() throws ServiceException {
    return Arrays.asList(new Doctor(1, "Aravinda"), new Doctor(2, "Hashan"));
  }
  //================================================================================================
}
