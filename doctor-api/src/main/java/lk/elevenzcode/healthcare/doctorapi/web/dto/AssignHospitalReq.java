package lk.elevenzcode.healthcare.doctorapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssignHospitalReq {
  private BigDecimal doctorFee;

  public BigDecimal getDoctorFee() {
    return doctorFee;
  }

  public void setDoctorFee(BigDecimal doctorFee) {
    this.doctorFee = doctorFee;
  }
}
