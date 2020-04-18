package lk.elevenzcode.healthcare.hospitalapi.service.integration.impl;

import lk.elevenzcode.healthcare.hospitalapi.service.integration.DoctorIntegrationService;
import lk.elevenzcode.healthcare.hospitalapi.service.integration.dto.DoctorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DoctorIntegrationServiceImpl implements DoctorIntegrationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(DoctorIntegrationServiceImpl.class);

  @Autowired
  private OAuth2RestTemplate oAuth2RestTemplate;

  @Value("${integ.service.doctor.getbyhospid.url}")
  private String getByHospIdUrl;

  @Override
  public List<DoctorInfo> getByHospId(int hospId) {
    return oAuth2RestTemplate.exchange(getByHospIdUrl, HttpMethod.GET, null,
        new ParameterizedTypeReference<List<DoctorInfo>>() {
        }, hospId).getBody();
  }
}
