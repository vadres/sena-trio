package com.ennodo.sena;

import com.ennodo.sena.domain.Games;
import com.ennodo.sena.infra.JsonGamesReader;

import java.util.List;
import java.util.logging.Logger;

public class App {
	private static final Logger logger = Logger.getLogger(App.class.getName());

	public static void main(String ...args) {
		String filename = "games.json";
		Games games = JsonGamesReader.read(filename);
		List<String> trios = games.mapTrio();

		String triosStr = trios.toString();
		logger.info(triosStr);
	}
}
