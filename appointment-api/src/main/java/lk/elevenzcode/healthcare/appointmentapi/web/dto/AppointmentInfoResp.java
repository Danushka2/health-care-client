package lk.elevenzcode.healthcare.appointmentapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lk.elevenzcode.healthcare.appointmentapi.domain.Appointment;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.DoctorIntegrationService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.PatientIntegrationService;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.DoctorSessionInfo;
import lk.elevenzcode.healthcare.appointmentapi.service.integration.dto.PatientInfo;
import lk.elevenzcode.healthcare.commons.serializer.JsonDateSerializer;
import lk.elevenzcode.healthcare.commons.serializer.JsonDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentInfoResp {
  private int id;
  private PatientInfo patient;
  private DoctorSessionInfo session;
  @JsonSerialize(using = JsonDateSerializer.class)
  private LocalDate appointmentDate;
  @JsonSerialize(using = JsonDateTimeSerializer.class)
  private LocalDateTime createDate;
  private int status;

  public static AppointmentInfoResp parse(Appointment appointment,
                                          PatientIntegrationService patientIntegrationService,
                                          DoctorIntegrationService doctorIntegService) {
    final AppointmentInfoResp appointmentInfoResp = new AppointmentInfoResp();
    appointmentInfoResp.setId(appointment.getId());
    appointmentInfoResp.setPatient(patientIntegrationService.getById(appointment.getPatientId()));
    appointmentInfoResp.setSession(doctorIntegService.getSessionInfo(appointment.getSessionId()));
    appointmentInfoResp.setAppointmentDate(appointment.getAppointmentDate());
    appointmentInfoResp.setCreateDate(appointment.getCreateDate());
    appointmentInfoResp.setStatus(appointment.getStatus().getId());
    return appointmentInfoResp;
  }

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
