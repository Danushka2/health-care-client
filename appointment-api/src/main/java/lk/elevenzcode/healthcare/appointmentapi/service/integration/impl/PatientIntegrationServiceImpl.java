package lk.elevenzcode.healthcare.appointmentapi.service.integration.impl;

import lk.elevenzcode.healthcare.appointmentapi.service.integration.PatientIntegrationService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.PatientInfo;
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
 * Created by හShaන් සNදීප on 3/21/2020 10:12 PM
 */
@Service
public class PatientIntegrationServiceImpl implements PatientIntegrationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PatientIntegrationServiceImpl.class);

  @Autowired
  private OAuth2RestTemplate oAuth2RestTemplate;

  @Value("${integ.service.patient.getbyid.url}")
  private String getByIdUrl;

  @Value("${integ.service.patient.getall.url}")
  private String getAllUrl;

  @Override
  public PatientInfo getById(int id) {
    return oAuth2RestTemplate.getForEntity(getByIdUrl, PatientInfo.class, id).getBody();
  }

  @Override
  public List<PatientInfo> getAll() {
    return oAuth2RestTemplate.exchange(getAllUrl, HttpMethod.GET, null,
        new ParameterizedTypeReference<List<PatientInfo>>() {
        }).getBody();
  }
}
