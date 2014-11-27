package com.mowitnow.business.commande;

import com.mowitnow.business.model.Deplacable;

public class CommandeTournerGauche implements Commande {
	private Deplacable	cibleCmd;

	@Override
	public void annuler() {
		if (cibleCmd != null) {
			cibleCmd.tournerDroite();
		}

	}

	@Override
	public void executer(Deplacable vehicule) {
		vehicule.tournerGauche();
		cibleCmd = vehicule;
	}

}
