package lk.elevenzcode.healthcare.commons.util;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by hashan on 5/19/19 11:20 AM
 */
public class ValidationUtil {
  private static final String NIC_REGEX = "^([0-9]{9}[x|X|v|V]|[0-9]{12})$";

  public static void validateNIC(String nic) throws ServiceException {
    if (StringUtils.isNotEmpty(nic)) {
      if (!Pattern.matches(NIC_REGEX, nic)) {
        throw new ServiceException(ServiceException.VALIDATION_FAILURE, "label.commons.err" +
            ".invalid.nic");
      }
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE, "label.commons.err.invalid" +
          ".nic");
    }
  }
}
