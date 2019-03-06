package com.mac.mazer.items;

import com.mac.mazer.items.characters.Character;
import com.mac.mazer.items.characters.Enemies;
import com.mac.mazer.items.characters.Hero;
import com.mac.util.Util;

public class Game {

	private final Coordinates coordinates;
	private Maze maze;
	private Enemies enemies;
	private Hero hero;


	public Game() {
		coordinates = new Coordinates(10, 10);
		maze = new Maze(coordinates);
		enemies = new Enemies(4, coordinates, 100);
		hero = new Hero(coordinates, 100);
	}

	public void display() {
		Character[] heroes = { hero };
		Character[] characters = Util.concatenate(enemies.getEnemies(), heroes);
		maze.display(characters);
	}

	public void turnLeft() {
		hero.turnLeft();
	}

	public void turnRight() {
		hero.turnRight();
	}

	public void moveForward() {
		enemies = hero.moveForward(this.maze, this.enemies);
	}

	public void moveBackwards() {
		enemies = hero.moveBackwards(this.maze, this.enemies);
	}
}
