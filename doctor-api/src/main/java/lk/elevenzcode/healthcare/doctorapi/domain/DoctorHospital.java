package lk.elevenzcode.healthcare.doctorapi.domain;


import lk.elevenzcode.healthcare.commons.domain.BaseDomain;
import lk.elevenzcode.healthcare.commons.util.Constant;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Table(name = DoctorHospital.TABLE_NAME)
public class DoctorHospital extends BaseDomain {
  public static final String TABLE_NAME = "doctor_hospital";

  @ManyToOne
  @JoinColumn(name = "doctor_id", nullable = false)
  private Doctor doctor;

  @Column(name = "hospital_id", nullable = false)
  private int hospitalId;

  @Column(name = "doctor_free", nullable = false)
  @Digits(integer = Constant.DEFAULT_MONEY_INT, fraction = Constant.DEFAULT_MONEY_FRAC)
  private BigDecimal doctorFee;

  public DoctorHospital() {
  }

  public DoctorHospital(Doctor doctor, int hospitalId, BigDecimal doctorFee) {
    this.doctor = doctor;
    this.hospitalId = hospitalId;
    this.doctorFee = doctorFee;
  }

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  public int getHospitalId() {
    return hospitalId;
  }

  public void setHospitalId(int hospitalId) {
    this.hospitalId = hospitalId;
  }

  public BigDecimal getDoctorFee() {
    return doctorFee;
  }

  public void setDoctorFee(BigDecimal doctorFee) {
    this.doctorFee = doctorFee;
  }
}
