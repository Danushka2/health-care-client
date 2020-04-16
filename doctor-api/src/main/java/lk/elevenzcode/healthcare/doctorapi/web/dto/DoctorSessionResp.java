package lk.elevenzcode.healthcare.doctorapi.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lk.elevenzcode.healthcare.doctorapi.domain.Doctor;
import lk.elevenzcode.healthcare.doctorapi.service.integration.dto.RoomInfo;

import java.math.BigDecimal;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorSessionResp {
  private Doctor doctor;
  private RoomInfo room;
  private LocalTime from;
  private LocalTime to;
  private BigDecimal docFee;
  private short status;

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  public RoomInfo getRoom() {
    return room;
  }

  public void setRoom(RoomInfo room) {
    this.room = room;
  }

  public LocalTime getFrom() {
    return from;
  }

  public void setFrom(LocalTime from) {
    this.from = from;
  }

  public LocalTime getTo() {
    return to;
  }

  public void setTo(LocalTime to) {
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
