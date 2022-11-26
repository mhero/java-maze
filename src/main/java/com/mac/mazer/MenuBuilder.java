package com.mac.mazer;

import com.mac.mazer.items.Game;
import com.mac.mazer.items.GameState;
import com.mac.util.GameStore;
import com.mac.util.IOPort;

import java.util.Optional;

/**
 * Drives the terminal game loop.
 * Uses an iterative loop instead of recursive activateMenu calls —
 * no stack overflow risk on long sessions.
 */
public class MenuBuilder {

    private final IOPort io;
    private final Game game;
    private final GameStore store;

    private GameState state;
    private Menu activeMenu;
    private boolean running = true;

    public MenuBuilder(IOPort io, Game game, GameStore store) {
        this.io = io;
        this.game = game;
        this.store = store;

        Menu mainMenu = buildMainMenu();
        activeMenu = mainMenu;
        runLoop();
    }

    private Menu buildMainMenu() {
        Menu mainMenu = new Menu("Main", "");
        Menu[] gameMenuRef = new Menu[1];

        mainMenu.putAction("start new game", () -> {
            state = GameState.newGame();
            game.display(state);
            activeMenu = gameMenuRef[0];
        });
        mainMenu.putAction("load game", () -> {
            Optional<GameState> loaded = store.load();
            if (loaded.isPresent()) {
                state = loaded.get();
                io.println("game loaded");
                game.display(state);
                activeMenu = gameMenuRef[0];
            } else {
                io.println("failed to load game");
            }
        });
        mainMenu.putAction("save current game", () -> {
            if (state != null) {
                store.save(state);
                io.println("game saved");
            } else {
                io.println("no game to save");
            }
        });
        mainMenu.putAction("quit", () -> {
            io.println("game ended, return soon!");
            running = false;
        });

        gameMenuRef[0] = buildGameMenu(mainMenu);
        return mainMenu;
    }

    private Menu buildGameMenu(Menu mainMenu) {
        Menu menu = new Menu("Game", "current game");

        menu.putAction("step forward", () -> {
            state = game.moveForward(state);
            if (game.isFinished(state)) {
                game.showFinishScreen(state);
                state = GameState.newGame();
            }
            game.display(state);
        });
        menu.putAction("rotate 180º", () -> {
            state = game.rotate180(state);
            game.display(state);
        });
        menu.putAction("turn left", () -> {
            state = game.turnLeft(state);
            game.display(state);
        });
        menu.putAction("turn right", () -> {
            state = game.turnRight(state);
            game.display(state);
        });
        menu.putAction("main menu", () -> activeMenu = mainMenu);

        return menu;
    }

    private void runLoop() {
        while (running) {
            io.println(activeMenu.generateText());
            try {
                int choice = io.readInt();
                activeMenu.executeAction(choice);
            } catch (Exception e) {
                io.println("Invalid input. Please try again.");
            }
        }
    }
}
