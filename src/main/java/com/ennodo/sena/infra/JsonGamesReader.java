package com.ennodo.sena.infra;

import com.ennodo.sena.domain.Games;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonGamesReader {
	private JsonGamesReader(){}

	public static Games read(String filename) {
		try {
			File file = new File(JsonGamesReader.class.getClassLoader().getResource(filename).getFile());

			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new FileReader(file));

			return gson.fromJson(reader, Games.class);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
