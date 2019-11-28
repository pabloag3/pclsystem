package com.lawyersys.pclsystembe.utilidades;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author tatoa
 */
public class Mail {
    
    private String usuario = "lawyersyspcl@gmail.com";
    private String pass = "abogados1472";
    
    public Mail(){
    }
    
    public Mail(String usuario, String pass){
        this.usuario = usuario;
        this.pass = pass;
    }
    
    public void enviaStartTLS(String receptor, String subject, String text){
        final String username = usuario;
        final String password = pass;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuario));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(receptor));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Correo de recuperacion de contrasenha enviado.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
