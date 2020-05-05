package lk.elevenzcode.healthcare.appointmentapi.service.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lk.elevenzcode.healthcare.commons.serializer.JsonDateTimeSerializer;
import lk.elevenzcode.healthcare.commons.util.JsonUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:48 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentInfo {
  private Integer id;
  private BigDecimal amount;
  @JsonSerialize(using = JsonDateTimeSerializer.class)
  private LocalDateTime payOn;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public LocalDateTime getPayOn() {
    return payOn;
  }

  public void setPayOn(LocalDateTime payOn) {
    this.payOn = payOn;
  }

  @Override
  public String toString() {
    return JsonUtil.toString(this);
  }
}
