package lk.elevenzcode.healthcare.hospitalapi.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomInfoResp {
  private int id;
  private HospitalInfoResp hospital;
  private String roomNo, location;
  private BigDecimal roomFee;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public HospitalInfoResp getHospital() {
    return hospital;
  }

  public void setHospital(HospitalInfoResp hospital) {
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
