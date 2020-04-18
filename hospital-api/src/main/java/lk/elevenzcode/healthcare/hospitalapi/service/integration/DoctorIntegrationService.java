package lk.elevenzcode.healthcare.hospitalapi.service.integration;

import lk.elevenzcode.healthcare.hospitalapi.service.integration.dto.DoctorInfo;

import java.util.List;


public interface DoctorIntegrationService {
  List<DoctorInfo> getByHospId(int hospId);
}
