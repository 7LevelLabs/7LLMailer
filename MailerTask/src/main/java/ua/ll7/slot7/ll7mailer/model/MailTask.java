package ua.ll7.slot7.ll7mailer.model;

import org.apache.commons.validator.routines.EmailValidator;

import java.io.Serializable;
import java.util.Objects;

/**
 * 7LLMailer
 * 19.09.13 : 16:39
 * Alex Velichko
 * alex.velichko.kyiv@gmail.com
 */

/**
 * Mail task - contain task to email: from, to, subject, text, etc.<br />
 * Immutable.
 * */
public class MailTask implements Serializable {

	/**
	 *
	 * Constructor
	 * @param from Field of the future email - From (email part). Email address validation according to RFC 822 standards. Can't be null, can't be empty.
	 * @param fromName Field of the future email - From (name part). Can be empty, not null.
	 * @param to Field of the future email - To (email part). Email address validation according to RFC 822 standards. Can't be null, can't be empty.
	 * @param toName Field of the future email - To (name part). Can be empty, not null.
	 * @param subject Field of the future email - Subject. Can't be null, can't be empty.
	 * @param messageBody Field of the future email - Subject. Can't be null, can't be empty.
	 * */
	public MailTask (final String from,
			   final String fromName,
			   final String to,
			   final String toName,
			   final String subject,
			   final String messageBody,
			   final boolean isHTML) {

		if ((from==null) ||
			(fromName==null) ||
			(to==null) ||
			(toName==null) ||
			(subject==null) ||
			(messageBody==null)) {
			throw new NullPointerException("Arguments can't be null.");
		}

		if ((from.length()==0) ||
			(to.length()==0) ||
			(subject.length()==0) ||
			(messageBody.length()==0)) {
			throw new IllegalArgumentException("Arguments can't be empty.");
		}

		if ((!this.emailVerify(from)) ||
			(!this.emailVerify(to))) {
			throw new IllegalArgumentException("EMail in the arguments must be valid according to RFC 822 standards.");
		}

		this.from = from;
		this.fromName = fromName;
		this.to = to;
		this.toName = toName;
		this.subject = subject;
		this.messageBody = messageBody;
		this.isHTMLMessage = isHTML;
	}

	/**
	 *
	 * Return From field in full form (RFC 822) , if possible.
	 * */
	public String getFromFullForm() {
		String res;
		if (this.getFromName().length()>0) {
			res = this.getFromName()+" "+this.getFrom();
		}
		else {
			res=this.getFrom();
		}
		return res;
	}

	/**
	 *
	 * Return To field in full form (RFC 822) , if possible.
	 * */
	public String getToFullForm() {
		String res;
		if (this.getToName().length()>0) {
			res = this.getToName()+" "+this.getTo();
		}
		else {
			res=this.getTo();
		}
		return res;
	}

	/**
	 *
	 * Email address validation according to RFC 822 standards
	 * @param emailToVerify Email to verify. Can't be null, can't be empty.
	 * */
	public boolean emailVerify(String emailToVerify){
		if (emailToVerify==null) {
			throw new NullPointerException("Arguments can't be null.");
		}
		if (emailToVerify.length()==0) {
			throw new IllegalArgumentException("Arguments can't be empty.");
		}

		EmailValidator validator = EmailValidator.getInstance();
		return validator.isValid(emailToVerify);
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getFromName() {
		return fromName;
	}

	public String getToName() {
		return toName;
	}

	public String getSubject() {
		return subject;
	}

	public boolean getIsHTMLMessage() {
		return isHTMLMessage;
	}

	public String getMessageBody() {
		return messageBody;
	}

	@Override
	public int hashCode() {
		return Objects.hash(from, fromName, to, toName, subject, messageBody);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final MailTask other = (MailTask) obj;
		return Objects.equals(this.from, other.from) && Objects.equals(this.fromName, other.fromName) && Objects.equals(this.to, other.to) && Objects.equals(this.toName, other.toName) && Objects.equals(this.subject, other.subject) && Objects.equals(this.messageBody, other.messageBody);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MailTask { ");
		sb.append("from='").append(from).append('\'');
		sb.append(", fromName='").append(fromName).append('\'');
		sb.append(", to='").append(to).append('\'');
		sb.append(", toName='").append(toName).append('\'');
		sb.append(", subject='").append(subject).append('\'');
		sb.append(", isHTMLMessage=").append(isHTMLMessage);
		sb.append(", messageBody='").append(messageBody).append('\'');
		sb.append('}');
		return sb.toString();
	}

	/**
	 *
	 * Future mail message - email-part of the From field
	 * */
	private final String from;

	/**
	 *
	 * Future mail message - name-part of the From field
	 * */
	private final String fromName;

	/**
	 *
	 * Future mail message - field To
	 * */
	private final String to;

	/**
	 *
	 * Future mail message - name-part of the To field
	 * */
	private final String toName;

	/**
	 *
	 * Future mail message - field Subject
	 * */
	private final String subject;

	/**
	 *
	 * Future mail message - will it be HTML message?
	 * */
	private final boolean isHTMLMessage;

	/**
	 *
	 * Future mail message - field Body
	 * */
	private final String messageBody;
}
