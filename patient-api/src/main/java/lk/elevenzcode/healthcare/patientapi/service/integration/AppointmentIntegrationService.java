package lk.elevenzcode.healthcare.patientapi.service.integration;

import lk.elevenzcode.healthcare.patientapi.service.integration.dto.AppointmentInfo;

import java.util.List;


public interface AppointmentIntegrationService {
  List<AppointmentInfo> getByPtId(int ptId);
}
