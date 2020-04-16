package lk.elevenzcode.healthcare.doctorapi.service.impl;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import lk.elevenzcode.healthcare.doctorapi.domain.Doctor;
import lk.elevenzcode.healthcare.doctorapi.repository.DoctorRepository;
import lk.elevenzcode.healthcare.doctorapi.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.annotation.PostConstruct;


@Service
public class DoctorServiceImpl extends GenericServiceImpl<Doctor> implements DoctorService {
  @Autowired
  private DoctorRepository doctorRepository;

  @PostConstruct
  void init() {
    init(doctorRepository);
  }

  // TODO: 3/21/2020 these are mock methods, remove once you implement the data layer ==============


  //insert doctor service layer
  @Override
  public void insert(Doctor domain) throws ServiceException {
    doctorRepository.save(domain);
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


}
