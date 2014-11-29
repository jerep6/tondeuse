package com.mowitnow.business.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Preconditions;

/**
 * Le terrain est un rectangle.
 *
 * Un terrain se voit assigner des tondeuses
 *
 * @author jpinsolle
 *
 */
public class Terrain {

	/** Coordonnées du coin en haut à droite. Le terrain est donc un rectangle */
	private Coordonnees		coordonneesBasGauche;

	/** Coordonnées du coin en haut à droite. Le terrain est donc un rectangle */
	private Coordonnees		coordonneesHautDroit;

	/** Tondeuses présentent sur le terrain */
	private Set<Tondeuse>	tondeuses	= new HashSet<>();

	/**
	 *
	 * Construit un terrain à partir de ses coordonnées haut/droit. Suppose que les coordonnées bas/gauche sont (0.0).
	 *
	 * @param coordonneesHautDroit
	 */
	public Terrain(Coordonnees coordonneesHautDroit) {
		this(new Coordonnees(0, 0), coordonneesHautDroit);
	}

	/**
	 * Construit un terrain à partir de ses coordonnées
	 *
	 * @param coordonneesBasGauche
	 * @param coordonneesHautDroit
	 * @throws IllegalArgumentException
	 *             si les coordonnées ne sont pas valide. C'est à dire qu'elles ne permettent pas de former un rectangle
	 *             dont les coordonnées sont positives
	 */
	public Terrain(Coordonnees coordonneesBasGauche, Coordonnees coordonneesHautDroit) {
		super();
		// Vérifie que les coordonnées sont valides pour construire le terrain
		if (coordonneesBasGauche == null //
				|| coordonneesHautDroit == null //
				|| coordonneesHautDroit.getX() <= coordonneesBasGauche.getX()
				|| coordonneesHautDroit.getY() <= coordonneesBasGauche.getY()) {
			throw new IllegalArgumentException("Les coordonnées fournies sont incorrectes");
		}
		this.coordonneesBasGauche = coordonneesBasGauche;
		this.coordonneesHautDroit = coordonneesHautDroit;
	}

	/**
	 * Ajoute une tondeuse sur le terrain.
	 *
	 * @param tondeuse
	 *            tondeuse à ajouter au terrain
	 * @return
	 */
	public void ajouterTondeuse(Tondeuse tondeuse) {
		tondeuses.add(tondeuse);

		// Renseigne également le terrain dans la tondeuse
		tondeuse.positionnerSurTerrain(this);
	}

	/**
	 * Ajoute une collection de tondeuses au terrain
	 * 
	 * @param collection
	 */
	public void ajouterTondeuses(Collection<Tondeuse> collection) {
		Preconditions.checkNotNull(collection);

		for (Tondeuse uneTondeuse : collection) {
			ajouterTondeuse(uneTondeuse);
		}
	}

	/**
	 * Indique si les coordonnées passées en paramètres sont à l'interieur du terrain
	 *
	 * @param c
	 *            coordonnées à tester
	 * @return vrai si les coordonnées sont dedans. False sinon
	 */
	public Boolean estDedans(Coordonnees c) {
		return coordonneesBasGauche.getX() <= c.getX() && coordonneesBasGauche.getY() <= c.getY()//
				&& coordonneesHautDroit.getX() >= c.getX() && coordonneesHautDroit.getY() >= c.getY();
	}

	/**
	 * @return Retourne l'ensemble des tondeuses présentes sur le terrain. L'ensemble retourné est immuable
	 */
	public Set<Tondeuse> getTondeuses() {
		return Collections.unmodifiableSet(tondeuses);
	}

	/**
	 * Lance la tonte du terrain avec toute les tondeuses présentes sur ce terrain
	 * Le traitement s'effectue en séquentiel. C'est à dire que chaque tondeuse effectue son parcourt et laisse la main
	 * à la suivante
	 */
	public void tondre() {
		for (Tondeuse uneTondeuse : tondeuses) {
			uneTondeuse.tondre();
		}
	}
}
