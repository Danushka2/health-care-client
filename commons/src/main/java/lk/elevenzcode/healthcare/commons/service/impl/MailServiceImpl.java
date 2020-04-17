package lk.elevenzcode.healthcare.commons.service.impl;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by හShaන් සNදීප on 4/17/2020 11:14 PM
 */
@Service
public class MailServiceImpl implements MailService {
  private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);
  @Autowired
  protected MessageSource messageSource;
  @Autowired
  private JavaMailSender emailSender;
  @Autowired
  private SpringTemplateEngine templateEngine;

  @Value("${mail.default.from.address}")
  private String fromAdr;
  @Value("${mail.default.from.name}")
  private String fromName;
  @Value("${mail.default.reply.to.address}")
  private String replyTo;

  private String getMessage(String msg, String... args) {
    return messageSource.getMessage(msg, args, LocaleContextHolder.getLocale());
  }

  @Override
  public void send(String recipientEmail, String subject, String templatePath,
                   Map<String, Object> model) throws ServiceException {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("send(recipientEmail:{}, subject:{}, templatePath:{}, model:{}", recipientEmail,
          subject, templatePath, model);
    }
    try {
      MimeMessage message = emailSender.createMimeMessage();
      final MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

      Context context = new Context();
      context.setVariables(model);
      String html = templateEngine.process(templatePath, context);

      helper.setTo(recipientEmail);
      helper.setText(html, true);
      helper.setSubject(subject);
      helper.setFrom(fromAdr, fromName);
      helper.setReplyTo(replyTo);

      new Thread(() -> {
        try {
          emailSender.send(message);
        } catch (MailException e) {
          LOGGER.error(e.getMessage(), e);
        }
      }).start();
    } catch (MessagingException | UnsupportedEncodingException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE,
          getMessage("label.err.mail.send.failed"));
    }
  }
}
