package lk.elevenzcode.healthcare.doctorapi.web.dto;

import lk.elevenzcode.healthcare.doctorapi.domain.Doctor;

/**
 * Created by Aravinda on 4/19/2020 12:14 PM
 */
public class DoctorRegistration extends Doctor {

  private String username, password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
