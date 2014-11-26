package com.mowitnow.business.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

public class Tondeuse {
	private static Logger		LOGGER			= LoggerFactory.getLogger(Tondeuse.class);

	/** Identifiant fonctionnel d'une tondeuse */
	private String				numero;

	private Coordonnees			coordonnees;
	private Orientation			orientation;
	private Terrain				terrain;

	private List<Instruction>	instructions	= new ArrayList<>();

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

	/** Supprime toute les instructions de la tondeuse. */
	public void deprogrammer() {
		instructions.clear();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Tondeuse other = (Tondeuse) obj;
		if (numero == null) {
			if (other.numero != null) return false;
		} else if (!numero.equals(other.numero)) return false;
		return true;
	}

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (numero == null ? 0 : numero.hashCode());
		return result;
	}

	/**
	 * Positionne la tondeuse sur le terrain
	 * Méthode uniquement accessible du package
	 *
	 * @param t
	 *            terrain sur lequel positionner la tondeuse
	 */
	void positionnerSurTerrain(Terrain t) {
		terrain = t;
	}

	/**
	 * Renseigne les instructions que la tondeuse devra effectuer. Chaque programmation annule la précedente.
	 * Les instructions nulles sont supprimées
	 *
	 * @param instructions
	 *            liste des instructions de la tondeuse. La liste ne doit pas être nulle
	 */
	public void programmer(List<Instruction> instructions) {
		Preconditions.checkNotNull(instructions);
		deprogrammer();

		// Supprime toutes les instructions nulles
		this.instructions.addAll(instructions.stream().filter(i -> i != null).collect(Collectors.toList()));
	}

	/**
	 * Lance toutes les instructions enregistrées dans la tondeuse. Si aucune instruction, alors la tondeuse ne fait
	 * rien
	 *
	 * La tondeuse doit avoir été positionnée au préalable sur un terrain par la méthode
	 * {@link Terrain#ajouterTondeuse(Tondeuse)}, sinon une exception est levée. Voir la
	 * méthode
	 */
	public void tondre() {
		Preconditions
		.checkArgument(terrain != null, "La tondeuse doit être positionnée sur un terrain avant de tondre");

		for (Instruction uneInstruction : instructions) {
			traiterInstruction(uneInstruction);
		}
	}

	protected void traiterInstruction(Instruction i) {

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
