package lk.elevenzcode.healthcare.commons.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by හShaන් සNදීප on 3/21/2020 8:36 PM
 */
@MappedSuperclass()
public abstract class BaseDomain implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  protected Integer id;

  public BaseDomain() {
  }

  public BaseDomain(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    BaseDomain that = (BaseDomain) o;

    return id != null ? id.equals(that.id) : that.id == null;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "BaseDomain{id=" + id + '}';
  }
}
