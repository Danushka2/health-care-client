package lk.elevenzcode.healthcare.paymentapi.service.integration.impl;


import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.integration.BaseIntegrationService;
import lk.elevenzcode.healthcare.paymentapi.service.integration.AppointmentIntegrationService;
import lk.elevenzcode.healthcare.paymentapi.service.integration.dto.AppointmentInfo;
import lk.elevenzcode.healthcare.paymentapi.service.integration.dto.AppointmentUpdateReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

/**
 * Created by හShaන් සNදීප on 3/27/2020 3:58 PM
 */
@Service
public class AppointmentIntegrationServiceImpl extends BaseIntegrationService implements AppointmentIntegrationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentIntegrationServiceImpl
      .class);

  @Autowired
  private OAuth2RestTemplate oAuth2RestTemplate;

  @Value("${integ.service.appointment.getbyid.url}")
  private String getByIdUrl;

  @Value("${integ.service.appointment.update.url}")
  private String updateUrl;

  @Override
  public AppointmentInfo getByApptId(int apptId) throws ServiceException {
    try {
      return oAuth2RestTemplate.getForEntity(getByIdUrl, AppointmentInfo.class, apptId).getBody();
    } catch (RestClientException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE,
          getMessage("label.err.integration.failed"));
    }
  }

  @Override
  public void updateStatus(int apptId, AppointmentUpdateReq req) throws ServiceException {
    try {
      oAuth2RestTemplate.put(updateUrl, req, apptId);
    } catch (RestClientException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE,
          getMessage("label.err.integration.failed"));
    }
  }
}
