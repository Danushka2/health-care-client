package lk.elevenzcode.healthcare.patientapi.web.dto;

/**
 * Created by Asus-pc on 4/15/2020 6:16 PM
 */
public class PatientRegisterDto {
  private String name, email, phoneNo,user_name,password;
  private short age;

  public String getUser_name() {
    return user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public String getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }

  public short getAge() {
    return age;
  }

  public void setAge(short age) {
    this.age = age;
  }
}
