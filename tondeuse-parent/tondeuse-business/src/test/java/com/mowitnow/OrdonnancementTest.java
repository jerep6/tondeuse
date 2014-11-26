package com.mowitnow;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.mowitnow.business.model.Coordonnees;
import com.mowitnow.business.model.Instruction;
import com.mowitnow.business.model.Orientation;
import com.mowitnow.business.model.Terrain;
import com.mowitnow.business.model.Tondeuse;

public class OrdonnancementTest {

	@Test
	public void testExerciceXebia() {

		Terrain terrain = new Terrain(new Coordonnees(5, 5));

		Tondeuse t1 = new Tondeuse("t1", new Coordonnees(1, 2), Orientation.NORD);
		t1.programmer(Arrays.asList(Instruction.PIVOTER_GAUCHE, //
				Instruction.AVANCER, //
				Instruction.PIVOTER_GAUCHE, //
				Instruction.AVANCER, //
				Instruction.PIVOTER_GAUCHE, //
				Instruction.AVANCER, //
				Instruction.PIVOTER_GAUCHE, //
				Instruction.AVANCER, //
				Instruction.AVANCER));

		Tondeuse t2 = new Tondeuse("t2", new Coordonnees(3, 3), Orientation.EST);
		t2.programmer(Arrays.asList(Instruction.AVANCER, //
				Instruction.AVANCER, //
				Instruction.PIVOTER_DROITE, //
				Instruction.AVANCER, //
				Instruction.AVANCER, //
				Instruction.PIVOTER_DROITE, //
				Instruction.AVANCER, //
				Instruction.PIVOTER_DROITE,//
				Instruction.PIVOTER_DROITE,//
				Instruction.AVANCER));

		terrain.ajouterTondeuse(t1);
		terrain.ajouterTondeuse(t2);

		terrain.tondre();

		// Tondeuse 1
		Assertions.assertThat(t1.getCoordonnees()).isEqualTo(new Coordonnees(1, 3));
		Assertions.assertThat(t1.getOrientation()).isEqualTo(Orientation.NORD);

		// Tondeuse 2
		Assertions.assertThat(t2.getCoordonnees()).isEqualTo(new Coordonnees(5, 1));
		Assertions.assertThat(t2.getOrientation()).isEqualTo(Orientation.EST);
	}
}
