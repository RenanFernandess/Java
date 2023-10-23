package com.renan.mailsend.mailsend.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SendMail {

  @Autowired
  private final JavaMailSender mailSender;

  public SendMail(final JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void send(String from,String to, String title, String body) {
    log.info("Enviando email para verificação de conta");

    var message = new SimpleMailMessage();
    message.setFrom(from);
    message.setTo(to);
    message.setSubject(title);
    message.setText(body);

    mailSender.send(message);
    log.info("Email enviado com sucesso");
  }
}
