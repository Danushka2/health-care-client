package lk.elevenzcode.healthcare.appointmentapi.service.integration;

import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.PatientInfo;

import java.util.List;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:10 PM
 */
public interface PatientIntegrationService {
  PatientInfo getById(int id);

  List<PatientInfo> getAll();
}
