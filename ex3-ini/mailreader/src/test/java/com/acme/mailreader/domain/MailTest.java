package com.acme.mailreader.domain;

import java.time.Instant;

import org.junit.Test;

import com.acme.mailreader.utils.DateIncorrecteException;

public class MailTest {
	

	@Test(expected=DateIncorrecteException.class)
	public final void erreurSiDateAvant1979() throws DateIncorrecteException {
		new Mail.Builder("sujet").date(Instant.parse("1912-01-01T00:00:00.00Z")).build();
				
	}
	
	
	@Test
	public final void nomDeSujetNonVide() throws DateIncorrecteException {
		new Mail.Builder("sujet").date(Instant.parse("2005-01-01T00:00:00.00Z")).build();
	}
	@Test
	public final void mailImportant() throws DateIncorrecteException {
		new Mail.Builder("sujet").important(true).build();
	}


}
