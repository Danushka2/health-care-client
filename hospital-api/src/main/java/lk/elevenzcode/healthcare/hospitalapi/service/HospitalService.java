package lk.elevenzcode.healthcare.hospitalapi.service;

import lk.elevenzcode.healthcare.commons.service.GenericService;
import lk.elevenzcode.healthcare.hospitalapi.domain.Hospital;

public interface HospitalService extends GenericService<Hospital> {
  public void deleteHospital(int id);
}
