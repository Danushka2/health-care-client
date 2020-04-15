package lk.elevenzcode.healthcare.appointmentapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lk.elevenzcode.healthcare.commons.domain.BaseDomain;

@Entity
@Table(name = AppointmentStatus.TABLE_NAME)
public class AppointmentStatus extends BaseDomain {
	public static final String TABLE_NAME = "appointment_status";
	public static final int STATUS_PENDING = 1;
	public static final int STATUS_COMPLETED = 2;
	public static final int STATUS_CANCELED = 3;
	
	@Column(name = "name", nullable = false, length = 10)
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AppointmentStatus(Integer id) {
		super(id);
	}
	
	public AppointmentStatus() {
		super();
	}
}
