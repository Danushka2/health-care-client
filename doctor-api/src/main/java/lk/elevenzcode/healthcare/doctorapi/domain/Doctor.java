package lk.elevenzcode.healthcare.doctorapi.domain;

import lk.elevenzcode.healthcare.commons.domain.BaseDomain;

import javax.persistence.Entity;
import javax.persistence.Table;


// TODO: 3/21/2020 update with neccessory attributes & annotations
@Entity
@Table(name = Doctor.TABLE_NAME)
public class Doctor extends BaseDomain {
  public static final String TABLE_NAME = "doctor";


  private String specialization;
  private String name;
  private String tel;
  private String email;
  private String status;
  private Integer user_id;


  public Doctor() {
  }

  public Doctor(Integer id) {
    super(id);
  }

  public Doctor(Integer id, String name) {
    super(id);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSpecialization() {
    return specialization;
  }

  public void setSpecialization(String specialization) {
    this.specialization = specialization;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }


}
