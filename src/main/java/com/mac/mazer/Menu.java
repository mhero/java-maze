package com.mac.mazer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

import com.mac.mazer.items.Game;
import com.mac.util.Store;

public class Menu {
	private final String name;
	private final String text;
	private LinkedHashMap<String, Runnable> actionsMap = new LinkedHashMap<>();

	public Menu(String name, String text) {
		this.name = name;
		this.text = text;
	}

	public void putAction(String name, Runnable action) {
		actionsMap.put(name, action);
	}

	public String generateText() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(": ");
		sb.append(text).append(":\n");
		List<String> actionNames = new ArrayList<>(actionsMap.keySet());
		for (int i = 0; i < actionNames.size(); i++) {
			sb.append(String.format(" %d: %s%n", i + 1, actionNames.get(i)));
		}
		return sb.toString();
	}

	public void executeAction(int actionNumber) {
		int effectiveActionNumber = actionNumber - 1;
		if (effectiveActionNumber < 0 || effectiveActionNumber >= actionsMap.size()) {
			System.out.println("Ignoring menu choice: " + actionNumber);
		} else {
			List<Runnable> actions = new ArrayList<>(actionsMap.values());
			actions.get(effectiveActionNumber).run();
		}
	}

	public static class App {
		private Menu menu;
		private Scanner scanner;
		private Game game;

		public App() {
			Menu mainMenu = new Menu("Main", "");
			Menu subMenuGame = new Menu("Game", "current game");

			mainMenu.putAction("start new game", () -> {
				game = new Game();
				game.display();
				activateMenu(subMenuGame);
			});
			mainMenu.putAction("load game", () -> {
				game = Store.load();
				System.out.println("game loaded");
				game.display();
				activateMenu(subMenuGame);
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
				game.display();
				activateMenu(subMenuGame);
			});
			subMenuGame.putAction("step backwards", () -> {
				game.moveBackwards();
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
			scanner = new Scanner(System.in);
			while (true) {
				int actionNumber = scanner.nextInt();
				menu.executeAction(actionNumber);
			}
		}

	}

	public static void main(String[] args) {
		new App();
	}

}
