package lk.elevenzcode.healthcare.paymentapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by හShaන් සNදීප on 5/3/2020 4:28 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentModifyCompleteReq {
  private String paymentIntentId;

  public String getPaymentIntentId() {
    return paymentIntentId;
  }

  public void setPaymentIntentId(String paymentIntentId) {
    this.paymentIntentId = paymentIntentId;
  }
}
