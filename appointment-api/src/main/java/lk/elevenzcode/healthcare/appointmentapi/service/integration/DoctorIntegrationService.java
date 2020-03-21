package lk.elevenzcode.healthcare.appointmentapi.service.integration;

import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.DoctorInfo;

import java.util.List;

/**
 * Created by හShaන් සNදීප on 3/21/2020 3:44 PM
 */
public interface DoctorIntegrationService {
  DoctorInfo getById(int id);

  List<DoctorInfo> getAll();
}
