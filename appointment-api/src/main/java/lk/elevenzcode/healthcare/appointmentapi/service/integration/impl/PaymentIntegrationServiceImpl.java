package lk.elevenzcode.healthcare.appointmentapi.service.integration.impl;

import lk.elevenzcode.healthcare.appointmentapi.service.integration.PaymentIntegrationService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.PaymentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:47 PM
 */
@Service
public class PaymentIntegrationServiceImpl implements PaymentIntegrationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PaymentIntegrationServiceImpl.class);

  @Autowired
  private OAuth2RestTemplate oAuth2RestTemplate;

  @Value("${integ.service.payment.getbyapptid.url}")
  private String getByApptIdUrl;

  @Override
  public PaymentInfo getByAppointmentId(int apptId) {
    return oAuth2RestTemplate.getForEntity(getByApptIdUrl, PaymentInfo.class, apptId).getBody();
  }
}
