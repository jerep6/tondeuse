package com.mowitnow.business.commande;

import com.mowitnow.business.model.Deplacable;

public class CommandeAvancer implements Commande {
	private Deplacable	cibleCmd;

	@Override
	public void annuler() {
		if (cibleCmd != null) {
			cibleCmd.reculer();
		}

	}

	@Override
	public void executer(Deplacable vehicule) {
		vehicule.avancer();
		cibleCmd = vehicule;
	}

}
