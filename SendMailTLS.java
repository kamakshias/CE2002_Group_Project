import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * This class implements java mail to send out mail to students
 * @author TAN Kheng Leong
 *
 */
public class SendMailTLS {
/**
 * Sending email to students whenever there is a change with adding/dropping Course
 * @param email Email address
 * @param message1 Contents of Email being sent to Student
 */
	public static void SENDMAIL(String email,String message1) {

		final String username = "prayingforbetteryear2020@gmail.com"; // to be added
		final String password = "ntugaShuk13"; // to be added

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
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email)); // to be added an email addr
			message.setSubject("COURSE REGISTRATION UPDATE");
			message.setText(message1);

			Transport.send(message);

			//System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}