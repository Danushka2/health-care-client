package lk.elevenzcode.healthcare.doctorapi.domain;

import lk.elevenzcode.healthcare.commons.domain.BaseDomain;
import lk.elevenzcode.healthcare.doctorapi.util.Constant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by හShaන් සNදීප on 3/21/2020 8:35 PM
 */
// TODO: 3/21/2020 update with neccessory attributes & annotations
@Entity
@Table(name = Doctor.TABLE_NAME)
public class Doctor extends BaseDomain {
  public static final String TABLE_NAME = "doctor";

  @Column(name = "name", length = Constant.NAME_LENGTH, nullable = false)
  private String name;

  public Doctor() {
  }

  public Doctor(Integer id, String name) {
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
