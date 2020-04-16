package lk.elevenzcode.healthcare.doctorapi.service.integration.impl;

import lk.elevenzcode.healthcare.doctorapi.service.integration.HospitalIntegrationService;
import lk.elevenzcode.healthcare.doctorapi.service.integration.dto.HospitalInfo;
import lk.elevenzcode.healthcare.doctorapi.service.integration.dto.RoomInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by හShaන් සNදීප on 3/22/2020 12:08 AM
 */
@Service
public class HospitalIntegrationServiceImpl implements HospitalIntegrationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(HospitalIntegrationServiceImpl
      .class);

  @Autowired
  private OAuth2RestTemplate oAuth2RestTemplate;

  @Value("${integ.service.hospital.getbyid.url}")
  private String getByIdUrl;

  @Value("${integ.service.hospital.room.getbyid.url}")
  private String getRoomByIdUrl;

  @Override
  public HospitalInfo getById(int id) {
    return oAuth2RestTemplate.getForEntity(getByIdUrl, HospitalInfo.class, id).getBody();
  }

  @Override
  public RoomInfo getByRoomId(int roomId) {
    return oAuth2RestTemplate.getForEntity(getRoomByIdUrl, RoomInfo.class, roomId).getBody();
  }
}
