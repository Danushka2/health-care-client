package lk.elevenzcode.healthcare.paymentapi.domain;

import lk.elevenzcode.healthcare.commons.domain.BaseDomain;
import lk.elevenzcode.healthcare.commons.util.Constant;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
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

  @Column(name = "pay_on", nullable = false)
  private LocalDateTime payOn;

  public Payment() {
  }

  public Payment(Integer id, BigDecimal amount, LocalDateTime payOn) {
    super(id);
    this.amount = amount;
    this.payOn = payOn;
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
}
