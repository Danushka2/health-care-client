package lk.elevenzcode.healthcare.appointmentapi.service.integration.impl;

import lk.elevenzcode.healthcare.appointmentapi.service.integration.DoctorIntegrationService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.DoctorInfo;
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
 * Created by හShaන් සNදීප on 3/21/2020 3:45 PM
 */
@Service
public class DoctorIntegrationServiceImpl implements DoctorIntegrationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(DoctorIntegrationServiceImpl.class);

  @Autowired
  private OAuth2RestTemplate oAuth2RestTemplate;

  @Value("${integ.service.doctor.getbyid.url}")
  private String getByIdUrl;

  @Value("${integ.service.doctor.getall.url}")
  private String getAllUrl;

  @Override
  public DoctorInfo getById(int id) {
    return oAuth2RestTemplate.getForEntity(getByIdUrl, DoctorInfo.class, id).getBody();
  }

  @Override
  public List<DoctorInfo> getAll() {
    return oAuth2RestTemplate.exchange(getAllUrl, HttpMethod.GET, null,
        new ParameterizedTypeReference<List<DoctorInfo>>() {
        }).getBody();
  }
}
