package lk.elevenzcode.healthcare.hospitalapi.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HospitalInfoResp {
  private Integer id;
  private String name, address, email, fax, tel, type, status ;
  private String username, password;


  public HospitalInfoResp() {
  }

  public HospitalInfoResp(Integer id, String name, String address, String email, String fax,
                          String tel, String type, String status, String username,
                          String password) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.email = email;
    this.fax = fax;
    this.tel = tel;
    this.type = type;
    this.status = status;
    this.username = username;
    this.password = password;
  }

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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getUsername() { return username; }

  public void setUsername(String username) { this.username = username; }

  public String getPassword() { return password; }

  public void setPassword(String password) { this.password = password; }

  public String getType() { return type; }

  public void setType(String type) { this.type = type; }

  public String getStatus() { return status; }

  public void setStatus(String status) { this.status = status; }
}
