package lk.elevenzcode.healthcare.patientapi.domain;

import lk.elevenzcode.healthcare.commons.domain.BaseDomain;
import lk.elevenzcode.healthcare.patientapi.util.Constant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:26 PM
 */
// TODO: 3/21/2020 update with neccessory attributes & annotations
@Entity
@Table(name = Patient.TABLE_NAME)
public class Patient extends BaseDomain {
  public static final String TABLE_NAME = "patient";

  @Column(name = "name", length = Constant.NAME_LENGTH, nullable = false)
  private String name;

  public Patient() {
  }

  public Patient(Integer id, String name) {
    super(id);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
