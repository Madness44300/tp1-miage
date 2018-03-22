package com.acme.mailreader.domain;


import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;

import java.time.Instant;

import org.hamcrest.core.Is;
import org.junit.Ignore;
import org.junit.Test;

import com.acme.mailreader.domain.Mail.Statut;
import com.acme.mailreader.utils.DateIncorrecteException;

public class MailTest {
	
	MailComparator comparator = new MailComparator();
	
	@Test(expected=DateIncorrecteException.class)
	public final void erreurSiDateAvant1970() throws DateIncorrecteException {
		new Mail.Builder("sujet").date(Instant.parse("1968-12-03T10:15:30.00Z")).build();
				
	}
	
	@Test
	public final void nomDeSujetNonVide() throws DateIncorrecteException {
		new Mail.Builder("sujet").date(Instant.parse("2005-01-01T00:00:00.00Z")).build();
	}
	@Test
	public final void mailImportant() throws DateIncorrecteException {
		new Mail.Builder("sujet").important(true).build();
	}
	
	@Test
	public final void premierPlusPetitSiDateNulle() throws DateIncorrecteException  {
		Mail mail1 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).build();
		Mail mail2 = new Mail.Builder("uyyuy").important(false).statut(Statut.READ).date(Instant.now()).build();
		assertThat(comparator.compare(mail1, mail2),is(1));
				
	}
	
	@Test
	public final void ordreAlphabetiqueSiMemeImportance() {
		Mail mail1 = new Mail.Builder("sujet").important(true).build();
		Mail mail2 = new Mail.Builder("sujet").important(false).build();
		assertThat(mail1, not(nullValue()));
		assertThat(comparator.compare(mail1, mail2), is(MailComparator.PREMIER_PLUS_GRAND));		
	}

}
