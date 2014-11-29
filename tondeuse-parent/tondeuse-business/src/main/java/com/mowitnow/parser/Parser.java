package com.mowitnow.parser;

import java.util.Set;

import com.mowitnow.business.model.Terrain;
import com.mowitnow.business.model.Tondeuse;

public interface Parser {

	Terrain getTerrain();

	Set<Tondeuse> getTondeuses();

}