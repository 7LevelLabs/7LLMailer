package ua.ll7.slot7.ll7mailer.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 7LLMailer
 * 19.09.13 : 16:42
 * Alex Velichko
 * alex.velichko.kyiv@gmail.com
 */
public class MailTaskTest extends Assert {

	private MailTask mailTask;

	@Before
	public void setUp() throws Exception {
		this.mailTask = new MailTask("alex@test.com",
			"",
			"alex@test-test.com",
			"Alex from the Test-test",
			"Test message : subject",
			"Test message : body",
			false);
	}

	@After
	public void tearDown() throws Exception {
		this.mailTask=null;
	}

	@Test(expected = NullPointerException.class)
	public void testConstructor() throws Exception {

		MailTask mt;

		String bf = "alex@test.com";
		String bfn = "";
		String bt = "alex@test-test.com";
		String btn = "Alex from the Test-test";
		String bs = "Test message : subject";
		String bb = "Test message : body";
		boolean bh = false;

		//for field From
		//must fire NPE
		mt = new MailTask(null,
			bfn,
			bt,
			btn,
			bs,
			bb,
			bh);

		mt = null;

		//for field To
		//must fire NPE
		mt = new MailTask(bf,
			bfn,
			null,
			btn,
			bs,
			bb,
			bh);

		//for field Subject
		//must fire NPE
		mt = new MailTask(bf,
			bfn,
			bt,
			btn,
			null,
			bb,
			bh);

		mt = null;

		//for field Body
		//must fire NPE
		mt = new MailTask(bf,
			bfn,
			bt,
			btn,
			bs,
			null,
			bh);

		mt = null;

	}

	@Test(expected = NullPointerException.class)
	public void testEmailVerifyNull() throws Exception {
		String assertRes = null;
		assertTrue(this.mailTask.emailVerify(assertRes));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmailVerifyEmpty() throws Exception {
		String assertRes = "";
		assertTrue(this.mailTask.emailVerify(assertRes));
	}

	@Test
	public void testEmailVerify() throws Exception {
		assertTrue(this.mailTask.emailVerify("alex@alex.com"));
		assertTrue(!this.mailTask.emailVerify("alex@alex@com"));
		assertTrue(!this.mailTask.emailVerify("@alex@com"));
	}

	@Test
	public void testGetFrom() throws Exception {
		String assertRes = "alex@test.com";
		assertTrue(assertRes.equals(this.mailTask.getFrom()));
	}

	@Test
	public void testGetFromFullForm() throws Exception {
		String assertRes = "alex@test.com";
		assertTrue(assertRes.equals(this.mailTask.getFromFullForm()));
	}

	@Test
	public void testGetTo() throws Exception {
		String assertRes = "alex@test-test.com";
		assertTrue(assertRes.equals(this.mailTask.getTo()));
	}

	@Test
	public void testGetToFullForm() throws Exception {
		String assertRes = "Alex from the Test-test alex@test-test.com";
		assertTrue(assertRes.equals(this.mailTask.getToFullForm()));
	}

	@Test
	public void testGetSubject() throws Exception {
		String assertRes = "Test message : subject";
		assertTrue(assertRes.equals(this.mailTask.getSubject()));
	}

	@Test
	public void testGetIsHTMLMessage() throws Exception {
		boolean assertRes = false;
		assertTrue(assertRes==this.mailTask.getIsHTMLMessage());
	}

	@Test
	public void testGetMessageBody() throws Exception {
		String assertRes = "Test message : body";
		assertTrue(assertRes.equals(this.mailTask.getMessageBody()));
	}

	@Test
	public void testHashCode() throws Exception {
		int hash = -1155852906;
		assertTrue(hash==this.mailTask.hashCode());
	}

	@Test
	public void testEquals() throws Exception {
		MailTask m1;

		String bf = "alex@test.com";
		String bfn = "";
		String bt = "alex@test-test.com";
		String btn = "Alex from the Test-test";
		String bs = "Test message : subject";
		String bb = "Test message : body";
		boolean bh = false;

		m1 = new MailTask(bf,
			bfn,
			bt,
			btn,
			bs,
			bb,
			bh);
		assertTrue(m1.equals(this.mailTask));
		m1 = null;

		m1 = new MailTask("equals@equals.com",
			bfn,
			bt,
			btn,
			bs,
			bb,
			bh);

		assertTrue(!m1.equals(this.mailTask));
		m1 = null;

		m1 = new MailTask(bf,
			bfn,
			"equals@equals.com",
			btn,
			bs,
			bb,
			bh);

		assertTrue(!m1.equals(this.mailTask));
		m1 = null;

		m1 = new MailTask(bf,
			bfn,
			bt,
			btn,
			"equals",
			bb,
			bh);
		assertTrue(!m1.equals(this.mailTask));
		m1 = null;

		m1 = new MailTask(bf,
			bfn,
			bt,
			btn,
			bs,
			"equals",
			bh);
		assertTrue(!m1.equals(this.mailTask));
		m1 = null;
	}

	@Test
	public void testToString() throws Exception {
		String assertRes = "MailTask { from='alex@test.com', fromName='', to='alex@test-test.com', toName='Alex from the Test-test', subject='Test message : subject', isHTMLMessage=false, messageBody='Test message : body'}";
		assertTrue(assertRes.equals(this.mailTask.toString()));
	}
}
