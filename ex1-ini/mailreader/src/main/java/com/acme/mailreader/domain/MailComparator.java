package com.acme.mailreader.domain;

import java.util.Comparator;


/**
 * Comparateur de mails
 * 
 * Comme on désire afficher les mails les plus importants en premier, l'element le plus grand retourne une valeur négative
 * @author bflorat
 *
 */
public class MailComparator implements Comparator<Mail> {

	public static final int EGAUX = 0;
	public static final int PREMIER_PLUS_PETIT = 1;
	public static final int PREMIER_PLUS_GRAND = -1;

	public int compare(Mail mail, Mail otherMail) {
		
		if (UnDesDeuxMailsEstNul(mail, otherMail)) {
			return EGAUX;
		}
		
		if (ImportanceDifferente(mail, otherMail)) {
			return triImportance(mail, otherMail);
		}
		
		if (StatutDifferent(mail, otherMail)) {
			return triStatut(mail, otherMail);
		}
		
		if (SujetDifferent(mail, otherMail)) {
			return triSujet(mail, otherMail);
		}
		
		return mail.getDate().compareTo(otherMail.getDate());
	}

	private int triImportance(Mail mail, Mail otherMail) {
		
		if (mail.isImportant() && !otherMail.isImportant()) {
			return PREMIER_PLUS_GRAND;
		} 
		else {
			return PREMIER_PLUS_PETIT;
		}
	}

	private int triStatut(Mail mail, Mail otherMail) {
		
		int comp = mail.getStatut().ordinal() - otherMail.getStatut().ordinal();
		return comp < 0 ? PREMIER_PLUS_PETIT : PREMIER_PLUS_GRAND;
		
	}

	private int triSujet(Mail mail, Mail otherMail) {
		
		if (mail.getSujet() == null && otherMail.getSujet() != null) {
			return PREMIER_PLUS_PETIT;
		}
		else if (mail.getSujet() != null && otherMail.getSujet() == null) {
			return PREMIER_PLUS_GRAND;
		}
		
		return mail.getSujet().compareTo(otherMail.getSujet());
	}

	private boolean UnDesDeuxMailsEstNul(Mail mail, Mail otherMail) {
		
		return mail == null || otherMail == null;
	}

	private boolean ImportanceDifferente(Mail mail, Mail otherMail) {
		
		return mail.isImportant() != otherMail.isImportant();
		
	}

	private boolean StatutDifferent(Mail mail, Mail otherMail) {
		
		return mail.getStatut() != otherMail.getStatut();
		
	}

	private boolean SujetDifferent(Mail mail, Mail otherMail) {
		
		if (mail.getSujet() == null || otherMail.getSujet() == null) {
			return true;
		}
		
		return !mail.getSujet().equals(otherMail.getSujet());
	}

}
