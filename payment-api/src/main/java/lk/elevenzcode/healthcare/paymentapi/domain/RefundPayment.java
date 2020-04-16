package lk.elevenzcode.healthcare.paymentapi.domain;

import lk.elevenzcode.healthcare.commons.domain.BaseDomain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by හShaන් සNදීප on 3/27/2020 3:55 PM
 */
@Entity
@Table(name = RefundPayment.TABLE_NAME)
public class RefundPayment extends BaseDomain {
  public static final String TABLE_NAME = "refund_payment";

  @OneToOne
  @JoinColumn(name = "payment_id", nullable = false, unique = true)
  private Payment payment;

  @Column(name = "reason", nullable = false, length = 100)
  private String reason;

  @Column(name = "refund_ref", nullable = false, length = 30)
  private String refundRef;

  @Column(name = "refund_on", nullable = false)
  private LocalDateTime refundOn = LocalDateTime.now();

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getRefundRef() {
    return refundRef;
  }

  public void setRefundRef(String refundRef) {
    this.refundRef = refundRef;
  }

  public LocalDateTime getRefundOn() {
    return refundOn;
  }

  public void setRefundOn(LocalDateTime refundOn) {
    this.refundOn = refundOn;
  }
}
