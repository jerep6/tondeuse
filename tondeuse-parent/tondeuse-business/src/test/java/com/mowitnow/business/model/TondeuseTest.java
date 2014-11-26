package com.mowitnow.business.model;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Les test sont placés dans le même package que l'objet testé car ils appellent une méthode protected
 *
 * @author jerep6
 *
 */
public class TondeuseTest {
	private static Logger				LOGGER			= LoggerFactory.getLogger(TondeuseTest.class);

	private static List<Instruction>	instructions1	= Arrays.asList(Instruction.AVANCER, null,//
																Instruction.PIVOTER_DROITE, Instruction.AVANCER, //
																Instruction.PIVOTER_GAUCHE, Instruction.AVANCER);

	@Test
	public void testAvancerEstDansTerrain() {
		Tondeuse tondeuse = new Tondeuse("t1", new Coordonnees(5, 2), Orientation.EST);
		Assertions.assertThat(tondeuse.getOrientation()).isEqualTo(Orientation.EST);

		Terrain terrain = new Terrain(new Coordonnees(10, 5));
		terrain.ajouterTondeuse(tondeuse);

		tondeuse.traiterInstruction(Instruction.AVANCER);
		Assertions.assertThat(tondeuse.getCoordonnees()).isEqualTo(new Coordonnees(6, 2));
	}

	@Test
	public void testAvancerHorsTerrain() {
		Tondeuse tondeuse = new Tondeuse("t1", new Coordonnees(10, 0), Orientation.EST);
		Assertions.assertThat(tondeuse.getOrientation()).isEqualTo(Orientation.EST);

		Terrain terrain = new Terrain(new Coordonnees(10, 5));
		terrain.ajouterTondeuse(tondeuse);

		tondeuse.traiterInstruction(Instruction.AVANCER);
		Assertions.assertThat(tondeuse.getCoordonnees()).isEqualTo(new Coordonnees(10, 0));
	}

	@Test
	public void testAvancerNordDansTerrain() {
		Tondeuse tondeuse = new Tondeuse("t1", new Coordonnees(5, 2), Orientation.NORD);
		Assertions.assertThat(tondeuse.getOrientation()).isEqualTo(Orientation.NORD);

		Terrain terrain = new Terrain(new Coordonnees(10, 5));
		terrain.ajouterTondeuse(tondeuse);

		tondeuse.traiterInstruction(Instruction.AVANCER);
		Assertions.assertThat(tondeuse.getCoordonnees()).isEqualTo(new Coordonnees(5, 3));
	}

	@Test
	public void testAvancerOuestDansTerrain() {
		Tondeuse tondeuse = new Tondeuse("t1", new Coordonnees(5, 2), Orientation.OUEST);
		Assertions.assertThat(tondeuse.getOrientation()).isEqualTo(Orientation.OUEST);

		Terrain terrain = new Terrain(new Coordonnees(10, 5));
		terrain.ajouterTondeuse(tondeuse);

		tondeuse.traiterInstruction(Instruction.AVANCER);
		Assertions.assertThat(tondeuse.getCoordonnees()).isEqualTo(new Coordonnees(4, 2));
	}

	@Test
	public void testAvancerSudDansTerrain() {
		Tondeuse tondeuse = new Tondeuse("t1", new Coordonnees(5, 2), Orientation.SUD);
		Assertions.assertThat(tondeuse.getOrientation()).isEqualTo(Orientation.SUD);

		Terrain terrain = new Terrain(new Coordonnees(10, 5));
		terrain.ajouterTondeuse(tondeuse);

		tondeuse.traiterInstruction(Instruction.AVANCER);
		Assertions.assertThat(tondeuse.getCoordonnees()).isEqualTo(new Coordonnees(5, 1));
	}

	@Test
	public void testProgrammerTondeuse() {
		Tondeuse t = new Tondeuse("t1", new Coordonnees(0, 0), Orientation.NORD);

		t.programmer(instructions1);

	}

	@Test
	public void testTondreSansTerrain() {
		Tondeuse tondeuse = new Tondeuse("t1", new Coordonnees(5, 2), Orientation.EST);

		tondeuse.programmer(instructions1);
		tondeuse.tondre();
		Assertions.assertThat(tondeuse.getCoordonnees()).isEqualTo(new Coordonnees(6, 2));
	}

	@Test
	public void testTournerDroite() {
		Tondeuse t = new Tondeuse("t1", new Coordonnees(0, 0), Orientation.NORD);
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.NORD);

		t.traiterInstruction(Instruction.PIVOTER_DROITE);
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.EST);

		t.traiterInstruction(Instruction.PIVOTER_DROITE);
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.SUD);

		t.traiterInstruction(Instruction.PIVOTER_DROITE);
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.OUEST);
	}

	@Test
	public void testTournerGauche() {
		Tondeuse t = new Tondeuse("t1", new Coordonnees(0, 0), Orientation.NORD);
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.NORD);

		t.traiterInstruction(Instruction.PIVOTER_GAUCHE);
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.OUEST);

		t.traiterInstruction(Instruction.PIVOTER_GAUCHE);
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.SUD);

		t.traiterInstruction(Instruction.PIVOTER_GAUCHE);
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.EST);

	}

}
