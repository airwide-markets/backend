package za.co.airwide.marketplace.messaging;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMailService {

    public static void send(String toEmailAddress,
                            String fromEmailAddress,
                            String toFullName,
                            String subject,
                            String textMessage ) {

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", "465");

        String username = "eworldmix@gmail.com";
        String password = "changeit2020";
        System.out.println("Gmail username: " + username);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("eworldmix@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmailAddress));
            message.setSubject(subject);
            message.setText("Dear " + toFullName + ","
                    + "\n\n" + textMessage);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    /*
    public static void send( String toEmailAddress,
                             String fromEmailAddress,
                             String toFullName,
                             String textMessage )
            throws IOException {

        System.out.println("Sending email, to: " + toEmailAddress +
                            ", from: " + fromEmailAddress +
                            ", to name: " + toFullName +
                            ", text: " + textMessage );


        String username = "eworldmix@gmail.com";
        String password = "changeit2020";
        System.out.println("Gmail username: " + username);

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.debug", "true");
        Session session = null;
        session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("eworldmix@gmail.com", "changeit2020");
                    }
                });

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress("eworldmix@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("eworldmix@gmail.com")
            );
            message.setSubject("eWorldMix Order Match Found");
            message.setText("Dear " + toFullName + ","
                    + "\n\n" + textMessage);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    */
}
