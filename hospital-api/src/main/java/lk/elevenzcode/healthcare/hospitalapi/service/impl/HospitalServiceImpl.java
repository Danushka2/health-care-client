package lk.elevenzcode.healthcare.hospitalapi.service.impl;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.hospitalapi.domain.Hospital;
import lk.elevenzcode.healthcare.hospitalapi.repository.HospitalRepository;
import lk.elevenzcode.healthcare.hospitalapi.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 * Created by හShaන් සNදීප on 3/21/2020 9:56 PM
 */
@Service
public class HospitalServiceImpl extends GenericServiceImpl<Hospital> implements HospitalService {
  @Autowired
  private HospitalRepository hospitalRepository;

  @PostConstruct
  void init() {
    init(hospitalRepository);
  }

  // TODO: 3/21/2020 these are mock methods, remove once you implement the data layer ==============
  @Override
  public Hospital get(Integer id) throws ServiceException {
    if (id == 1) {
      return new Hospital(1, "Lanka Hospital");
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE,
          "label.hospital.err.invalid.id");
    }
  }

  @Override
  public List<Hospital> getAll() throws ServiceException {
    return Arrays.asList(new Hospital(1, "Lanka Hospital"),
        new Hospital(2, "Nawaloka Hospital"));
  }
  //================================================================================================
}
