package com.mowitnow.business.commande;

import com.mowitnow.business.model.Deplacable;

public interface Commande {
	void executer(Deplacable vehicule);

	void annuler();

}
