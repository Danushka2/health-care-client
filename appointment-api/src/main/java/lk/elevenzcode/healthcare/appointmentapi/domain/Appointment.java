package lk.elevenzcode.healthcare.appointmentapi.domain;

/**
 * @author Yasas Alwis
 */

import lk.elevenzcode.healthcare.commons.domain.BaseDomain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//Appointment class that store appointment details
@Entity // Javax persistence entity annotation
@Table(name = Appointment.TABLE_NAME)
public class Appointment extends BaseDomain {
	public static final String TABLE_NAME = "appointment";

	@Column(name = "session_id", nullable = false)
	private Integer sessionId;
	
	@Column(name = "patient_id", nullable = false)
	private Integer patientId; // Patient id, ID of the patient

	@Column(name = "appointment_date", nullable = false)
	private LocalDate appointmentDate; // Appointment date

	@Column(name = "create_datetime", nullable = false)
	private LocalDateTime createDate = LocalDateTime.now(); // Created time and date of appointment
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private AppointmentStatus status = new AppointmentStatus(AppointmentStatus.STATUS_PENDING);
	
	public Appointment() {
	}

	// Constructor for appointment class
	public Appointment(Integer sessionId, Integer patientId, LocalDate appointmentDate, LocalTime appointmentTime,
			LocalDateTime createDate) {
		super();
		this.sessionId = sessionId;
		this.patientId = patientId;
		this.appointmentDate = appointmentDate;
		this.createDate = createDate;
	}

	// ---------------------------------- Getter to get
	// data-----------------------------------------//
	public Integer getPatientId() {
		return patientId;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}
	
	public AppointmentStatus getStatus() {
		return status;
	}
	
	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public Integer getSessionId() {
		return sessionId;
	}
	// --------------------------- TO String method to get all
	// data----------------------------------//

	@Override
	public String toString() {
		return "Appointment [sessionId=" + sessionId + ", patientId=" + patientId + ", " +
				"appointmentDate="
				+ appointmentDate + ", createDate=" + createDate + ", status=" + status + "]";
	}
}
