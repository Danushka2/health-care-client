package lk.elevenzcode.healthcare.patientapi.service.integration.impl;

import lk.elevenzcode.healthcare.patientapi.service.integration.AppointmentIntegrationService;
import lk.elevenzcode.healthcare.patientapi.service.integration.dto.AppointmentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by හShaන් සNදීප on 3/22/2020 1:11 AM
 */
@Service
public class AppointmentIntegrationServiceImpl implements AppointmentIntegrationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentIntegrationServiceImpl
      .class);

  @Autowired
  private OAuth2RestTemplate oAuth2RestTemplate;

  @Value("${integ.service.appointment.getbyptid.url}")
  private String getByPtIdUrl;

  @Override
  public List<AppointmentInfo> getByPtId(int ptId) {
    return oAuth2RestTemplate.exchange(getByPtIdUrl, HttpMethod.GET, null,
        new ParameterizedTypeReference<List<AppointmentInfo>>() {
        }, ptId).getBody();
  }
}
