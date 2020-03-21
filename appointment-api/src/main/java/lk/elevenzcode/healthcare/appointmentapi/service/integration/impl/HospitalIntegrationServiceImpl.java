package lk.elevenzcode.healthcare.appointmentapi.service.integration.impl;

import lk.elevenzcode.healthcare.appointmentapi.service.integration.HospitalIntegrationService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.HospitalInfo;
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
 * Created by හShaන් සNදීප on 3/21/2020 9:44 PM
 */
@Service
public class HospitalIntegrationServiceImpl implements HospitalIntegrationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(HospitalIntegrationServiceImpl
      .class);

  @Autowired
  private OAuth2RestTemplate oAuth2RestTemplate;

  @Value("${integ.service.hospital.getbyid.url}")
  private String getByIdUrl;

  @Value("${integ.service.hospital.getall.url}")
  private String getAllUrl;

  @Override
  public HospitalInfo getById(int id) {
    return oAuth2RestTemplate.getForEntity(getByIdUrl, HospitalInfo.class, id).getBody();
  }

  @Override
  public List<HospitalInfo> getAll() {
    return oAuth2RestTemplate.exchange(getAllUrl, HttpMethod.GET, null,
        new ParameterizedTypeReference<List<HospitalInfo>>() {
        }).getBody();
  }
}
