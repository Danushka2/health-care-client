package lk.elevenzcode.healthcare.hospitalapi.service.integration;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.dto.UserInfo;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.dto.UserRegDto;


public interface AuthIntegrationService {
  UserInfo getByUsername(String username) throws ServiceException;

  Integer registerUser(UserRegDto userRegDto) throws ServiceException;
}
