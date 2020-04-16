package lk.elevenzcode.healthcare.paymentapi.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by හShaන් සNදීප on 4/16/2020 3:10 PM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentInitResp {
  private String clientSecret;

  public PaymentInitResp() {
  }

  public PaymentInitResp(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }
}
