package com.mowitnow.test;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.mowitnow.business.exception.TondeuseDejaSurTerrainException;
import com.mowitnow.business.model.Coordonnees;
import com.mowitnow.business.model.Terrain;
import com.mowitnow.business.model.Tondeuse;

public class TerrainTest {

	@Test
	public void testChangerTondeuseDeTerrain() {
		Terrain t = new Terrain(new Coordonnees(2, 3), new Coordonnees(10, 5));
		Tondeuse t1 = new Tondeuse("t1");
		t.ajouterTondeuse(t1);
		t.supprimerTondeuse(t1);

		Terrain t2 = new Terrain(new Coordonnees(1, 1), new Coordonnees(10, 5));
		t2.ajouterTondeuse(t1);
	}

	@Test
	public void testCoordonneesDansTerrain() {
		Terrain t = new Terrain(new Coordonnees(2, 3), new Coordonnees(10, 5));

		Boolean estDedans = t.estDedans(new Coordonnees(9, 5));
		Assertions.assertThat(estDedans).isTrue();
	}

	@Test
	public void testCoordonneesHorsDuTerrain1() {
		Terrain t = new Terrain(new Coordonnees(2, 3), new Coordonnees(10, 5));

		Boolean estDedans = t.estDedans(new Coordonnees(15, 5));
		Assertions.assertThat(estDedans).isFalse();
	}

	@Test
	public void testCoordonneesHorsDuTerrain2() {
		Terrain t = new Terrain(new Coordonnees(2, 3), new Coordonnees(10, 5));

		Boolean estDedans = t.estDedans(new Coordonnees(1, 1));
		Assertions.assertThat(estDedans).isFalse();
	}

	@Test
	public void testTerrainAjouterTondeuse() {
		Terrain terrain = new Terrain(new Coordonnees(10, 5));

		Tondeuse t1 = new Tondeuse("t1");
		Tondeuse t2 = new Tondeuse("t2");
		Tondeuse t2bis = new Tondeuse("t2");
		Tondeuse t3 = new Tondeuse("t3");

		// Ajout des tondeuses
		terrain.ajouterTondeuse(t1);
		terrain.ajouterTondeuse(t2);
		terrain.ajouterTondeuse(t2bis);
		terrain.ajouterTondeuse(t3);

		// Il ne doit y avoir que 3 tondeuses sur le terrain car ajout multiple de la tondeuse 2
		Assertions.assertThat(terrain.getTondeuses()).containsExactly(t1, t2, t3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTerrainInvalide() {
		new Terrain(new Coordonnees(5, 3), new Coordonnees(10, 3));

		// Une exception doit être levée car les coordonnées passées en paramètre ne permettent pas de former un
		// rectangle
	}

	@Test(expected = TondeuseDejaSurTerrainException.class)
	public void testTondeuseDejaSurTerrain() {
		Terrain t = new Terrain(new Coordonnees(2, 3), new Coordonnees(10, 5));
		Tondeuse t1 = new Tondeuse("t1");
		t.ajouterTondeuse(t1);

		Terrain t2 = new Terrain(new Coordonnees(1, 1), new Coordonnees(10, 5));
		// Il est impossible d'ajouter la tondeuse au second terrain car elle est déjà sur le premier
		t2.ajouterTondeuse(t1);
	}

	@Test
	public void testTondeuseSurMemeTerrain() {
		Terrain t = new Terrain(new Coordonnees(2, 3), new Coordonnees(10, 5));
		Tondeuse t1 = new Tondeuse("t1");
		t.ajouterTondeuse(t1);
		t.ajouterTondeuse(t1);
	}
}
