package lk.elevenzcode.healthcare.hospitalapi.service.integration.impl;

import lk.elevenzcode.healthcare.commons.enums.UserType;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.integration.BaseIntegrationService;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.AuthIntegrationService;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.dto.UserInfo;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.dto.UserRegDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;


@Service
public class AuthIntegrationServiceImpl extends BaseIntegrationService implements AuthIntegrationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentIntegrationServiceImpl
      .class);

  @Autowired
  private OAuth2RestTemplate oAuth2RestTemplate;

  @Value("${integ.service.auth.user.getbyusername.url}")
  private String getByUsernameUrl;

  @Value("${integ.service.auth.user.register.url}")
  private String userRegUrl;

  @Override
  public UserInfo getByUsername(String username) throws ServiceException {
    try {
      return oAuth2RestTemplate.getForEntity(getByUsernameUrl, UserInfo.class, username).getBody();
    } catch (RestClientException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE,
          getMessage("label.err.integration.failed"));
    }
  }

  @Override
  public Integer registerUser(UserRegDto userRegDto) throws ServiceException {
    try {
      userRegDto.setUserType(UserType.HOSPITAL_ADMIN);
      return oAuth2RestTemplate.postForEntity(userRegUrl, userRegDto, Integer.class).getBody();
    } catch (RestClientException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE,
          getMessage("label.err.integration.failed"));
    }
  }
}
