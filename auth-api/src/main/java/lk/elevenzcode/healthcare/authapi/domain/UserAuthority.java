package lk.elevenzcode.healthcare.authapi.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by හShaන් සNදීප on 4/15/2020 8:51 PM
 */
@Entity
@Table(name = UserAuthority.TABLE_NAME)
public class UserAuthority implements Serializable {
  public static final String TABLE_NAME = "authorities";

  @Id
  @ManyToOne
  @JoinColumn(name = "username", nullable = false, referencedColumnName = "username")
  private User user;

  @Id
  @Column(name = "authority", nullable = false)
  private String authority;

  public UserAuthority() {
  }


  public UserAuthority(User user, String authority) {
    this.user = user;
    this.authority = authority;
  }


  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
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

    UserAuthority that = (UserAuthority) o;

    if (!user.equals(that.user)) {
      return false;
    }
    return authority.equals(that.authority);
  }

  @Override
  public int hashCode() {
    int result = user.hashCode();
    result = 31 * result + authority.hashCode();
    return result;
  }
}
