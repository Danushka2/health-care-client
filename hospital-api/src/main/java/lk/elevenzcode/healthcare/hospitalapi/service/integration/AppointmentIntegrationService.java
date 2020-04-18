package lk.elevenzcode.healthcare.hospitalapi.service.integration;

import lk.elevenzcode.healthcare.hospitalapi.service.integration.dto.AppointmentInfo;

import java.util.List;


public interface AppointmentIntegrationService {
  List<AppointmentInfo> getByHospId(int hospId);
}
