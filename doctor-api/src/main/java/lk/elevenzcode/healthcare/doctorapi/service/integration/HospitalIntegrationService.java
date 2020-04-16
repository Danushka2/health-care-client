package lk.elevenzcode.healthcare.doctorapi.service.integration;

import lk.elevenzcode.healthcare.doctorapi.service.integration.dto.HospitalInfo;
import lk.elevenzcode.healthcare.doctorapi.service.integration.dto.RoomInfo;

/**
 * Created by හShaන් සNදීප on 3/22/2020 12:08 AM
 */
public interface HospitalIntegrationService {
  HospitalInfo getById(int id);

  RoomInfo getByRoomId(int roomId);
}
