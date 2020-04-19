package lk.elevenzcode.healthcare.doctorapi.service.impl;

import lk.elevenzcode.healthcare.commons.enums.UserType;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.commons.util.Constant;
import lk.elevenzcode.healthcare.doctorapi.domain.Doctor;
import lk.elevenzcode.healthcare.doctorapi.repository.DoctorRepository;
import lk.elevenzcode.healthcare.doctorapi.service.DoctorService;
import lk.elevenzcode.healthcare.doctorapi.service.integration.AuthIntegrationService;
import lk.elevenzcode.healthcare.doctorapi.service.integration.dto.UserRegDto;
import lk.elevenzcode.healthcare.doctorapi.web.dto.DoctorRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.annotation.PostConstruct;


@Service
public class DoctorServiceImpl extends GenericServiceImpl<Doctor> implements DoctorService {
  @Autowired
  private DoctorRepository doctorRepository;

  @Autowired
  private AuthIntegrationService authIntegrationService;

  @PostConstruct
  void init() {
    init(doctorRepository);
  }

  // TODO: 3/21/2020 these are mock methods, remove once you implement the data layer ==============


  //insert doctor service layer
  @Override
  public int insert(Doctor domain) throws ServiceException {
    doctorRepository.save(domain);
    return 0;
  }


  //get all doctor service layer
  public List<Doctor> getAll() throws ServiceException {
    return (List<Doctor>) doctorRepository.findAll();
  }


  //get one doctor service layer
  public Doctor get(Integer id) throws ServiceException {
    return doctorRepository.findById(id).get();
  }


  //get update service layer
  public void update(Doctor domain, Doctor doc) throws ServiceException {

    doc.setSpecialization(domain.getSpecialization());
    doc.setName(domain.getName());
    doc.setTel(domain.getTel());
    doc.setEmail(domain.getEmail());


    doctorRepository.save(doc);
  }


  //delete one doctor service layer
  public void delete(Integer id) throws ServiceException {
    doctorRepository.deleteById(id);
  }


  //doctor register
  @Override
  @Transactional(value = Constant.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
      rollbackFor = ServiceException.class)
  public int register(DoctorRegistration registerDto) throws ServiceException {
    //create user account
    final Integer userId = authIntegrationService.registerUser(new UserRegDto(registerDto
        .getUsername(), registerDto.getPassword(), UserType.DOCTOR));

    final Doctor doctor = new Doctor();


    doctor.setSpecialization(registerDto.getSpecialization());
    doctor.setName(registerDto.getName());
    doctor.setTel(registerDto.getTel());
    doctor.setEmail(registerDto.getEmail());
    doctor.setStatus(registerDto.getStatus());
    doctor.setUser_id(userId);
    insert(doctor);


    return doctor.getId();


  }






















}
