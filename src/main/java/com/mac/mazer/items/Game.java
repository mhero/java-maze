package com.mac.mazer.items;

import com.mac.mazer.items.characters.Character;
import com.mac.mazer.items.characters.EnemiesHandler;
import com.mac.mazer.items.characters.Hero;
import com.mac.util.Util;

public class Game {

	private final Coordinates boardSize;
	private Maze maze;
	private EnemiesHandler enemies;
	private Hero hero;

	private final Integer MAZE_SIZE = 5;
	private final Integer ENEMY_AMOUNT = 8;
	private final Integer ENEMY_POWER = 20;
	private final Integer HERO_POWER = 100;

	public Game() {
		boardSize = new Coordinates(MAZE_SIZE, MAZE_SIZE);
		maze = new Maze(boardSize);
		enemies = new EnemiesHandler(ENEMY_AMOUNT, boardSize, ENEMY_POWER);
		hero = new Hero(HERO_POWER);
	}

	public void display() {
		Character[] heroes = { hero };
		Character[] gameCharacters = Util.concatenate(enemies.getEnemies(), heroes);
		hero.displayCurrentStats();
		maze.display(gameCharacters);
	}

	public void turnLeft() {
		hero.turnLeft();
	}

	public void turnRight() {
		hero.turnRight();
	}

	public void rotate180() {
		hero.rotate180();
	}

	public void moveForward() {
		enemies = hero.moveForward(this.maze, this.enemies);
	}

	public Boolean gameFinished() {
		return this.hero.getCurrentCoordinates()
				.equals(new Coordinates(this.boardSize.getX() - 1, this.boardSize.getY() - 1));
	}

	public void finishGame() {
		System.out.println(String.format("Your score is %d", hero.currentPower()));
		System.out.println("You finished the puzzle!");
		if (hero.currentPower() < 0) {
			System.out.println("...but with a negative score...so...haha!");
		}
		Util.pressAnyKey("Press any key to play again...");
	}
}
