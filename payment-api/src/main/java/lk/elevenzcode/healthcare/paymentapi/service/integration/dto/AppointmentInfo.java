package lk.elevenzcode.healthcare.paymentapi.service.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
  private LocalDate appointmentDate;
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
    private int id;

    Status(int id) {
      this.id = id;
    }

    public int getId() {
      return id;
    }
  }
}
