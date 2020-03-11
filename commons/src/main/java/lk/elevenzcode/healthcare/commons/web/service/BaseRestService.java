package lk.elevenzcode.healthcare.commons.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by හShaන් සNදීප on 3/9/2020 2:51 PM
 */
public abstract class BaseRestService {
  @Autowired
  private MessageSource messageSource;

  public String getMessage(String msg, String[] args) {
    try {
      return messageSource.getMessage(msg, args, null);
    } catch (Exception e) {
      return msg;
    }
  }

  public String getUsername() {
    Authentication a = SecurityContextHolder.getContext().getAuthentication();
    return a.getName();
  }
}
