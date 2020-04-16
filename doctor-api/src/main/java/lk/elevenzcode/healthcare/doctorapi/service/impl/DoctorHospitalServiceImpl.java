package lk.elevenzcode.healthcare.doctorapi.service.impl;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.doctorapi.domain.DoctorHospital;
import lk.elevenzcode.healthcare.doctorapi.repository.DoctorHospitalRepository;
import lk.elevenzcode.healthcare.doctorapi.service.DoctorHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Aravinda on 4/15/2020 9:15 PM
 */
@Service
public class DoctorHospitalServiceImpl extends GenericServiceImpl<DoctorHospital> implements DoctorHospitalService {
  @Autowired
  private DoctorHospitalRepository doctorHospitalRepository;


  @PostConstruct
  void init() {
    init(doctorHospitalRepository);
  }

  //insert doctor service layer

  public void insert(DoctorHospital domain, int doctorId, int hostpitleId) throws ServiceException {

    domain.setDoctor_id(doctorId);
    domain.setDoctor_fee(domain.getDoctor_fee());
    domain.setHospital_id(hostpitleId);
    doctorHospitalRepository.save(domain);
  }
}
