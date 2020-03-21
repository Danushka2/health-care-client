package lk.elevenzcode.healthcare.commons.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;

/**
 * Created by hashan on 5/20/19 1:10 PM
 */
public class RandomIdUtil {
  public static String getReference() {
    final long now = System.currentTimeMillis();
    String CurrentUnixTimeMillisStr = String.valueOf(now);
    final int remainingLength = 16 - CurrentUnixTimeMillisStr.length();
    final double maxLimit = Math.pow(10, remainingLength);
    final SecureRandom r = new SecureRandom();
    final int random = r.nextInt((int) maxLimit) + 1;
    CurrentUnixTimeMillisStr += String.format("%0" + remainingLength + "d", random);
    final long timeWithRand = Long.parseLong(CurrentUnixTimeMillisStr);
    return String.format("%x", timeWithRand).toUpperCase();
  }

  public static String getUniqueNumber(int length) {
    return RandomStringUtils.random(length, false, true);
  }

  public static String generateNumber(int length, String prefix) {
    if (!StringUtils.isEmpty(prefix)) {
      if (prefix.length() >= length) {
        return prefix.substring(0, length);
      } else {
        return prefix + getUniqueNumber(length - prefix.length());
      }
    }
    return getUniqueNumber(length);
  }

  public static String getWithPaddingZero(int val, int length) {
    return String.format("%0" + length + "d", val);
  }
}
