package lk.elevenzcode.healthcare.commons.web.service.dto;

/**
 * Created by හShaන් සNදීප on 4/15/2020 11:37 PM
 */
public class ServiceResponse<T> {
  private boolean hasError;
  private String error;
  private T body;

  public boolean isHasError() {
    return hasError;
  }

  public void setHasError(boolean hasError) {
    this.hasError = hasError;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    hasError = true;
    this.error = error;
  }

  public T getBody() {
    return body;
  }

  public void setBody(T body) {
    this.body = body;
  }
}
