package com.mowitnow.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.mowitnow.business.commande.Commande;
import com.mowitnow.business.model.Coordonnees;
import com.mowitnow.business.model.Orientation;
import com.mowitnow.business.model.Terrain;
import com.mowitnow.business.model.Tondeuse;

/**
 * Parser de fichier simple. Cela signique que le fichier ne doit pas être volumineux car toutes les lignes sont montées
 * en mémoire
 *
 * @author jpinsolle
 *
 */
public class SimpleFileParser implements Parser {
	private static Logger	LOGGER	= LoggerFactory.getLogger(SimpleFileParser.class);

	/** Lignes du fichier */
	private List<String>	lignes;

	public SimpleFileParser(Path cheminFichier) throws IOException {
		super();
		LOGGER.debug("Création d'un SimpleFileParser avec le fichier {}", cheminFichier.toAbsolutePath());

		Preconditions.checkNotNull(cheminFichier);
		if (!Files.exists(cheminFichier)) {
			throw new FileNotFoundException();
		}

		// conserve uniquement les lignes métiers (supprime les commentaires et sauts de lignes)
		lignes = Files.lines(cheminFichier, StandardCharsets.UTF_8).filter(l -> estLigneMetier(l)).map(l -> l.trim())
				.collect(Collectors.toList());
	}

	/**
	 * Indique si la ligne contient de l'information métier
	 * Les lignes ayant les caractéristiques suivantes sont ignorées :
	 * <ul>
	 * <li>Ligne vide</li>
	 * <li>Ligne commençant par #</li>
	 * </ul>
	 *
	 * @return
	 */
	private boolean estLigneMetier(String l) {
		boolean b = false;
		// Est ce que la ligne est vide ou commence par # ?
		if (com.google.common.base.Strings.isNullOrEmpty(l) || l.trim().startsWith("#")) {
			b = true;
		}

		return !b;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.mowitnow.parser.Parser#getTerrain()
	 */
	@Override
	public Terrain getTerrain() {
		// Le terrain correspond à la première ligne
		String l = lignes.get(0);
		LOGGER.debug("Traitement du terrain {}", l);
		String[] split = l.split(" ");

		Terrain t = new Terrain(new Coordonnees(Integer.valueOf(split[0]), Integer.valueOf(split[1])));
		return t;
	}

	@Override
	public Set<Tondeuse> getTondeuses() {
		Set<Tondeuse> tondeuses = new HashSet<>(lignes.size() - 1 / 2);

		// Les tondeuses commencent à la deuxième ligne
		for (int i = 1; i < lignes.size(); i = i + 2) {

			// Initialisation de la tondeuse
			String ligne1 = lignes.get(i);
			LOGGER.debug("Traitement tondeuse {}", ligne1);
			String[] split1 = ligne1.split(" ");
			Coordonnees coord = new Coordonnees(Integer.valueOf(split1[0]), Integer.valueOf(split1[1]));
			Tondeuse tondeuseCourante = new Tondeuse(UUID.randomUUID().toString(), coord,
					Orientation.valueOfByCode(split1[2]));

			// Initialisation des instructions
			String ligne2 = lignes.get(i + 1);
			LOGGER.debug("Traitement des instructions de la tondeuse {}", ligne2);
			List<Commande> cmd = ligne2.chars()//
					.mapToObj(c -> (char) c)// conversion des int en char
					.map(CharacterToCommande::charToCommande)// extraction des classes des commandes
					.flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())// suppression des optionals
					.collect(Collectors.toList());// conversion en liste
			LOGGER.debug("Liste des commandes {}", cmd);

			tondeuseCourante.programmer(cmd);
			tondeuses.add(tondeuseCourante);
		}
		return tondeuses;
	}

}
