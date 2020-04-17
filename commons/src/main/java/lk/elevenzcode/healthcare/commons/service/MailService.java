package lk.elevenzcode.healthcare.commons.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;

import java.util.Map;

/**
 * Created by හShaන් සNදීප on 4/17/2020 11:13 PM
 */
public interface MailService {
  void send(String recipientEmail, String subject, String templatePath, Map<String, Object> model)
      throws ServiceException;
}
