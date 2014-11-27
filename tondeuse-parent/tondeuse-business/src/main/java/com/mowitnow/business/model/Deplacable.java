package com.mowitnow.business.model;

/**
 * Interface signifiant que le véhicule peut se déplacer
 *
 * @author jpinsolle
 */
public interface Deplacable {

	/** Déplace le véhicule d'une case vers l'avant (dans le sens de la direction courante du véhicule) */
	void avancer();

	/**
	 * Déplace le véhicule d'une case vers l'arrière (dans le sens opposé à la direction courante du véhicule)
	 */
	void reculer();

	/** Pivote le véhicule de 90° dans le sens anti-trigonométrique */
	void tournerDroite();

	/** Pivote le véhicule de 90° dans le sens trigonométrique */
	void tournerGauche();

}
