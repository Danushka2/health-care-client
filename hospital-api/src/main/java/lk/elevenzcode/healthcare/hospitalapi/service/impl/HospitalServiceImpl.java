package lk.elevenzcode.healthcare.hospitalapi.service.impl;

import lk.elevenzcode.healthcare.commons.enums.UserType;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.commons.web.service.dto.ServiceResponse;
import lk.elevenzcode.healthcare.hospitalapi.domain.Hospital;
import lk.elevenzcode.healthcare.hospitalapi.repository.HospitalRepository;
import lk.elevenzcode.healthcare.hospitalapi.service.HospitalService;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.AuthIntegrationService;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.dto.UserRegDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class HospitalServiceImpl extends GenericServiceImpl<Hospital> implements HospitalService {
  @Autowired
  private HospitalRepository hospitalRepository;

  @Autowired
  private AuthIntegrationService authIntegrationService;

  @PostConstruct
  void init() {
    init(hospitalRepository);
  }

  public void createHospital(Hospital hospital) {
    hospitalRepository.save(hospital);
  }

  public void deleteHospital(int id) {
    hospitalRepository.deleteById(id);
  }

  public void updateHospital(Hospital hospital) {
    hospitalRepository.save(hospital);
  }

  @Override
  public Integer registerUser(String username, String Password) throws ServiceException {
    //create user account
    final ServiceResponse<Integer> userRegisterResponse =
        authIntegrationService.registerUser(new UserRegDto(username, Password,
            UserType.HOSPITAL_ADMIN));
    if (!userRegisterResponse.getHasError() || StringUtils.isEmpty(userRegisterResponse.getError())) {
      return userRegisterResponse.getBody();
    } else {
      throw new ServiceException(ServiceException.PROCESSING_FAILURE,
          userRegisterResponse.getError());
    }
  }
}
