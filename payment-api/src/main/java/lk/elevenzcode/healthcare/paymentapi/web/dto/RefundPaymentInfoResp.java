package lk.elevenzcode.healthcare.paymentapi.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lk.elevenzcode.healthcare.commons.serializer.JsonDateTimeSerializer;
import lk.elevenzcode.healthcare.paymentapi.domain.RefundPayment;

import java.time.LocalDateTime;

/**
 * Created by හShaන් සNදීප on 5/3/2020 2:32 PM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RefundPaymentInfoResp {
  private String reason;
  @JsonSerialize(using = JsonDateTimeSerializer.class)
  private LocalDateTime refundOn;

  public static RefundPaymentInfoResp parse(RefundPayment refundPayment) {
    final RefundPaymentInfoResp resp = new RefundPaymentInfoResp();
    resp.setReason(refundPayment.getReason());
    resp.setRefundOn(refundPayment.getRefundOn());
    return resp;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public LocalDateTime getRefundOn() {
    return refundOn;
  }

  public void setRefundOn(LocalDateTime refundOn) {
    this.refundOn = refundOn;
  }
}
