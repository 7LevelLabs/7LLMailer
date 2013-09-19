package ua.ll7.slot7.ll7mailer;

import ua.ll7.slot7.ll7mailer.model.MailTask;

import javax.ejb.Local;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * 7LLMailer
 * 19.09.13 : 16:55
 * Alex Velichko
 * alex.velichko.kyiv@gmail.com
 */
@Local
public interface EMailSenderLocal {
	/**
	 *
	 * Sending email with content of {@link ua.ll7.slot7.ll7mailer.model.MailTask}
	 * @param mailTask Mail task to send, can't be null
	 * @throws javax.mail.MessagingException if mail resource not ready,
	 * @throws java.io.UnsupportedEncodingException if character encoding is not supported
	 *
	 * */
	public void sendEmail(MailTask mailTask) throws MessagingException, UnsupportedEncodingException;

}
