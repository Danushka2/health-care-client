package lk.elevenzcode.healthcare.commons.web.service.dto;

import java.util.List;

/**
 * Created by හShaන් සNදීප on 5/3/2020 1:42 PM
 */
public class GridData<T> {
  private List<T> rows;
  private long total;

  public List<T> getRows() {
    return rows;
  }

  public void setRows(List<T> rows) {
    this.rows = rows;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }
}
