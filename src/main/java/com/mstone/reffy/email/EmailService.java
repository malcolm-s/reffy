package com.mstone.reffy.email;

import com.mstone.reffy.referendum.Referendum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailService {
  private final JavaMailSender mailer;
  private final String adminEmail;
  private final String fromEmail;

  public EmailService(
    JavaMailSender mailer,
    @Value("${reffy.admin.email}") String adminEmail,
    @Value("${reffy.email.from}") String fromEmail
  ) {
    this.mailer = mailer;
    this.adminEmail = adminEmail;
    this.fromEmail = fromEmail;
  }

  public void sendEmail(String to, String subject, String content) {
    var mailMessage = new SimpleMailMessage();

    mailMessage.setFrom(fromEmail);
    mailMessage.setTo(to);
    mailMessage.setSubject(subject);
    mailMessage.setText(content);

    mailer.send(mailMessage);
  }

  public void sendAdminEmail(String subject, String content) {
    sendEmail(adminEmail, subject, content);
  }
}
