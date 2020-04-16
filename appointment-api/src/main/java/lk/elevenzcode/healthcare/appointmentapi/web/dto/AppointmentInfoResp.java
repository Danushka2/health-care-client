package lk.elevenzcode.healthcare.appointmentapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.DoctorSessionInfo;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.PatientInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentInfoResp {
  private int id;
  private PatientInfo patient;
  private DoctorSessionInfo session;
  private LocalDate appointmentDate;
  private LocalDateTime createDate;
  private int status;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public PatientInfo getPatient() {
    return patient;
  }

  public void setPatient(PatientInfo patient) {
    this.patient = patient;
  }

  public DoctorSessionInfo getSession() {
    return session;
  }

  public void setSession(DoctorSessionInfo session) {
    this.session = session;
  }

  public LocalDate getAppointmentDate() {
    return appointmentDate;
  }

  public void setAppointmentDate(LocalDate appointmentDate) {
    this.appointmentDate = appointmentDate;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
