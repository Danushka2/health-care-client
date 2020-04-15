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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.annotations.Type;

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

	@Column(name = "appointment_time", nullable = false)
	private LocalTime appointmentTime; // Appointment Time

	@Column(name = "create_datetime", nullable = false)
	private LocalDateTime createDate; // Created time and date of appointment
	
	@ManyToOne
	@JoinColumn
	private AppointmentStatus status;
	
	public Appointment() {
	}

	// Constructor for appointment class
	public Appointment(Integer sessionId, Integer patientId, LocalDate appointmentDate, LocalTime appointmentTime,
			LocalDateTime createDate) {
		super();
		this.sessionId = sessionId;
		this.patientId = patientId;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.createDate = createDate;
		this.status = new AppointmentStatus(AppointmentStatus.STATUS_PENDING);
	}

	// ---------------------------------- Getter to get
	// data-----------------------------------------//
	public Integer getPatientId() {
		return patientId;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public LocalTime getAppointmentTime() {
		return appointmentTime;
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
		return "Appointment [sessionId=" + sessionId + ", patientId=" + patientId + ", appointmentDate="
				+ appointmentDate + ", appointmentTime=" + appointmentTime + ", createDate=" + createDate + ", status="
				+ status + "]";
	}
}
