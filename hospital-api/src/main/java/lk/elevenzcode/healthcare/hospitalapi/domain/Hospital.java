package lk.elevenzcode.healthcare.hospitalapi.domain;

import lk.elevenzcode.healthcare.commons.domain.BaseDomain;
import lk.elevenzcode.healthcare.hospitalapi.util.Constant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by හShaන් සNදීප on 3/21/2020 9:56 PM
 */
// TODO: 3/21/2020 update with neccessory attributes & annotations
@Entity
@Table(name = Hospital.TABLE_NAME)
public class Hospital extends BaseDomain {
  public static final String TABLE_NAME = "hospital";

  @Column(name = "name", length = Constant.NAME_LENGTH, nullable = false)
  private String name;

  public Hospital() {
  }

  public Hospital(Integer id, String name) {
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
