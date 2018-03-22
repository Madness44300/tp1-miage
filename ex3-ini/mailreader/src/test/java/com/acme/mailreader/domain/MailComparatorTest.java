package com.acme.mailreader.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.acme.mailreader.domain.Mail.Statut;

public class MailComparatorTest {
	
	private MailComparator comparator;

	@Before
	public void setUp() throws Exception {
		this.comparator = new MailComparator();
	}

	@Test
	public final void egauxSiUnDesMailsNul() {
		Mail mail = null;
		Mail otherMail = null;
		assertThat(comparator.compare(mail, otherMail), is(MailComparator.EGAUX));
	}
	
	@Test
	public final void mailPlusImportantEnPremier() {
		Mail mail = new Mail.Builder("sujet").important(true).build();
		Mail otherMail = new Mail.Builder("sujet").important(false).build();
		assertThat(mail, not(nullValue()));
		assertThat(comparator.compare(mail, otherMail), is(MailComparator.PREMIER_PLUS_GRAND));		
	}
	
	@Test
	public final void StatutMailDifferent() {
		Mail mail = new Mail.Builder("sujet").statut(Statut.UNSENT).build();
		Mail otherMail = new Mail.Builder("sujet").statut(Statut.SENT).build();
		assertThat(comparator.compare(mail, otherMail), is(1));		
	}
	
}

