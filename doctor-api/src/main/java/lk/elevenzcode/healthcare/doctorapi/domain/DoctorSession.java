package lk.elevenzcode.healthcare.doctorapi.domain;

import lk.elevenzcode.healthcare.commons.domain.BaseDomain;

import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = DoctorSession.TABLE_NAME)
public class DoctorSession extends BaseDomain {
  public static final String TABLE_NAME = "doctor_session";
  public static final short STATUS_AVAILABLE = 1;
  public static final short STATUS_UNAVAILABLE = 2;

  @ManyToOne
  @JoinColumn(name = "doctor_id", nullable = false)
  private Doctor doctor;

  @Column(name = "room_id", nullable = false)
  private int roomId;

  @Column(name = "session_from", nullable = false)
  private LocalTime from;

  @Column(name = "session_to", nullable = false)
  private LocalTime to;

  @Column(name = "status", nullable = false)
  private short status = STATUS_AVAILABLE;

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
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

  public short getStatus() {
    return status;
  }

  public void setStatus(short status) {
    this.status = status;
  }


  public DoctorSession(Doctor doctor, int roomId, LocalTime from, LocalTime to, short status) {
    this.doctor = doctor;
    this.roomId = roomId;
    this.from = from;
    this.to = to;
    this.status = status;
  }

  public DoctorSession() {
  }
}
