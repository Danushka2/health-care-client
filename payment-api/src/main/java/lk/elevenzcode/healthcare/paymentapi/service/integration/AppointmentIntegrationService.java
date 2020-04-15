package lk.elevenzcode.healthcare.paymentapi.service.integration;

import lk.elevenzcode.healthcare.paymentapi.service.integration.dto.AppointmentInfo;

/**
 * Created by හShaන් සNදීප on 3/27/2020 3:58 PM
 */
public interface AppointmentIntegrationService {
  AppointmentInfo getByApptId(int apptId);
}
