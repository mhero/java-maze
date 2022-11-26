package com.mac.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mac.mazer.items.Game;

import java.io.File;
import java.io.IOException;

public class Store {

    public static final String GAME_FILE = "game.json";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
    }

    public static void save(Game game) {
        try {
            MAPPER.writeValue(new File(GAME_FILE), game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Game load() {
        try {
            return MAPPER.readValue(new File(GAME_FILE), Game.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
