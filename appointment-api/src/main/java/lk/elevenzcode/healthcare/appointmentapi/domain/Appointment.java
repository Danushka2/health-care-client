package lk.elevenzcode.healthcare.appointmentapi.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//Appointment class that store appointment details
public class Appointment {
	private long app_id; //Appointment id Auto incremented
	private long did; //Doctor id that patient is going to channel
	private long pid; //Patient id, ID of the patient
	private LocalDate adate; //Appointment date
	private LocalTime atime; //Appointment Time
	private LocalDateTime cdate; //Created time and date of appointment
	
	//Constructor for appointment class
	public Appointment(long app_id, long did, long pid, LocalDate adate, LocalTime atime, LocalDateTime cdate) {
		super();
		this.app_id = app_id;
		this.did = did;
		this.pid = pid;
		this.adate = adate;
		this.atime = atime;
		this.cdate = cdate;
	}
	
	//-------------------------------------Getter to get data-------------------------------------------------//
	public long getApp_id() {
		return app_id;
	}

	public long getDid() {
		return did;
	}

	public long getPid() {
		return pid;
	}

	public LocalDate getAdate() {
		return adate;
	}

	public LocalTime getAtime() {
		return atime;
	}

	public LocalDateTime getCdate() {
		return cdate;
	}
	
	//--------------------------------------------------TO String method to get all data----------------------------------//
	@Override
	public String toString() {
		return "Appointment [app_id=" + app_id + ", did=" + did + ", pid=" + pid + ", adate=" + adate + ", atime="
				+ atime + ", cdate=" + cdate + "]";
	}
}
