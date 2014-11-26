package com.mowitnow;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mowitnow.business.model.Coordonnees;
import com.mowitnow.business.model.Instruction;
import com.mowitnow.business.model.Orientation;
import com.mowitnow.business.model.Tondeuse;

public class TondeuseTest {
	private static Logger	LOGGER	= LoggerFactory.getLogger(TondeuseTest.class);

	@Test
	public void testAvancerDansTerrain() {
		Tondeuse t = new Tondeuse(new Coordonnees(0, 0), Orientation.NORD);
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.NORD);

		t.traiterInstruction(Instruction.AVANCER);
		Assertions.assertThat(t.getCoordonnees()).isEqualTo(new Coordonnees(0, 1));

	}

	@Test
	public void testAvancerHorsTerrain() {
		Tondeuse t = new Tondeuse(new Coordonnees(10, 0), Orientation.EST);
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.EST);

		t.traiterInstruction(Instruction.AVANCER);
		Assertions.assertThat(t.getCoordonnees()).isEqualTo(new Coordonnees(10, 0));
	}

	@Test
	public void testTournerDroite() {
		Tondeuse t = new Tondeuse(new Coordonnees(0, 0), Orientation.NORD);
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
		Tondeuse t = new Tondeuse(new Coordonnees(0, 0), Orientation.NORD);
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.NORD);

		t.traiterInstruction(Instruction.PIVOTER_GAUCHE);
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.OUEST);

		t.traiterInstruction(Instruction.PIVOTER_GAUCHE);
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.SUD);

		t.traiterInstruction(Instruction.PIVOTER_GAUCHE);
		Assertions.assertThat(t.getOrientation()).isEqualTo(Orientation.EST);

	}

}
