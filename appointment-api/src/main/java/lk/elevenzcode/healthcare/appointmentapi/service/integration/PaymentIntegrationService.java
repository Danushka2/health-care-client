package lk.elevenzcode.healthcare.appointmentapi.service.integration;

import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.PaymentInfo;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:47 PM
 */
public interface PaymentIntegrationService {
  PaymentInfo getByAppointmentId(int apptId);
}
