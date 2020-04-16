package lk.elevenzcode.healthcare.paymentapi.domain;

import lk.elevenzcode.healthcare.commons.domain.BaseDomain;
import lk.elevenzcode.healthcare.commons.util.Constant;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:32 PM
 */
@Entity
@Table(name = Payment.TABLE_NAME)
public class Payment extends BaseDomain {
  public static final String TABLE_NAME = "payment";

  @Column(name = "amount", nullable = false)
  @Digits(integer = Constant.DEFAULT_MONEY_INT, fraction = Constant.DEFAULT_MONEY_FRAC)
  private BigDecimal amount;

  @Column(name = "stripe_intent_id", nullable = false, length = 30)
  private String stripeIntentId;

  @Column(name = "reference", nullable = false, length = 15)
  private String reference;

  @Column(name = "appointment_id", nullable = false)
  private Integer appointmentId;

  @Column(name = "pay_on", nullable = false)
  private LocalDateTime paidOn = LocalDateTime.now();

  @ManyToOne
  @JoinColumn(name = "status", nullable = false)
  private PaymentStatus status;

  public Payment() {
  }

  public Payment(Integer id, BigDecimal amount, LocalDateTime paidOn) {
    this.id = id;
    this.amount = amount;
    this.paidOn = paidOn;
  }

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

  public String getStripeIntentId() {
    return stripeIntentId;
  }

  public void setStripeIntentId(String stripeIntentId) {
    this.stripeIntentId = stripeIntentId;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public Integer getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(Integer appointmentId) {
    this.appointmentId = appointmentId;
  }

  public LocalDateTime getPaidOn() {
    return paidOn;
  }

  public void setPaidOn(LocalDateTime paidOn) {
    this.paidOn = paidOn;
  }

  public PaymentStatus getStatus() {
    return status;
  }

  public void setStatus(PaymentStatus status) {
    this.status = status;
  }
}
