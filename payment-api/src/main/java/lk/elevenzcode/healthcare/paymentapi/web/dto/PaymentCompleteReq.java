package lk.elevenzcode.healthcare.paymentapi.web.dto;

/**
 * Created by හShaන් සNදීප on 4/16/2020 5:17 PM
 */
public class PaymentCompleteReq {
  private int appointmentId;
  private String paymentIntentId;

  public int getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(int appointmentId) {
    this.appointmentId = appointmentId;
  }

  public String getPaymentIntentId() {
    return paymentIntentId;
  }

  public void setPaymentIntentId(String paymentIntentId) {
    this.paymentIntentId = paymentIntentId;
  }
}
