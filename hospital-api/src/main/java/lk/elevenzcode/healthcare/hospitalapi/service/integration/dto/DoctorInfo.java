package lk.elevenzcode.healthcare.hospitalapi.service.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lk.elevenzcode.healthcare.commons.util.JsonUtil;

/**
 * Created by හShaන් සNදීප on 3/21/2020 9:30 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorInfo {
  private Integer id;
  private String name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return JsonUtil.toString(this);
  }
}
