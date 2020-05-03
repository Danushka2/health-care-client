package lk.elevenzcode.healthcare.paymentapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by හShaන් සNදීප on 4/16/2020 3:07 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentInitReq {
  private int appointmentId;

  public int getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(int appointmentId) {
    this.appointmentId = appointmentId;
  }
}
