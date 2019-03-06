package com.mac.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.mac.mazer.items.Game;

public class Store {

	public static void save(Game game) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			mapper.writeValue(new File("game.json"), game);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Game load() {
		ObjectMapper mapper = new ObjectMapper();

		try {

			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			// Convert JSON string from file to Object
			Game game = mapper.readValue(new File("game.json"), Game.class);
			return game;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
