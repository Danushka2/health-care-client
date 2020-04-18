package lk.elevenzcode.healthcare.hospitalapi.service.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lk.elevenzcode.healthcare.commons.enums.UserType;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegDto {
  private String username, password;
  private UserType userType;

  public UserRegDto() {
  }

  public UserRegDto(String username, String password, UserType userType) {
    this.username = username;
    this.password = password;
    this.userType = userType;
  }

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

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }
}
