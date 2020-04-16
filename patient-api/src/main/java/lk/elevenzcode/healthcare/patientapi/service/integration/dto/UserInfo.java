package lk.elevenzcode.healthcare.patientapi.service.integration.dto;

/**
 * Created by හShaන් සNදීප on 4/15/2020 8:43 PM
 */
public class UserInfo {
  private int id;
  private String username, type;
  private boolean isEnabled;

  public UserInfo() {
  }

  public UserInfo(int id, String username, String type, boolean isEnabled) {
    this.id = id;
    this.username = username;
    this.type = type;
    this.isEnabled = isEnabled;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean enabled) {
    isEnabled = enabled;
  }
}
