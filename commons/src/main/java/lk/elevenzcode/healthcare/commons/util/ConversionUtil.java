package lk.elevenzcode.healthcare.commons.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author HaShaN on 8/31/2019 3:20 PM.
 */
public class ConversionUtil {
  public static final RoundingMode DEFAULT_ROUND_MODE = RoundingMode.HALF_UP;
  public static final String THOUSAND_SEP_PATTERN = "###,##0.00";
  private static final int ONE_HUNDRED = 100;
  private static int defaultScale = 2;

  public static BigDecimal getMoney(BigDecimal source) {
    return source.setScale(defaultScale, DEFAULT_ROUND_MODE);
  }

  public static BigDecimal getMoney(double source) {
    return new BigDecimal(source).setScale(defaultScale, DEFAULT_ROUND_MODE);
  }

  public static long getMoneyInCents(BigDecimal source) {
    return getMoney(source).multiply(new BigDecimal(100)).longValue();
  }

  public static BigDecimal parseMoney(long source) {
    return getMoney(new BigDecimal(source).divide(new BigDecimal(ONE_HUNDRED)));
  }

  public static String getMoneyWithThousandSeparator(BigDecimal source) {
    return new DecimalFormat(THOUSAND_SEP_PATTERN).format(getMoney(source));
  }
}
