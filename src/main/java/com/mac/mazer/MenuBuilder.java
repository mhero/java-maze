package com.mac.mazer;

import com.mac.mazer.items.Game;
import com.mac.util.Store;

import java.util.Scanner;

public class MenuBuilder {

    private final Scanner scanner = new Scanner(System.in);
    private Game game;

    public MenuBuilder() {
        Menu mainMenu = new Menu("Main", "");
        Menu gameMenu = buildGameMenu(mainMenu);

        mainMenu.putAction("start new game", () -> {
            game = new Game();
            game.display();
            activateMenu(gameMenu);
        });
        mainMenu.putAction("load game", () -> {
            game = Store.load();
            if (game != null) {
                System.out.println("game loaded");
                game.display();
                activateMenu(gameMenu);
            } else {
                System.out.println("failed to load game");
                activateMenu(mainMenu);
            }
        });
        mainMenu.putAction("save current game", () -> {
            Store.save(game);
            System.out.println("game saved");
            activateMenu(mainMenu);
        });
        mainMenu.putAction("quit", () -> {
            System.out.println("game ended, return soon!");
            System.exit(0);
        });

        activateMenu(mainMenu);
    }

    private Menu buildGameMenu(Menu mainMenu) {
        Menu menu = new Menu("Game", "current game");
        menu.putAction("step forward", () -> {
            game.moveForward();
            if (game.gameFinished()) {
                game.finishGame();
                game = new Game();
            }
            game.display();
            activateMenu(menu);
        });
        menu.putAction("rotate 180º", () -> {
            game.rotate180();
            game.display();
            activateMenu(menu);
        });
        menu.putAction("turn left", () -> {
            game.turnLeft();
            game.display();
            activateMenu(menu);
        });
        menu.putAction("turn right", () -> {
            game.turnRight();
            game.display();
            activateMenu(menu);
        });
        menu.putAction("main menu", () -> activateMenu(mainMenu));
        return menu;
    }

    private void activateMenu(Menu menu) {
        System.out.println(menu.generateText());
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                menu.executeAction(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
}