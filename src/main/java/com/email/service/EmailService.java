package com.email.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    public boolean sendEmail(String subject, String message, String to){



        boolean f = false;

        String from ="";

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        System.out.println(properties);

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // step:1 to get the session object

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("", "");
            }

        });

        // session debug

        session.setDebug(true);

        // step2: compose the message [text,multimedia]

        MimeMessage m = new MimeMessage(session);

        try {

            // from email
            m.setFrom(from);

            // adding recipent to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // adding subjet to message

            m.setSubject(subject);

            // adding text to message
            m.setText(message);

            Transport.send(m);

            System.out.println("sent successfully...");

            f=true;

        } catch (MessagingException e) {

            e.printStackTrace();
        }

        return f;

    }

}
