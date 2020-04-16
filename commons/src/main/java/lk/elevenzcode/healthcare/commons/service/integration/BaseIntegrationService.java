package lk.elevenzcode.healthcare.commons.service.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Created by හShaන් සNදීප on 4/16/2020 9:21 AM
 */
public abstract class BaseIntegrationService {
  @Autowired
  private MessageSource messageSource;

  public String getMessage(String msg, String... args) {
    try {
      return messageSource.getMessage(msg, args, LocaleContextHolder.getLocale());
    } catch (Exception e) {
      return msg;
    }
  }
}
