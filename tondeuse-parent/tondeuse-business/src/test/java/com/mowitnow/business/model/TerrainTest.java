package com.mowitnow.business.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mowitnow.business.model.Coordonnees;
import com.mowitnow.business.model.Terrain;
import com.mowitnow.business.model.Tondeuse;

public class TerrainTest {
	private static Logger	LOGGER	= LoggerFactory.getLogger(TerrainTest.class);

	@Test
	public void testCoordonneesDansTerrain() {
		Terrain t = new Terrain(new Coordonnees(10, 5));

		Boolean estDedans = t.estDedans(new Coordonnees(9, 5));
		Assertions.assertThat(estDedans).isTrue();
	}

	@Test
	public void testCoordonneesHorsDuTerrain() {
		Terrain t = new Terrain(new Coordonnees(10, 5));

		Boolean estDedans = t.estDedans(new Coordonnees(15, 5));
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

		// Il ne doit y avoir que 3 tondeuses sur le terrain car ajout de plusieurs fois de la tondeuse 2
		Assertions.assertThat(terrain.getTondeuses()).containsExactly(t1, t2, t3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTerrainInvalide() {
		new Terrain(new Coordonnees(0, 0));
	}
}
