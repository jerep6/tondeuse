package com.mowitnow.business.commande;

import com.mowitnow.business.model.Deplacable;

public class CommandeTournerDroite implements Commande {
	private Deplacable	cibleCmd;

	@Override
	public void annuler() {
		if (cibleCmd != null) {
			cibleCmd.tournerGauche();
		}

	}

	@Override
	public void executer(Deplacable vehicule) {
		vehicule.tournerDroite();
		cibleCmd = vehicule;
	}

}
