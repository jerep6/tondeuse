package com.mowitnow.business.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.mowitnow.business.commande.Commande;
import com.mowitnow.business.event.EvenementDebutTonte;
import com.mowitnow.business.event.EvenementFinTonte;
import com.mowitnow.business.event.EventBusWrapper;

/**
 * Toute action de déplacement de la tondeuse nécessite sont positionnement sur un terrain
 *
 * @author jpinsolle
 *
 */
public class Tondeuse implements Deplacable {
	private static Logger	LOGGER		= LoggerFactory.getLogger(Tondeuse.class);

	/** Identifiant fonctionnel d'une tondeuse */
	private String			numero;

	private Coordonnees		coordonnees;
	private Orientation		orientation;
	private Terrain			terrain;

	/** Liste des commandes qu doit effectuer la tondeuse lors de la tonte */
	private List<Commande>	commandes	= new ArrayList<>();

	/** Construit une tondeuse située aux coordonnées (0,0) et orientée vers le nord */
	public Tondeuse(String numero) {
		this(numero, new Coordonnees(0, 0), Orientation.NORD);
	}

	public Tondeuse(String numero, Coordonnees coordonnees, Orientation orientation) {
		super();
		this.numero = numero;
		this.coordonnees = coordonnees;
		this.orientation = orientation;
	}

	@Override
	public void avancer() {
		checkTerrain();
		Coordonnees nouvelleCoord = coordonnees.add(orientation.getTranslationUnite1());

		// Si la nouvelle position de la tondeuse est dans le terrain => déplacement
		if (terrain.estDedans(nouvelleCoord)) {
			coordonnees = nouvelleCoord;
		} else { // Tondeuse hors du terrain = log
			LOGGER.info("La tondeuse {} ne peut pas avancer car elle est à la limite du terrain", numero);
		}
	}

	/** Vérifie que la tondeuse est bien positionné sur un terrain. */
	private void checkTerrain() {
		Preconditions.checkArgument(terrain != null,
				"La tondeuse doit être positionnée sur un terrain avant d'effectuer des actions");

	}

	/** Supprime toute les instructions de la tondeuse. */
	public void deprogrammer() {
		commandes.clear();
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

	public List<Commande> getCommandes() {
		return Collections.unmodifiableList(commandes);
	}

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public String getNumero() {
		return numero;
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
	public void programmer(List<Commande> instructions) {
		Preconditions.checkNotNull(instructions);
		deprogrammer();

		// Supprime toutes les instructions nulles
		this.commandes.addAll(instructions.stream().filter(i -> i != null).collect(Collectors.toList()));
	}

	@Override
	public void reculer() {
		Coordonnees nouvelleCoord = coordonnees.substract(orientation.getTranslationUnite1());

		// Si la nouvelle position de la tondeuse est dans le terrain => déplacement
		if (terrain.estDedans(nouvelleCoord)) {
			coordonnees = nouvelleCoord;
		} else { // Tondeuse hors du terrain = log
			LOGGER.info("La tondeuse {} ne peut pas reculer car elle est à la limite du terrain", numero);
		}
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
		checkTerrain();

		EventBusWrapper.post(new EvenementDebutTonte(this, new Date()));
		for (Commande uneCommande : commandes) {
			uneCommande.executer(this);
		}
		EventBusWrapper.post(new EvenementFinTonte(this, new Date()));
	}

	@Override
	public String toString() {
		return "Tondeuse [numero=" + numero + ", coordonnees=" + coordonnees + ", orientation=" + orientation + "]";
	}

	@Override
	public void tournerDroite() {
		checkTerrain();
		orientation = orientation.tourner(90);
	}

	@Override
	public void tournerGauche() {
		checkTerrain();
		orientation = orientation.tourner(-90);
	}

}
