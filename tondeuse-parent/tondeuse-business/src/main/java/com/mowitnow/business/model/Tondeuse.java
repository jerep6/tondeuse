package com.mowitnow.business.model;

public class Tondeuse {
	private Coordonnees	coordonnees;
	private Orientation	orientation;
	private Terrain		terrain;

	/**
	 * Construit une tondeuse située aux coordonnées (0,0) et orientée vers le nord
	 */
	public Tondeuse() {
		this(new Coordonnees(0, 0), Orientation.NORD);
	}

	public Tondeuse(Coordonnees coordonnees, Orientation orientation) {
		super();
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
				coordonnees = coordonnees.add(orientation.getTranslationUnite1());
				break;

			default:
				break;
		}

	}

}
