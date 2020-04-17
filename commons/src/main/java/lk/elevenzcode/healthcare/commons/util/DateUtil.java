package lk.elevenzcode.healthcare.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by hashan on 5/9/19 9:54 AM
 */
public class DateUtil {
  private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

  public static final String PATTERN_DATE = "dd-MMM-yyyy";
  public static final String PATTERN_TIME = "hh:mm a";
  public static final String PATTERN_DATE_TIME = String.format("%s %s", PATTERN_DATE, PATTERN_TIME);

  public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
  public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(PATTERN_TIME);
  public static DateTimeFormatter dateTimeFormatter =
      DateTimeFormatter.ofPattern(PATTERN_DATE_TIME);

  public static String formatDate(LocalDate date) {
    return dateFormatter.format(date);
  }

  public static LocalDate parseDate(String date) {
    return LocalDate.parse(date, dateFormatter);
  }

  public static String formatDate(LocalDateTime dateTime) {
    return dateFormatter.format(dateTime);
  }

  public static String formatTime(LocalTime time) {
    return timeFormatter.format(time);
  }

  public static LocalTime parseTime(String time) {
    return LocalTime.parse(time, timeFormatter);
  }

  public static String formatTime(LocalDateTime dateTime) {
    return dateFormatter.format(dateTime);
  }

  public static String formatDateTime(LocalDateTime dateTime) {
    return dateTimeFormatter.format(dateTime);
  }

  public static LocalDateTime parseDateTime(String dateTime) {
    return LocalDateTime.parse(dateTime, dateTimeFormatter);
  }
}
