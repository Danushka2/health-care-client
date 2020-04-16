package lk.elevenzcode.healthcare.paymentapi.service.integration.dto;

/**
 * Created by හShaන් සNදීප on 4/16/2020 8:06 PM
 */
public class AppointmentUpdateReq {
  private Integer status;

  public AppointmentUpdateReq() {
  }

  public AppointmentUpdateReq(Integer status) {
    this.status = status;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
