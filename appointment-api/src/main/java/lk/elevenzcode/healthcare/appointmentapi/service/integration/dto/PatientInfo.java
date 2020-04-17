package lk.elevenzcode.healthcare.appointmentapi.service.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lk.elevenzcode.healthcare.commons.util.JsonUtil;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:11 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientInfo {
  private Integer id;
  private String name, email;
  private short age;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public short getAge() {
    return age;
  }

  public void setAge(short age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return JsonUtil.toString(this);
  }
}
