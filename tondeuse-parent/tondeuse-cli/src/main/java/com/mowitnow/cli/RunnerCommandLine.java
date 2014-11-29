package com.mowitnow.cli;

import java.io.IOException;
import java.nio.file.Paths;

import com.google.common.eventbus.Subscribe;
import com.mowitnow.business.event.EvenementFinTonte;
import com.mowitnow.business.event.EventBusWrapper;
import com.mowitnow.business.model.Terrain;
import com.mowitnow.parser.SimpleFileParser;

public class RunnerCommandLine {

	public static class GererEvenementFinTonte {
		@Subscribe
		public void gererFinTonte(EvenementFinTonte e) {
			System.out.println("#############################");
			System.out.println("La tondeuse " + e.getTondeuse().getNumero() + " a fini de tondre");
			System.out.println(e.getTondeuse().getCoordonnees());
			System.out.println("Orientation : " + e.getTondeuse().getOrientation());
			System.out.println("");
		}
	}

	public static void main(String[] args) throws IOException {
		// Récupération du chemin du fichier contenant les instructions
		if (args.length < 1) {
			System.err.println("Le chemin du fichier est manquant");
			System.exit(1);
		}

		EventBusWrapper.register(new RunnerCommandLine.GererEvenementFinTonte());

		SimpleFileParser parser = new SimpleFileParser(Paths.get(args[0]));
		Terrain terrain = parser.getTerrain();
		terrain.ajouterTondeuses(parser.getTondeuses());

		System.out.println("Début de la tonte sur le terrain");
		terrain.tondre();

	}

}
