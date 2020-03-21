package lk.elevenzcode.healthcare.doctorapi.service.integration;

import lk.elevenzcode.healthcare.doctorapi.service.integration.dto.AppointmentInfo;

import java.util.List;

/**
 * Created by හShaන් සNදීප on 3/21/2020 11:47 PM
 */
public interface AppointmentIntegrationService {
  List<AppointmentInfo> getByDocId(int docId);
}
