package ua.ll7.slot7.ll7mailer;

import org.apache.log4j.Logger;
import ua.ll7.slot7.ll7mailer.model.MailTask;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * 7LLMailer
 * 19.09.13 : 17:06
 * Alex Velichko
 * alex.velichko.kyiv@gmail.com
 */
@MessageDriven(name = "MQListenerBean", activationConfig =  {
	@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MQListenerBean implements MessageListener {

	/**
	 *
	 * Logger
	 * */
	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@EJB
	private EMailSenderLocal emailSender;

	/**
	 *
	 * Receive and process Message
	 * */
	@Override
	public void onMessage(Message message) {

		ObjectMessage objectMessage = (ObjectMessage) message;

		try {
			MailTask mailTask = (MailTask) objectMessage.getObject();
			emailSender.sendEmail(mailTask);
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public MQListenerBean() {}
}
