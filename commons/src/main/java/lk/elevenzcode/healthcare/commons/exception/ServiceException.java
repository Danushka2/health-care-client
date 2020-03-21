package lk.elevenzcode.healthcare.commons.exception;

/**
 * Created by හShaන් සNදීප on 3/21/2020 8:42 PM
 */
public class ServiceException extends Exception {
  public static final int VALIDATION_FAILURE = 1;
  public static final int PROCESSING_FAILURE = 2;

  private int code;

  public ServiceException(String message) {
    super(message);
    code = PROCESSING_FAILURE;
  }

  public ServiceException(int code, String message) {
    super(message);
    this.code = code;
  }

  public ServiceException(String message, Throwable cause) {
    super(message, cause);
    code = PROCESSING_FAILURE;
  }

  public ServiceException(int code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
