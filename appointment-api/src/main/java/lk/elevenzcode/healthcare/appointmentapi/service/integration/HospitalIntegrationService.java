package lk.elevenzcode.healthcare.appointmentapi.service.integration;

import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.HospitalInfo;

import java.util.List;

/**
 * Created by හShaන් සNදීප on 3/21/2020 9:44 PM
 */
public interface HospitalIntegrationService {
  HospitalInfo getById(int id);

  List<HospitalInfo> getAll();
}
