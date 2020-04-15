package lk.elevenzcode.healthcare.authapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lk.elevenzcode.healthcare.commons.enums.UserType;

/**
 * Created by හShaන් සNදීප on 4/15/2020 8:46 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreationDto {
  private String username, password;
  private UserType userType;

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
