package ua.ll7.slot7.ll7mailer;

import org.apache.log4j.Logger;
import ua.ll7.slot7.ll7mailer.model.MailTask;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 7LLMailer
 * 19.09.13 : 16:57
 * Alex Velichko
 * alex.velichko.kyiv@gmail.com
 */
@Stateless(name = "EMailSenderBean")
@Local(EMailSenderLocal.class)
public class EMailSenderBean implements EMailSenderLocal {

	/**
	 *
	 * Logger
	 * */
	private final Logger LOGGER = Logger.getLogger(this.getClass());

	/**
	 *
	 * Resource of the Application server
	 * */
	@Resource
	private Session session;

	@Override
	public void sendEmail(MailTask mailTask) throws MessagingException, UnsupportedEncodingException {

		if (mailTask==null) {
			throw new IllegalArgumentException("Arguments can't be null.");
		}

		Message msg = new MimeMessage(session);
		msg.setSentDate(new Date());

		msg.setFrom(
			new InternetAddress(
				mailTask.getFrom(),
				mailTask.getFromName()));

		msg.setRecipient(
			MimeMessage.RecipientType.TO,
			new InternetAddress(
				mailTask.getTo(),
				mailTask.getToName()));

		msg.setSubject(mailTask.getSubject());

		if (mailTask.getIsHTMLMessage()) {
			msg.setContent(mailTask.getMessageBody(),"text/html");
		} else {
			msg.setText(mailTask.getMessageBody());
		}

		Transport.send(msg);

	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public EMailSenderBean() {}
}
