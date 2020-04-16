package lk.elevenzcode.healthcare.authapi.domain;

import lk.elevenzcode.healthcare.commons.domain.BaseDomain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by හShaන් සNදීප on 4/15/2020 7:39 PM
 */
@Entity
@Table(name = User.TABLE_NAME)
public class User extends BaseDomain {
  public static final String TABLE_NAME = "users";

  @Column(name = "username", nullable = false, length = 50)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "enabled", nullable = false)
  private boolean isEnabled;

  @ManyToOne
  @JoinColumn(name = "type", nullable = false, columnDefinition = "int(11) default 1")
  private UserType type;

  @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
  private List<UserAuthority> authorities;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean enabled) {
    isEnabled = enabled;
  }

  public UserType getType() {
    return type;
  }

  public void setType(UserType type) {
    this.type = type;
  }

  public List<UserAuthority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<UserAuthority> authorities) {
    this.authorities = authorities;
  }
}
