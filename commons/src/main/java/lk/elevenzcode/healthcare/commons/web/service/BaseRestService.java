package lk.elevenzcode.healthcare.commons.web.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.web.service.dto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by හShaන් සNදීප on 3/9/2020 2:51 PM
 */
public abstract class BaseRestService {
  @Autowired
  private MessageSource messageSource;

  protected String getMessage(String msg, String... args) {
    try {
      return messageSource.getMessage(msg, args, LocaleContextHolder.getLocale());
    } catch (Exception e) {
      return msg;
    }
  }

  protected String getAuthUser() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }

  protected void setError(ServiceException e, ServiceResponse r) {
    String error;
    if (e.getCode() == ServiceException.PROCESSING_FAILURE) {
      error = e.getMessage();
    } else {
      error = getMessage(e.getMessage());
    }
    r.setError(error);
  }
}
