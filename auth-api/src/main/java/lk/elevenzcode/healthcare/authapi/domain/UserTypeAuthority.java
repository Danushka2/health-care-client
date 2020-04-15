package lk.elevenzcode.healthcare.authapi.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by හShaන් සNදීප on 4/15/2020 8:51 PM
 */
@Entity
@Table(name = UserTypeAuthority.TABLE_NAME)
@IdClass(UserTypeAuthority.UserTypeAuthorityId.class)
public class UserTypeAuthority implements Serializable {
  public static final String TABLE_NAME = "user_type_authority";

  @Id
  @ManyToOne
  @JoinColumn(name = "type", nullable = false)
  private UserType type;

  @Id
  @Column(name = "authority", nullable = false, length = 50)
  private String authority;

  public UserType getType() {
    return type;
  }

  public void setType(UserType type) {
    this.type = type;
  }

  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  public static class UserTypeAuthorityId implements Serializable {
    private UserType type;
    private String authority;

    public UserTypeAuthorityId() {
    }

    public UserTypeAuthorityId(UserType type, String authority) {
      this.type = type;
      this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      UserTypeAuthorityId that = (UserTypeAuthorityId) o;

      if (!type.equals(that.type)) {
        return false;
      }
      return authority.equals(that.authority);
    }

    @Override
    public int hashCode() {
      int result = type.hashCode();
      result = 31 * result + authority.hashCode();
      return result;
    }
  }
}
