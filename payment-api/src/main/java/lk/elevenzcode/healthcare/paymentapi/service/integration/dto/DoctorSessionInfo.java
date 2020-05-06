package lk.elevenzcode.healthcare.paymentapi.service.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lk.elevenzcode.healthcare.commons.serializer.JsonTimeSerializer;

import java.math.BigDecimal;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorSessionInfo {
  private DoctorInfo doctor;
  private RoomInfo room;
  private String from;
  private String to;
  private BigDecimal docFee;
  private short status;

  public DoctorInfo getDoctor() {
    return doctor;
  }

  public void setDoctor(DoctorInfo doctor) {
    this.doctor = doctor;
  }

  public RoomInfo getRoom() {
    return room;
  }

  public void setRoom(RoomInfo room) {
    this.room = room;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public BigDecimal getDocFee() {
    return docFee;
  }

  public void setDocFee(BigDecimal docFee) {
    this.docFee = docFee;
  }

  public short getStatus() {
    return status;
  }

  public void setStatus(short status) {
    this.status = status;
  }
}
