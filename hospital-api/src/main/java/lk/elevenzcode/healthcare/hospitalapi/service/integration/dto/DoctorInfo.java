package lk.elevenzcode.healthcare.hospitalapi.service.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lk.elevenzcode.healthcare.commons.util.JsonUtil;


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
