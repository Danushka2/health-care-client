package lk.elevenzcode.healthcare.doctorapi.service.impl;

import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.doctorapi.domain.DoctorSession;
import lk.elevenzcode.healthcare.doctorapi.repository.DoctorSessionRepository;
import lk.elevenzcode.healthcare.doctorapi.service.DoctorSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DoctorSessionServiceImpl extends GenericServiceImpl<DoctorSession> implements DoctorSessionService {
  @Autowired
  private DoctorSessionRepository doctorSessionRepository;

  @PostConstruct
  private void init() {
    init(doctorSessionRepository);
  }
}
