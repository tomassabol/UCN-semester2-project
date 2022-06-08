package model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import controller.OrderController;
import controller.OrderDetailsController;
import exceptions.NotFoundException;

public class Mail {

	private OrderDetailsController orderDetailsCtrl;
	private OrderController orderCtrl;

	public Mail() throws SQLException {
		orderDetailsCtrl = new OrderDetailsController();
		orderCtrl = new OrderController();
	}

	public static Message setUp() throws AddressException, MessagingException {
		final String username = "info.bigbike@gmail.com";
    	final String password = "gjdhbdnldmpgzmuo";

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

		return message;
	}

    public static void sendMail(String subject, String text) throws MessagingException, UnknownHostException {
		Message message = setUp();

		InetAddress ip = InetAddress.getLocalHost();
    	message.setSubject(subject);
    	message.setText(text +
		"\nIP address: " + ip);
    	Transport.send(message);
    }

	public void sendInvoice(Order order) throws MessagingException, UnknownHostException, SQLException, NotFoundException {
    	Message message = setUp();
		List<String> info = new ArrayList<>();

		orderDetailsCtrl.findByOrderId(order.getId()).forEach(OrderLine -> {
			info.add(OrderLine.getProduct().getName() + "		" + String.valueOf(OrderLine.getQuantity() + "\n"));
		});

		info.add("\n");
		info.add("Total Price: " + String.valueOf(orderCtrl.getOrderPriceAfterDiscount(order, false)) + "€");

		String orderInfo = info.toString();

    	message.setSubject("Order no." + order.getId());
		message.setText(String.format("""
			Dear Customer,
			Thank you for shopping in Bigbike. Down below you can find the invoice for your order id %d \n
			%s
				""", order.getId(), orderInfo.replaceAll(",","").replace("[","").replace("]","")));
    	Transport.send(message);
    }

}
