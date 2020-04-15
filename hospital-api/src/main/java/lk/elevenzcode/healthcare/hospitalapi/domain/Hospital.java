package lk.elevenzcode.healthcare.hospitalapi.domain;


import lk.elevenzcode.healthcare.commons.domain.BaseDomain;
import lk.elevenzcode.healthcare.hospitalapi.util.Constant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Hospital.TABLE_NAME)
public class Hospital extends BaseDomain {
  public static final String TABLE_NAME = "hospital";

  @Column(name = "h_name", length = Constant.NORMAL_LENGTH, nullable = false)
  private String hospitalName;

  @Column(name = "h_address", length = Constant.LONG_LENGTH, nullable = false)
  private String hospitalAddress; 

  @Column(name = "h_email", length = Constant.NORMAL_LENGTH, nullable = false)
  private String hospitalEmail; 

  @Column(name = "h_type", length = Constant.NORMAL_LENGTH, nullable = false)
  private String hospitalType;

  @Column(name = "h_status", length = Constant.NORMAL_LENGTH, nullable = false)
  private String hospitalStatus;

  @Column(name = "h_fax", length = Constant.NORMAL_LENGTH)
  private String hospitalFax;

  @Column(name = "h_tell", length = Constant.NORMAL_LENGTH, nullable = false)
  private String hospitalTell;

  @Column(name = "user_id", length = Constant.NORMAL_LENGTH, nullable = false)
  private Integer userId;

  public Hospital() {
  }

  public Hospital(Integer id, String name, String address, String email, String type, String fax,
                  String tell, String status, Integer userId) {
    super(id);
    this.hospitalName = name;
    this.hospitalAddress = address;
    this.hospitalEmail = email;
    this.hospitalType = type;
    this.hospitalFax = fax;
    this.hospitalTell = tell;
    this.hospitalStatus = status;
    this.userId = userId;
  }

  public String getHospitalName() {
    return hospitalName;
  }

  public void setHospitalName(String hospitalName) {
    this.hospitalName = hospitalName;
  }

  public String getHospitalAddress() {
    return hospitalAddress;
  }

  public void setHospitalAddress(String hospitalAddress) {
    this.hospitalAddress = hospitalAddress;
  }

  public String getHospitalEmail() {
    return hospitalEmail;
  }

  public void setHospitalEmail(String hospitalEmail) {
    this.hospitalEmail = hospitalEmail;
  }

  public String getHospitalType() {
    return hospitalType;
  }

  public void setHospitalType(String hospitalType) {
    this.hospitalType = hospitalType;
  }

  public String getHospitalStatus() {
    return hospitalStatus;
  }

  public void setHospitalStatus(String hospitalStatus) {
    this.hospitalStatus = hospitalStatus;
  }

  public String getHospitalFax() {
    return hospitalFax;
  }

  public void setHospitalFax(String hospitalFax) {
    this.hospitalFax = hospitalFax;
  }

  public String getHospitalTell() {
    return hospitalTell;
  }

  public void setHospitalTell(String hospitalTell) {
    this.hospitalTell = hospitalTell;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "Hospital{" +
        "hospitalName='" + hospitalName + '\'' +
        ", hospitalAddress='" + hospitalAddress + '\'' +
        ", hospitalEmail='" + hospitalEmail + '\'' +
        ", hospitalType='" + hospitalType + '\'' +
        ", hospitalStatus='" + hospitalStatus + '\'' +
        ", hospitalFax='" + hospitalFax + '\'' +
        ", hospitalTell='" + hospitalTell + '\'' +
        ", userId='" + userId + '\'' +
        '}';
  }
}
