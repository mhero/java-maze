package com.mac.mazer.items;

import java.util.Scanner;

import com.mac.mazer.items.characters.Character;
import com.mac.mazer.items.characters.EnemyBuilder;
import com.mac.mazer.items.characters.Hero;
import com.mac.util.Util;

public class Game {

	private final Coordinates coordinates;
	private Maze maze;
	private EnemyBuilder enemies;
	private Hero hero;
	private Scanner sc;

	public Game() {
		coordinates = new Coordinates(6, 6);
		maze = new Maze(coordinates);
		enemies = new EnemyBuilder(4, coordinates, 20);
		hero = new Hero(coordinates, 100);
	}

	public void display() {
		Character[] heroes = { hero };
		Character[] characters = Util.concatenate(enemies.getEnemiesArray(), heroes);
		hero.displayCurrentStats();
		maze.display(characters);
	}

	public void turnLeft() {
		hero.turnLeft();
	}

	public void turnRight() {
		hero.turnRight();
	}

	public void rotate180() {
		hero.rotate18();
	}

	public void moveForward() {
		enemies = hero.moveForward(this.maze, this.enemies);
	}

	public Boolean gameFinished() {
		return this.hero.getCurrentCoordinates()
				.equals(new Coordinates(this.coordinates.getX() - 1, this.coordinates.getY() - 1));
	}

	public void finishGame() {
		System.out.println(String.format("Your score is %d", hero.currentPower()));
		System.out.println("You finished the puzzle!");
		if (hero.currentPower() < 0) {
			System.out.println("...but with a negative score...so...haha!");
		}
		sc = new Scanner(System.in);
		System.out.println("Press any key to play again...");
		sc = new Scanner(System.in);
		sc.nextLine();
	}
}
