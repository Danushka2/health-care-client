package lk.elevenzcode.healthcare.paymentapi.service.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lk.elevenzcode.healthcare.commons.serializer.JsonDateSerializer;
import lk.elevenzcode.healthcare.commons.serializer.JsonDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by හShaන් සNදීප on 3/27/2020 4:00 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentInfo {
  private int id;
  private PatientInfo patient;
  private DoctorSessionInfo session;
  private int status;
  @JsonSerialize(using = JsonDateSerializer.class)
  private LocalDate appointmentDate;
  @JsonSerialize(using = JsonDateTimeSerializer.class)
  private LocalDateTime createDate;

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

  public int getStatus() {
    return status;
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

  public void setStatus(int status) {
    this.status = status;
  }

  public enum Status {
    PENDING(1), COMPLETED(2), CANCELED(3);
    private final int id;

    Status(int id) {
      this.id = id;
    }

    public int getId() {
      return id;
    }
  }
}
