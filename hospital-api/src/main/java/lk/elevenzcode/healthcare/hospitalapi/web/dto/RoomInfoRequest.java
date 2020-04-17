package lk.elevenzcode.healthcare.hospitalapi.web.dto;

import java.math.BigDecimal;

public class RoomInfoRequest {
  private Integer roomId, hospitalId;
  private String roomNo, location;
  private BigDecimal roomFee;
  private Short status;

  public RoomInfoRequest(Integer roomId, Integer hospitalId, String roomNo, String location,
                         BigDecimal roomFee,
                         Short status) {
    this.roomId = roomId;
    this.hospitalId = hospitalId;
    this.roomNo = roomNo;
    this.location = location;
    this.roomFee = roomFee;
    this.status = status;
  }

  public Integer getRoomId() {
    return roomId;
  }

  public void setRoomId(Integer roomId) {
    this.roomId = roomId;
  }

  public Integer getHospitalId() {
    return hospitalId;
  }

  public void setHospitalId(Integer hospitalId) {
    this.hospitalId = hospitalId;
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

  public Short getStatus() {
    return status;
  }

  public void setStatus(Short status) {
    this.status = status;
  }
}
