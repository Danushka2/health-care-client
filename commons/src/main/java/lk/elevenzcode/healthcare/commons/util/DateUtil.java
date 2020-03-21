package lk.elevenzcode.healthcare.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hashan on 5/9/19 9:54 AM
 */
public class DateUtil {
  public static final String PATTERN_DATE = "yyyy/MM/dd";
  public static final String PATTERN_DATE_TIME = "yyyy/MM/dd HH:mm:ss";
  public static final String PATTERN_DATE_MMM = "dd-MMM-yyyy";
  public static final String PATTERN_DATE_MMM_TIME_12HRS = "dd-MMM-yyyy hh:mm a";

  private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
  public static SimpleDateFormat webDateFormat = new SimpleDateFormat(PATTERN_DATE);
  public static SimpleDateFormat webDateTimeFormat = new SimpleDateFormat(PATTERN_DATE_TIME);
  public static SimpleDateFormat webDateTimeMMMFormat =
      new SimpleDateFormat(PATTERN_DATE_MMM_TIME_12HRS);
  public static SimpleDateFormat webDateMMMFormat =
      new SimpleDateFormat(PATTERN_DATE_MMM);

  static {
    webDateFormat.setLenient(false);
    webDateTimeFormat.setLenient(false);
    webDateTimeMMMFormat.setLenient(false);
    webDateMMMFormat.setLenient(false);
  }

  public static String formatToWebDate(Date date) {
    return webDateFormat.format(date);
  }

  public static Date parseToWebDate(String date) {
    try {
      return webDateFormat.parse(date);
    } catch (ParseException e) {
      logger.error("Error ", e);
    }
    return null;
  }

  public static String formatToWebDateTime(Date date) {
    return webDateTimeFormat.format(date);
  }

  public static Date parseToWebDateTime(String date) {
    try {
      return webDateTimeFormat.parse(date);
    } catch (ParseException e) {
      logger.error("Error ", e);
    }
    return null;
  }

  public static String formatToWebDateTimeMMM(Date date) {
    return webDateTimeMMMFormat.format(date);
  }

  public static Date parseToWebDateTimeMMM(String date) {
    try {
      return webDateTimeMMMFormat.parse(date);
    } catch (ParseException e) {
      logger.error("Error ", e);
    }
    return null;
  }

  public static String formatToWebDateMMM(Date date) {
    return webDateMMMFormat.format(date);
  }

  public static Date parseToWebDateMMM(String date) {
    try {
      return webDateMMMFormat.parse(date);
    } catch (ParseException e) {
      logger.error("Error ", e);
    }
    return null;
  }
}
