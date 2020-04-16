package lk.elevenzcode.healthcare.doctorapi.domain;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * Created by Aravinda on 4/15/2020 7:17 PM
 */

@Entity
@Table(name = DoctorHospital.TABLE_NAME)
@IdClass(DoctorHospital.DoctorHospitalID.class)

public class DoctorHospital implements Serializable {
  public static final String TABLE_NAME = "doctor_hospital";

  @Id
  @JoinColumn(name = "doctor_id", nullable = false)
  private Integer doctor_id;


  @Id
  @Column(name = "hospital_id", nullable = false, length = 50)
  private Integer hospital_id;

  private double doctor_fee;

  public Integer getDoctor_id() {
    return doctor_id;
  }

  public void setDoctor_id(Integer doctor_id) {
    this.doctor_id = doctor_id;
  }

  public Integer getHospital_id() {
    return hospital_id;
  }

  public void setHospital_id(Integer hospital_id) {
    this.hospital_id = hospital_id;
  }

  public double getDoctor_fee() {
    return doctor_fee;
  }

  public void setDoctor_fee(double doctor_fee) {
    this.doctor_fee = doctor_fee;
  }


  public static class DoctorHospitalID implements Serializable {

    private Integer doctor_id;
    private Integer hospital_id;

    public DoctorHospitalID() {
    }

    public DoctorHospitalID(Integer doctor_id, Integer hospital_id) {
      this.doctor_id = doctor_id;
      this.hospital_id = hospital_id;
    }


    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      DoctorHospitalID that = (DoctorHospitalID) o;

      if (!doctor_id.equals(that.doctor_id)) {
        return false;
      }
      return hospital_id.equals(that.hospital_id);
    }

    @Override
    public int hashCode() {
      int result = doctor_id.hashCode();
      result = 31 * result + hospital_id.hashCode();
      return result;
    }
  }


}