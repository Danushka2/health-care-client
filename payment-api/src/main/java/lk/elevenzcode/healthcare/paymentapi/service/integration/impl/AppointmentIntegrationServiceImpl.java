package lk.elevenzcode.healthcare.paymentapi.service.integration.impl;


import lk.elevenzcode.healthcare.paymentapi.service.integration.AppointmentIntegrationService;
import lk.elevenzcode.healthcare.paymentapi.service.integration.dto.AppointmentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by හShaන් සNදීප on 3/27/2020 3:58 PM
 */
@Service
public class AppointmentIntegrationServiceImpl implements AppointmentIntegrationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentIntegrationServiceImpl
      .class);

  @Autowired
  private OAuth2RestTemplate oAuth2RestTemplate;

  @Value("${integ.service.appointment.getbyid.url}")
  private String getByIdUrl;

  @Override
  public AppointmentInfo getByApptId(int apptId) {
    return oAuth2RestTemplate.getForEntity(getByIdUrl, AppointmentInfo.class, apptId).getBody();
  }
}
