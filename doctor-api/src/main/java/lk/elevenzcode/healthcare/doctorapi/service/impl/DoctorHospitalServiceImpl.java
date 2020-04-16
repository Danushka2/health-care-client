package lk.elevenzcode.healthcare.doctorapi.service.impl;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.doctorapi.domain.DoctorHospital;
import lk.elevenzcode.healthcare.doctorapi.repository.DoctorHospitalRepository;
import lk.elevenzcode.healthcare.doctorapi.service.DoctorHospitalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DoctorHospitalServiceImpl extends GenericServiceImpl<DoctorHospital> implements DoctorHospitalService {
  private static final Logger LOGGER = LoggerFactory.getLogger(DoctorHospitalServiceImpl.class);

  @Autowired
  private DoctorHospitalRepository doctorHospitalRepository;

  @PostConstruct
  private void init() {
    init(doctorHospitalRepository);
  }

  @Override
  public DoctorHospital get(int doctorId, int hospitalId) throws ServiceException {
    try {
      return doctorHospitalRepository.findByDoctorIdAndHospitalId(doctorId, hospitalId);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE, e.getMessage(), e.getCause());
    }
  }
}
