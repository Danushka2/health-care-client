package lk.elevenzcode.healthcare.appointmentapi.domain;

/**
 * @author Yasas Alwis
 *
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//Appointment class that store appointment details
@Entity //Javax persistence entity annotation
@Table(name = "appointment")
public class Appointment {
	
	@Id //annotation will tell to the compiler, this is the primary key of the table
	@GeneratedValue(strategy = GenerationType.IDENTITY) //this annotation will tell the compiler this attribute is a auto increment value
	private long appointmentid; //Appointment id Auto incremented
	
	private long doctorId; //Doctor id that patient is going to channel
	
	private long patientId; //Patient id, ID of the patient
	
	private LocalDate appointmentDate; //Appointment date
	
	private LocalTime appointmentTime; //Appointment Time
	
	private LocalDateTime createDate; //Created time and date of appointment
	
	//Constructor for appointment class
	public Appointment(long appointmentid, long doctorId, long patientId, LocalDate appointmentDate,
			LocalTime appointmentTime, LocalDateTime createDate) {
		super();
		this.appointmentid = appointmentid;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.createDate = createDate;
	}
	
	//-------------------------------------Getter to get data-------------------------------------------------//
	public long getAppointmentid() {
		return appointmentid;
	}


	public long getDoctorId() {
		return doctorId;
	}


	public long getPatientId() {
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
	
	//--------------------------------------------------TO String method to get all data----------------------------------//
	@Override
	public String toString() {
		return "Appointment [appointmentid=" + appointmentid + ", doctorId=" + doctorId + ", patientId=" + patientId
				+ ", appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime + ", createDate="
				+ createDate + "]";
	}

	
}
