package lk.elevenzcode.healthcare.patientapi.service.integration;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.web.service.dto.ServiceResponse;
import lk.elevenzcode.healthcare.patientapi.service.integration.dto.UserInfo;
import lk.elevenzcode.healthcare.patientapi.service.integration.dto.UserRegDto;

/**
 * Created by හShaන් සNදීප on 4/16/2020 9:03 AM
 */
public interface AuthIntegrationService {
  UserInfo getByUsername(String username) throws ServiceException;

  ServiceResponse<Integer> registerUser(UserRegDto userRegDto) throws ServiceException;
}
