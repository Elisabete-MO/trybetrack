package com.betrybe.trybetrack.services.impl;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import java.util.Properties;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private final String username = "<seu_endereço_email_gmail>";
  private final String password = "<senha_de_aplicação>";

  public void sendEmail(String to, String subject, String message) {
//    configurações de segurança, endereço do servidor SMTP e a porta a ser utilizada
    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", 587);
//    criação uma variável session com o código de autenticação no servidor SMTP do GMa
//    il que, ao final do processo, retorna uma seção válida para envio de email
    Session session = Session.getInstance(props,
        new Authenticator() {
          @Override
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
          }
        });

    try{

      Message mimeMessage = new MimeMessage(session);
      mimeMessage.setFrom(new InternetAddress(username));
      mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
      mimeMessage.setSubject(subject);
      mimeMessage.setText(message);

      Transport.send(mimeMessage);
      System.out.println("Email enviado com sucesso!");

    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }
}