package lk.elevenzcode.healthcare.patientapi.domain;

import lk.elevenzcode.healthcare.commons.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = PatientStatus.TABLE_NAME)
public class PatientStatus extends BaseDomain {
  public static final String TABLE_NAME = "patient_status";
  public static final int STATUS_INACTIVE = 1;
  public static final int STATUS_ACTIVE = 2;
  public static final int STATUS_DELETED = 3;

  @Column(name = "name", nullable = false)
  private String name;

  public PatientStatus() {
  }

  public PatientStatus(Integer id) {
    super(id);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
