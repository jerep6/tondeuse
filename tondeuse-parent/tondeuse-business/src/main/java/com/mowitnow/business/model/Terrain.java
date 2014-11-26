package com.mowitnow.business.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Le terrain est un rectangle dont les coordonnées du coin en bas à gauche sont (0,0). L
 *
 * @author jpinsolle
 *
 */
public class Terrain {

	/** Coordonnées du coin en haut à droite */
	private Coordonnees		coordonneesHautDroit;

	/** Liste des tondeuses présentent sur le terrain */
	private Set<Tondeuse>	tondeuses	= new HashSet<>();

	public Terrain(Coordonnees coordonneesHautDroit) {
		super();
		// Vérifie que les coordonnées sont valides pour construire le terrain
		if (coordonneesHautDroit == null || coordonneesHautDroit.getX() <= 0 || coordonneesHautDroit.getY() <= 0) {
			throw new IllegalArgumentException("Les coordonnées fournies sont incorrectes");
		}
		this.coordonneesHautDroit = coordonneesHautDroit;
	}

	/**
	 * Ajoute une tondeuse sur le terrain.
	 *
	 * @param tondeuse
	 * @return
	 */
	public Terrain ajouterTondeuse(Tondeuse tondeuse) {
		tondeuses.add(tondeuse);

		// Renseigne également le terrain dans la tondeuse
		tondeuse.positionnerSurTerrain(this);
		return this;
	}

	/**
	 * Indique si les coordonnées passées en paramètres sont à l'interieur du terrain
	 *
	 * @param c
	 *            coordonnées à tester
	 * @return vrai si les coordonnées sont dedans. False sinon
	 */
	public Boolean estDedans(Coordonnees c) {
		return coordonneesHautDroit.getX() >= c.getX() && coordonneesHautDroit.getY() >= c.getY();
	}

	public Set<Tondeuse> getTondeuses() {
		return Collections.unmodifiableSet(tondeuses);
	}
}
