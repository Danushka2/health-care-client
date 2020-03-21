package lk.elevenzcode.healthcare.patientapi.service.integration;

import lk.elevenzcode.healthcare.patientapi.service.integration.dto.AppointmentInfo;

import java.util.List;

/**
 * Created by හShaන් සNදීප on 3/22/2020 1:11 AM
 */
public interface AppointmentIntegrationService {
  List<AppointmentInfo> getByPtId(int ptId);
}
