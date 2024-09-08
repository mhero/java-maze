package com.mac.mazer;

import com.mac.mazer.items.Game;
import com.mac.util.Store;

import java.util.Scanner;

public class MenuBuilder {
    private Menu menu;
    private Scanner scanner;
    private Game game;

    public MenuBuilder() {
        Menu mainMenu = new Menu("Main", "");
        Menu subMenuGame = new Menu("Game", "current game");

        mainMenu.putAction("start new game", () -> {
            game = new Game();
            game.display();
            activateMenu(subMenuGame);
        });
        mainMenu.putAction("load game", () -> {
            game = Store.load();
            if (game != null) {
                System.out.println("game loaded");
                game.display();
                activateMenu(subMenuGame);
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

        subMenuGame.putAction("step forward", () -> {
            game.moveForward();
            if (game.gameFinished()) {
                game.finishGame();
                game = new Game();

            }
            game.display();
            activateMenu(subMenuGame);
        });
        subMenuGame.putAction("rotate 180ª", () -> {
            game.rotate180();
            game.display();
            activateMenu(subMenuGame);
        });
        subMenuGame.putAction("turn left", () -> {
            game.turnLeft();
            game.display();
            activateMenu(subMenuGame);
        });
        subMenuGame.putAction("turn right", () -> {
            game.turnRight();
            game.display();
            activateMenu(subMenuGame);
        });
        subMenuGame.putAction("main menu", () -> activateMenu(mainMenu));

        activateMenu(mainMenu);
    }

    private void activateMenu(Menu newMenu) {
        menu = newMenu;
        System.out.println(newMenu.generateText());
        while (true) {
            try {
                scanner = new Scanner(System.in);
                int actionNumber = Integer.parseInt(scanner.nextLine());
                menu.executeAction(actionNumber);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please try again.");
            }

        }
    }

}
