package com.mac.util;

import com.mac.mazer.items.GameState;

import java.util.Optional;

public class InMemoryGameStore implements GameStore {

    private GameState stored;

    @Override
    public void save(GameState state) {
        this.stored = state;
    }

    @Override
    public Optional<GameState> load() {
        return Optional.ofNullable(stored);
    }
}
