package com.mowitnow.parser;

import java.util.Optional;

import com.mowitnow.business.commande.Commande;
import com.mowitnow.business.commande.CommandeAvancer;
import com.mowitnow.business.commande.CommandeTournerDroite;
import com.mowitnow.business.commande.CommandeTournerGauche;

public class CharacterToCommande {

	/**
	 * Converti un char en commande.
	 *
	 * @param c
	 *            caractère représentant le nom de l'instruction
	 * @return
	 */
	public static Optional<Commande> charToCommande(char c) {
		Commande com = null;
		switch (c) {
			case 'D':
				com = new CommandeTournerDroite();
				break;
			case 'G':
				com = new CommandeTournerGauche();
				break;
			case 'A':
				com = new CommandeAvancer();
				break;

			default:
				break;
		}

		return Optional.ofNullable(com);
	}

}
