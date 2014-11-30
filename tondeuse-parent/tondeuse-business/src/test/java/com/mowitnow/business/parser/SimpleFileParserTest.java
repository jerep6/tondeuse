package com.mowitnow.business.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.mowitnow.business.commande.CommandeAvancer;
import com.mowitnow.business.commande.CommandeTournerDroite;
import com.mowitnow.business.commande.CommandeTournerGauche;
import com.mowitnow.business.model.Coordonnees;
import com.mowitnow.business.model.Orientation;
import com.mowitnow.business.model.Terrain;
import com.mowitnow.business.model.Tondeuse;
import com.mowitnow.parser.SimpleFileParser;

public class SimpleFileParserTest {

	private void assertTondeuse(Set<Tondeuse> tondeuses, Coordonnees coordonnees, Orientation orientation,
			List<Class> commandes) {
		// récupère la tondeuse à partir de ses coordonnées.
		Optional<Tondeuse> optT1 = tondeuses.stream().filter(t -> coordonnees.equals(t.getCoordonnees())).findFirst();
		Tondeuse t1 = optT1.orElse(null);
		Assertions.assertThat(t1).isNotNull(); // La tondeuse doit exister
		Assertions.assertThat(t1.getOrientation()).isEqualTo(orientation); // vérifie l'orientation

		// Ses instructions sont les suivantes. Ne vérifie que les classes
		Assertions.assertThat(t1.getCommandes().stream().map(c -> {
			Class cl = c.getClass();
			return cl;
		}).collect(Collectors.toList()))//
		.containsExactlyElementsOf(commandes);
	}

	@Test(expected = FileNotFoundException.class)
	public void testParseFichierInexistant() throws IOException {
		new SimpleFileParser(Paths.get("fichier_inexistant"));
	}

	@Test
	public void testParseOK() throws IOException {
		SimpleFileParser fp = new SimpleFileParser(Paths.get("./src/test/resources/tondeuse_init_ok.txt"));

		// Coordonnées du terrain
		Terrain terrain = fp.getTerrain();
		Assertions.assertThat(terrain).isEqualToComparingFieldByField(new Terrain(new Coordonnees(5, 5)));

		// Tondeuses 1
		assertTondeuse(fp.getTondeuses(), new Coordonnees(1, 2), Orientation.NORD,
				Arrays.asList(CommandeTournerGauche.class, //
						CommandeAvancer.class,//
						CommandeTournerGauche.class, //
						CommandeAvancer.class,//
						CommandeTournerGauche.class, //
						CommandeAvancer.class,//
						CommandeTournerGauche.class, //
						CommandeAvancer.class,//
						CommandeAvancer.class));

		// Tondeuses 2
		assertTondeuse(fp.getTondeuses(), new Coordonnees(3, 3), Orientation.EST, Arrays.asList(CommandeAvancer.class, //
				CommandeAvancer.class,//
				CommandeTournerDroite.class, //
				CommandeAvancer.class,//
				CommandeAvancer.class,//
				CommandeTournerDroite.class, //
				CommandeAvancer.class,//
				CommandeTournerDroite.class, //
				CommandeTournerDroite.class, //
				CommandeAvancer.class));

	}
}
