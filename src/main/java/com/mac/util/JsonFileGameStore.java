package com.mac.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mac.mazer.items.GameState;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class JsonFileGameStore implements GameStore {

    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
        MAPPER.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
    }

    private final String filePath;

    public JsonFileGameStore(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(GameState state) {
        try {
            MAPPER.writeValue(new File(filePath), state);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<GameState> load() {
        try {
            return Optional.of(MAPPER.readValue(new File(filePath), GameState.class));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
