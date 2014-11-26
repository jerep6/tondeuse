package com.mowitnow.business.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tondeuse {
	private static Logger	LOGGER	= LoggerFactory.getLogger(Tondeuse.class);

	/** Identifiant fonctionnel d'une tondeuse */
	private String			numero;

	private Coordonnees		coordonnees;
	private Orientation		orientation;
	private Terrain			terrain;

	/**
	 * Construit une tondeuse située aux coordonnées (0,0) et orientée vers le nord
	 */
	public Tondeuse(String numero) {
		this(numero, new Coordonnees(0, 0), Orientation.NORD);
	}

	public Tondeuse(String numero, Coordonnees coordonnees, Orientation orientation) {
		super();
		this.numero = numero;
		this.coordonnees = coordonnees;
		this.orientation = orientation;
	}

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * Positionne la tondeuse sur le terrain
	 * Méthode uniquement accessible du package
	 *
	 * @param t
	 *            terrain sur lequel positionner la tondeuse
	 */
	protected void positionnerSurTerrain(Terrain t) {
		terrain = t;
	}

	public void traiterInstruction(Instruction i) {

		switch (i) {
			case PIVOTER_DROITE:
				orientation = orientation.tourner(90);
				break;
			case PIVOTER_GAUCHE:
				orientation = orientation.tourner(-90);
				break;
			case AVANCER:
				Coordonnees nouvelleCoord = coordonnees.add(orientation.getTranslationUnite1());

				// Si la nouvelle position de la tondeuse est dans le terrain => déplacement
				if (terrain.estDedans(nouvelleCoord)) {
					coordonnees = nouvelleCoord;
				} else { // Tondeuse hors du terrain = log
					LOGGER.info("La tondeuse {} ne peut pas avancer car elle est à la limite du terrain", numero);
				}
				break;

			default:
				break;
		}

	}

}
