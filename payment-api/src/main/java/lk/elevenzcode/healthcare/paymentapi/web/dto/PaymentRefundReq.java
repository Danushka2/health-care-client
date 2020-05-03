package lk.elevenzcode.healthcare.paymentapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by හShaන් සNදීප on 4/16/2020 8:36 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRefundReq {
  private String reason;

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
