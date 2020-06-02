package com.lawyersys.notificacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import io.github.cdimascio.dotenv.Dotenv;


/**
 *
 * @author carlo
 */
public class Mail {
    public static void main(String[] args) throws IOException {
        
        
        Dotenv dotenv = Dotenv.load();
        ArrayList<Cuentas> listaCuentas = new ArrayList<Cuentas>();
        String mensaje = null;

        ModeloEmail modeloEmail = new ModeloEmail();
       
        
        //traer actividades
        listaCuentas = modeloEmail.listarCuentas();
        //traer email emisor
       
        
        for (Cuentas cuentas: listaCuentas) {
          
            mensaje = "Tiene una cuenta del caso con descripcion " + cuentas.getDescripcion() + "con un total de: " + cuentas.getTotal() + " y un saldo de: " + cuentas.getSaldo();
            
            
            Mail mail = new Mail(dotenv.get("CORREO"), dotenv.get("CONTRASENHA"));
            mail.enviaStartTLS(dotenv.get("CORREO"), cuentas.getCorreo(), "Notificacion del Sistema - PCL SYSTEM", mensaje);
        }
    }

    private String usuario;
    private String pass;

    public Mail(String usuario, String pass){
        this.usuario = usuario;
        this.pass = pass;
    }

    public void enviaStartTLS(String from, String to, String subject, String text){
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
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
