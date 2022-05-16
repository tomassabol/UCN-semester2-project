package model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

    public static void sendMail(String subject, String text) throws MessagingException, UnknownHostException {
    	final String username = "info.bigbike@gmail.com";
    	final String password = "CSCCSDS211";

    	Properties props = new Properties();
    	props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.debug", "true");

    	Session session = Session.getInstance(props,
    	    new javax.mail.Authenticator() {
    	        protected PasswordAuthentication getPasswordAuthentication() {
    	            return new PasswordAuthentication(username, password);
    	        }
    	    }
    	);

    	Message message = new MimeMessage(session);
    	message.setFrom(new InternetAddress("info.bigbike@gmail.com"));
    	message.setRecipients(Message.RecipientType.TO,
    	                    InternetAddress.parse("info.bigbike@gmail.com"));

		InetAddress ip = InetAddress.getLocalHost();
    	message.setSubject(subject);
    	message.setText(text +
		"\nIP address: " + ip);
    	Transport.send(message);
    }
}
