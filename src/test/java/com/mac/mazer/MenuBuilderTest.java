package com.mac.mazer;

import com.mac.mazer.items.Game;
import com.mac.util.InMemoryGameStore;
import com.mac.util.StubIOPort;
import com.mac.util.StubTriviaPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuBuilderTest {

    private StubIOPort io;
    private Game game;
    private InMemoryGameStore store;

    @BeforeEach
    public void init() {
        io = new StubIOPort();
        game = new Game(io, new StubTriviaPort());
        store = new InMemoryGameStore();
    }

    private void runMenuWith(int... inputs) {
        for (int i : inputs) io.addInput(i);
        new MenuBuilder(io, game, store);
    }

    @Test
    public void testQuitExitsCleanly() {
        // Main menu: 4 = quit
        assertDoesNotThrow(() -> runMenuWith(4));
    }

    @Test
    public void testStartNewGameThenMainMenuThenQuit() {
        // 1 = start new game, then game menu: 5 = main menu, then 4 = quit
        assertDoesNotThrow(() -> runMenuWith(1, 5, 4));
    }

    @Test
    public void testSaveWithNoGamePrintsMessage() {
        // 3 = save (no game loaded), 4 = quit
        runMenuWith(3, 4);
        assertTrue(io.getOutput().stream().anyMatch(s -> s.contains("no game")));
    }

    @Test
    public void testLoadWithNothingSavedPrintsFailure() {
        // 2 = load (nothing saved), 4 = quit
        runMenuWith(2, 4);
        assertTrue(io.getOutput().stream().anyMatch(s -> s.contains("failed")));
    }

    @Test
    public void testSaveAndLoadRoundtrip() {
        // 1 = start, 5 = main menu, 3 = save, 4 = quit
        runMenuWith(1, 5, 3, 4);
        assertTrue(store.load().isPresent());
    }

    @Test
    public void testInvalidInputDoesNotCrash() {
        // feed invalid choice, then quit
        io.addInput(99);
        io.addInput(4);
        assertDoesNotThrow(() -> new MenuBuilder(io, game, store));
    }

    @Test
    public void testStepForwardInGameMenu() {
        // 1 = start new game, game menu: 1 = step forward, 5 = main menu, 4 = quit
        assertDoesNotThrow(() -> runMenuWith(1, 1, 5, 4));
    }
}
