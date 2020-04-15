package lk.elevenzcode.healthcare.paymentapi.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by හShaන් සNදීප on 3/27/2020 3:55 PM
 */
@Entity
@Table(name = RefundPayment.TABLE_NAME)
@PrimaryKeyJoinColumn(name = RefundPayment.REFERENCED_COLUMN_NAME, referencedColumnName = "id")
public class RefundPayment extends Payment {
  public static final String TABLE_NAME = "refund_payment";
  public static final String REFERENCED_COLUMN_NAME = "payment_id";

  @Column(name = "reason", nullable = false, length = 100)
  private String reason;

  @Column(name = "refund_ref", nullable = false, length = 30)
  private String refundRef;

  @Column(name = "refund_on", nullable = false)
  private LocalDateTime refundOn = LocalDateTime.now();

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
