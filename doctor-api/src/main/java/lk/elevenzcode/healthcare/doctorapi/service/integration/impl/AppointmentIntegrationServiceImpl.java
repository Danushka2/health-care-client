package lk.elevenzcode.healthcare.doctorapi.service.integration.impl;

import lk.elevenzcode.healthcare.doctorapi.service.integration.AppointmentIntegrationService;
import lk.elevenzcode.healthcare.doctorapi.service.integration.dto.AppointmentInfo;
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
 * Created by හShaන් සNදීප on 3/21/2020 11:47 PM
 */
@Service
public class AppointmentIntegrationServiceImpl implements AppointmentIntegrationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentIntegrationServiceImpl
      .class);

  @Autowired
  private OAuth2RestTemplate oAuth2RestTemplate;

  @Value("${integ.service.appointment.getbydocid.url}")
  private String getByDocIdUrl;

  @Override
  public List<AppointmentInfo> getByDocId(int docId) {
    return oAuth2RestTemplate.exchange(getByDocIdUrl, HttpMethod.GET, null,
        new ParameterizedTypeReference<List<AppointmentInfo>>() {
        }, docId).getBody();
  }
}
