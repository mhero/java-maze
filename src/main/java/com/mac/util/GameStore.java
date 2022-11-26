package com.mac.util;

import com.mac.mazer.items.GameState;

import java.util.Optional;

/**
 * Persistence abstraction. Swap for DB, Redis, in-memory, etc.
 */
public interface GameStore {
    void save(GameState state);

    Optional<GameState> load();
}
