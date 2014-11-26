package com.mowitnow;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mowitnow.business.model.Coordonnees;
import com.mowitnow.business.model.Terrain;

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

	@Test(expected = IllegalArgumentException.class)
	public void testTerrainInvalide() {
		new Terrain(new Coordonnees(0, 0));
	}
}
