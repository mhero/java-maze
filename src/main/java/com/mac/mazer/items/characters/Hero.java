package com.mac.mazer.items.characters;

import java.util.ArrayList;

import com.mac.mazer.items.Coordinates;
import com.mac.mazer.items.Maze;

public class Hero extends Character {

	private Direction direction;

	public enum Direction {
		N("▲", 0, -1), S("▼", 0, 1), E("►", 1, 0), W("◄", -1, 0);

		private final String logo;
		private Direction left;
		private Direction right;
		private Direction opposite;
		private Integer xForward;
		private Integer yForward;

		static {
			N.left = W;
			S.left = E;
			E.left = N;
			W.left = S;

			N.right = E;
			S.right = W;
			E.right = S;
			W.right = N;

			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}

		private Direction(String logo, Integer xForward, Integer yForward) {
			this.logo = logo;
			this.xForward = xForward;
			this.yForward = yForward;
		}

	}

	public Hero() {

	}

	public Hero(Coordinates coordinates, Integer power) {
		this.positions = new ArrayList<>();
		this.positions.add(new Coordinates(0, 0));
		this.power = power;
		updateDirection(Direction.S);

	}

	@Override
	public boolean isCharacterHere(Coordinates coordinates) {
		return this.positions.get(this.positions.size() - 1).equals(coordinates);
	}

	public Direction getDirection() {
		return direction;
	}

	public void turnLeft() {
		updateDirection(this.direction.left);
	}

	public void turnRight() {
		updateDirection(this.direction.right);
	}

	public void rotate18() {
		updateDirection(this.direction.opposite);
	}

	public void displayCurrentStats() {
		System.out.println("hero power: " + this.power);
		Coordinates heroCoordinates = this.positions.get(this.positions.size() - 1);
		System.out.println("hero position: " + heroCoordinates.toString());
	}

	public Enemies moveForward(Maze maze, Enemies enemies) {
		Coordinates heroCoordinates = this.positions.get(this.positions.size() - 1);
		Integer x = heroCoordinates.getX() + this.direction.xForward;
		Integer y = heroCoordinates.getY() + this.direction.yForward;
		return moveAndUpdateEnemies(x, y, maze, enemies);

	}

	private Enemies moveAndUpdateEnemies(Integer x, Integer y, Maze maze, Enemies enemies) {
		if (moveIfValid(x, y, maze)) {
			Coordinates heroCoordinates = this.positions.get(this.positions.size() - 1);
			Battle battle = new Battle();
			return battle.checkEnemiesColision(enemies, this, heroCoordinates);
		} else {
			return enemies;
		}
	}

	private Boolean moveIfValid(Integer x, Integer y, Maze maze) {
		if (maze.outsideMazeLimits(x, y) || hitsFloor(x, y, maze) || hitsWall(x, y, maze)) {
			System.out.println("Invalid move");
			return false;
		} else {
			this.positions.add(new Coordinates(x, y));
			return true;
		}
	}

	private Boolean hitsFloor(Integer x, Integer y, Maze maze) {
		return (this.direction.equals(Direction.S) && maze.hasFloorAt(x, y))
				|| (this.direction.equals(Direction.N) && maze.hasFloorAt(x, y + 1));

	}

	private Boolean hitsWall(Integer x, Integer y, Maze maze) {
		return (this.direction.equals(Direction.E) && maze.hasWallAt(x, y))
				|| (this.direction.equals(Direction.W) && maze.hasWallAt(x + 1, y));
	}

	private void updateDirection(Direction direction) {
		this.direction = direction;
		this.logo = this.direction.logo;

	}

}
