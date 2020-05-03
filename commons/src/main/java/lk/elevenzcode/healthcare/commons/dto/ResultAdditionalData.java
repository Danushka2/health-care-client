package lk.elevenzcode.healthcare.commons.dto;

/**
 * Created by හShaන් සNදීප on 5/3/2020 1:46 PM
 */
public class ResultAdditionalData {
  private Long count = new Long(0);

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public void addCount(Long count) {
    this.count += count != null ? count : 0;
  }
}
