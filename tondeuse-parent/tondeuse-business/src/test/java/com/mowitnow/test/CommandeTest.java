package com.mowitnow.test;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.mowitnow.business.commande.Commande;
import com.mowitnow.business.commande.CommandeAvancer;
import com.mowitnow.business.commande.CommandeTournerDroite;
import com.mowitnow.business.commande.CommandeTournerGauche;
import com.mowitnow.business.model.Coordonnees;
import com.mowitnow.business.model.Orientation;
import com.mowitnow.business.model.Terrain;
import com.mowitnow.business.model.Tondeuse;

public class CommandeTest {

	@Test
	public void testCommandeAvancer() {
		// Init
		Tondeuse t = new Tondeuse("t1", new Coordonnees(5, 5), Orientation.NORD);
		Terrain terrain = new Terrain(new Coordonnees(10, 10));
		terrain.ajouterTondeuse(t);

		// Avance la tondeuse. L'orientation ne change pas
		Commande cmd = new CommandeAvancer();
		cmd.executer(t);
		Assertions.assertThat(t.getCoordonnees()).isEqualTo(new Coordonnees(5, 6));
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.NORD);

		// Annule l'actio. La tondeuse revient à ses coordonnées précédentes.
		cmd.annuler();
		Assertions.assertThat(t.getCoordonnees()).isEqualTo(new Coordonnees(5, 5));
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.NORD);
	}

	@Test
	public void testCommandeTounerDroite() {
		// Init
		Tondeuse t = new Tondeuse("t1", new Coordonnees(5, 5), Orientation.NORD);
		Terrain terrain = new Terrain(new Coordonnees(10, 10));
		terrain.ajouterTondeuse(t);

		// Tourne la tondeuse.
		Commande cmd = new CommandeTournerDroite();
		cmd.executer(t);
		Assertions.assertThat(t.getCoordonnees()).isEqualTo(new Coordonnees(5, 5));
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.EST);

		// Annule l'action. La tondeuse revient à sont orientation précédente
		cmd.annuler();
		Assertions.assertThat(t.getCoordonnees()).isEqualTo(new Coordonnees(5, 5));
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.NORD);
	}

	@Test
	public void testCommandeTounerGauche() {
		// Init
		Tondeuse t = new Tondeuse("t1", new Coordonnees(5, 5), Orientation.NORD);
		Terrain terrain = new Terrain(new Coordonnees(10, 10));
		terrain.ajouterTondeuse(t);

		// Tourne la tondeuse.
		Commande cmd = new CommandeTournerGauche();
		cmd.executer(t);
		Assertions.assertThat(t.getCoordonnees()).isEqualTo(new Coordonnees(5, 5));
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.OUEST);

		// Annule l'action. La tondeuse revient à sont orientation précédente
		cmd.annuler();
		Assertions.assertThat(t.getCoordonnees()).isEqualTo(new Coordonnees(5, 5));
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.NORD);
	}
}
