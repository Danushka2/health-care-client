package lk.elevenzcode.healthcare.paymentapi.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by හShaන් සNදීප on 4/16/2020 5:41 PM
 */
public class PaymentInfoResp {
  private int id;
  private String reference;
  private BigDecimal fee;
  private LocalDateTime paidOn;
  private String status;

  public PaymentInfoResp() {
  }

  public PaymentInfoResp(int id, String reference, BigDecimal fee, LocalDateTime paidOn,
                         String status) {
    this.id = id;
    this.reference = reference;
    this.fee = fee;
    this.paidOn = paidOn;
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public BigDecimal getFee() {
    return fee;
  }

  public void setFee(BigDecimal fee) {
    this.fee = fee;
  }

  public LocalDateTime getPaidOn() {
    return paidOn;
  }

  public void setPaidOn(LocalDateTime paidOn) {
    this.paidOn = paidOn;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
