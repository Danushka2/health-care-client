package lk.elevenzcode.healthcare.authapi.domain;

import lk.elevenzcode.healthcare.commons.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by හShaන් සNදීප on 4/15/2020 7:42 PM
 */
@Entity
@Table(name = UserType.TABLE_NAME)
public class UserType extends BaseDomain {
  public static final String TABLE_NAME = "user_type";

  @Column(name = "type", nullable = false, length = 15)
  private String type;

  public UserType() {
  }

  public UserType(Integer id) {
    super(id);
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
