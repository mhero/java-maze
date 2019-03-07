package com.mac.mazer.items;

import com.mac.mazer.items.characters.Character;
import com.mac.mazer.items.characters.EnemyBuilder;
import com.mac.mazer.items.characters.Hero;
import com.mac.util.Util;

public class Game {

	private final Coordinates coordinates;
	private Maze maze;
	private EnemyBuilder enemies;
	private Hero hero;

	public Game() {
		coordinates = new Coordinates(10, 10);
		maze = new Maze(coordinates);
		enemies = new EnemyBuilder(100, coordinates, 20);
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

}
