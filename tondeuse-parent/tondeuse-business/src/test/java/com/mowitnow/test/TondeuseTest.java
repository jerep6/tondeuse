package com.mowitnow.test;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.Subscribe;
import com.mowitnow.business.commande.Commande;
import com.mowitnow.business.commande.CommandeAvancer;
import com.mowitnow.business.commande.CommandeTournerDroite;
import com.mowitnow.business.commande.CommandeTournerGauche;
import com.mowitnow.business.event.EvenementFinTonte;
import com.mowitnow.business.event.EventBusWrapper;
import com.mowitnow.business.model.Coordonnees;
import com.mowitnow.business.model.Orientation;
import com.mowitnow.business.model.Terrain;
import com.mowitnow.business.model.Tondeuse;

public class TondeuseTest {
	private static Logger			LOGGER			= LoggerFactory.getLogger(TondeuseTest.class);

	private static List<Commande>	instructions1	= Arrays.asList(new CommandeAvancer(), null,//
			new CommandeTournerDroite(), new CommandeAvancer(), //
			new CommandeTournerGauche(), new CommandeAvancer());

	/** Tondeuse recu par l'évenement de fin de tonte. Compte sur le fait que le bus d'évements soit synchrone */
	private Tondeuse				tondeuseEvenement;

	@Subscribe
	public void gererEvenementFinTonte(EvenementFinTonte e) {
		tondeuseEvenement = e.getTondeuse();
	}

	@Test
	public void testAvancerEstDansTerrain() {
		Tondeuse tondeuse = new Tondeuse("t1", new Coordonnees(5, 2), Orientation.EST);
		Assertions.assertThat(tondeuse.getOrientation()).isEqualTo(Orientation.EST);

		Terrain terrain = new Terrain(new Coordonnees(10, 5));
		terrain.ajouterTondeuse(tondeuse);

		tondeuse.avancer();
		// La tondeuse s'est déplacée d'une case vers la droite
		Assertions.assertThat(tondeuse.getCoordonnees()).isEqualTo(new Coordonnees(6, 2));
	}

	@Test
	public void testAvancerHorsTerrain() {
		Tondeuse tondeuse = new Tondeuse("t1", new Coordonnees(10, 0), Orientation.EST);
		Assertions.assertThat(tondeuse.getOrientation()).isEqualTo(Orientation.EST);

		Terrain terrain = new Terrain(new Coordonnees(10, 5));
		terrain.ajouterTondeuse(tondeuse);

		tondeuse.avancer();
		// La tondeuse ne s'est pas déplacée
		Assertions.assertThat(tondeuse.getCoordonnees()).isEqualTo(new Coordonnees(10, 0));
	}

	@Test
	public void testAvancerNordDansTerrain() {
		Tondeuse tondeuse = new Tondeuse("t1", new Coordonnees(5, 2), Orientation.NORD);
		Assertions.assertThat(tondeuse.getOrientation()).isEqualTo(Orientation.NORD);

		Terrain terrain = new Terrain(new Coordonnees(10, 5));
		terrain.ajouterTondeuse(tondeuse);

		tondeuse.avancer();
		// La tondeuse s'est déplacée vers le haut
		Assertions.assertThat(tondeuse.getCoordonnees()).isEqualTo(new Coordonnees(5, 3));
	}

	@Test
	public void testAvancerOuestDansTerrain() {
		Tondeuse tondeuse = new Tondeuse("t1", new Coordonnees(5, 2), Orientation.OUEST);
		Assertions.assertThat(tondeuse.getOrientation()).isEqualTo(Orientation.OUEST);

		Terrain terrain = new Terrain(new Coordonnees(10, 5));
		terrain.ajouterTondeuse(tondeuse);

		tondeuse.avancer();
		// La tondeuse s'est déplacée d'une case vers la gauche
		Assertions.assertThat(tondeuse.getCoordonnees()).isEqualTo(new Coordonnees(4, 2));
	}

	@Test
	public void testAvancerSudDansTerrain() {
		Tondeuse tondeuse = new Tondeuse("t1", new Coordonnees(5, 2), Orientation.SUD);
		Assertions.assertThat(tondeuse.getOrientation()).isEqualTo(Orientation.SUD);

		Terrain terrain = new Terrain(new Coordonnees(10, 5));
		terrain.ajouterTondeuse(tondeuse);

		tondeuse.avancer();
		// La tondeuse s'est déplacée d'une case vers le bas
		Assertions.assertThat(tondeuse.getCoordonnees()).isEqualTo(new Coordonnees(5, 1));
	}

	@Test
	public void testEvenements() {
		EventBusWrapper.register(this);

		Tondeuse t = new Tondeuse("t1", new Coordonnees(0, 0), Orientation.NORD);
		Terrain terrain = new Terrain(new Coordonnees(10, 5));
		terrain.ajouterTondeuse(t);

		t.tondre();
		// Compte sur le fait que le bus d'évenement est synchrone pour ce test
		Assertions.assertThat(tondeuseEvenement).isNotNull().isEqualTo(t);

		// Supprime le listener pour ne pas traiter l'évemenent dans les autres tests effectuant des tontes
		EventBusWrapper.unregister(this);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTondreSansTerrain() {
		Tondeuse tondeuse = new Tondeuse("t1", new Coordonnees(5, 2), Orientation.EST);

		tondeuse.programmer(instructions1);
		tondeuse.tondre();

		// une exeption doit être levée car il est impossible de tondre si la tondeuse n'est pas sur un terrain
	}

	@Test
	public void testTournerDroite() {
		Tondeuse t = new Tondeuse("t1", new Coordonnees(0, 0), Orientation.NORD);
		Terrain terrain = new Terrain(new Coordonnees(10, 5));
		terrain.ajouterTondeuse(t);

		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.NORD);

		t.tournerDroite();
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.EST);

		t.tournerDroite();
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.SUD);

		t.tournerDroite();
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.OUEST);
	}

	@Test
	public void testTournerGauche() {
		Tondeuse t = new Tondeuse("t1", new Coordonnees(0, 0), Orientation.NORD);
		Terrain terrain = new Terrain(new Coordonnees(10, 5));
		terrain.ajouterTondeuse(t);
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.NORD);

		t.tournerGauche();
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.OUEST);

		t.tournerGauche();
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.SUD);

		t.tournerGauche();
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.EST);
	}

}
