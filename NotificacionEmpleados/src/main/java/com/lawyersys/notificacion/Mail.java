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


/**
 *
 * @author carlo
 */
public class Mail {
    public static void main(String[] args) throws IOException {
        
        
        
        ArrayList<Actividad> listaActividades = new ArrayList<Actividad>();
        String emailReceptor;
        String mensaje = null;

        ModeloEmail modeloEmail = new ModeloEmail();
       
        
        //traer actividades
        listaActividades = modeloEmail.listarActividad();
        //traer email emisor
       
        
        for (Actividad actividad: listaActividades) {
            emailReceptor = modeloEmail.treaerEmail(actividad.getCedula());
          
            mensaje = "Tiene una actividad con descripcion " + actividad.getDescripcion() + " que es del dia " + actividad.getFecha().substring(0, 10);
            Mail mail = new Mail("lawyersyspcl@gmail.com", "abogados1472");
            mail.enviaStartTLS("lawyersyspcl@gmail.com", emailReceptor, "Notificacion del Sistema - PCL SYSTEM", mensaje);
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
