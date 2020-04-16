package lk.elevenzcode.healthcare.paymentapi.service.integration.impl;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.integration.BaseIntegrationService;
import lk.elevenzcode.healthcare.paymentapi.service.integration.AuthIntegrationService;
import lk.elevenzcode.healthcare.paymentapi.service.integration.dto.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

/**
 * Created by හShaන් සNදීප on 4/16/2020 9:03 AM
 */
@Service
public class AuthIntegrationServiceImpl extends BaseIntegrationService implements AuthIntegrationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentIntegrationServiceImpl
      .class);

  @Autowired
  private OAuth2RestTemplate oAuth2RestTemplate;

  @Value("${integ.service.auth.user.getbyusername.url}")
  private String getByUsernameUrl;

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
}
