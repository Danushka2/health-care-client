package lk.elevenzcode.healthcare.hospitalapi.service.integration;

import lk.elevenzcode.healthcare.hospitalapi.service.integration.dto.DoctorInfo;

import java.util.List;

/**
 * Created by හShaන් සNදීප on 3/22/2020 12:50 AM
 */
public interface DoctorIntegrationService {
  List<DoctorInfo> getByHospId(int hospId);
}
