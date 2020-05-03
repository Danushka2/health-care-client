package lk.elevenzcode.healthcare.paymentapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

/**
 * Created by හShaන් සNදීප on 5/3/2020 4:19 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentModifyInitReq {
  private BigDecimal fee;

  public BigDecimal getFee() {
    return fee;
  }

  public void setFee(BigDecimal fee) {
    this.fee = fee;
  }
}
