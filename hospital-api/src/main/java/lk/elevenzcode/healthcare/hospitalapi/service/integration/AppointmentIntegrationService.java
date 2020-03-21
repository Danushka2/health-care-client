package lk.elevenzcode.healthcare.hospitalapi.service.integration;

import lk.elevenzcode.healthcare.hospitalapi.service.integration.dto.AppointmentInfo;

import java.util.List;

/**
 * Created by හShaන් සNදීප on 3/22/2020 1:01 AM
 */
public interface AppointmentIntegrationService {
  List<AppointmentInfo> getByHospId(int hospId);
}
