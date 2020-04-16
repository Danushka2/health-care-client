package lk.elevenzcode.healthcare.paymentapi.service.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomInfo {
  private int id;
  private HospitalInfo hospital;
  private String roomNo, location;
  private BigDecimal roomFee;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public HospitalInfo getHospital() {
    return hospital;
  }

  public void setHospital(HospitalInfo hospital) {
    this.hospital = hospital;
  }

  public String getRoomNo() {
    return roomNo;
  }

  public void setRoomNo(String roomNo) {
    this.roomNo = roomNo;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public BigDecimal getRoomFee() {
    return roomFee;
  }

  public void setRoomFee(BigDecimal roomFee) {
    this.roomFee = roomFee;
  }
}
