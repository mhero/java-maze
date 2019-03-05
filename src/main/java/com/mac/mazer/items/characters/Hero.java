package com.mac.mazer.items.characters;

import java.util.ArrayList;

import com.mac.mazer.items.Coordinates;
import com.mac.mazer.items.MazeGenerator;

public class Hero extends Character {

	private Direction direction;

	public enum Direction {
		N("▲", 0, -1, 0, 1), S("▼", 0, 1, 0, -1), E("►", 1, 0, -1, 0), W("◄", -1, 0, 1, 0);

		private final String logo;
		private Direction left;
		private Direction right;
		private Integer xForward;
		private Integer yForward;
		private Integer xBackwards;
		private Integer yBackwards;

		static {
			N.left = W;
			S.left = E;
			E.left = N;
			W.left = S;

			N.right = E;
			S.right = W;
			E.right = S;
			W.right = N;
		}

		private Direction(String logo, Integer xForward, Integer yForward, Integer xBackwards, Integer yBackwards) {
			this.logo = logo;
			this.xForward = xForward;
			this.yForward = yForward;
			this.xBackwards = xBackwards;
			this.yBackwards = yBackwards;
		}

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

	public Enemies moveForward(MazeGenerator maze, Enemies enemies) {
		Coordinates heroCoordinates = this.positions.get(this.positions.size() - 1);
		Integer x = heroCoordinates.getX() + this.direction.xForward;
		Integer y = heroCoordinates.getY() + this.direction.yForward;
		return moveAndUpdateEnemies(x, y, maze, enemies);

	}

	public Enemies moveBackwards(MazeGenerator maze, Enemies enemies) {
		Coordinates heroCoordinates = this.positions.get(this.positions.size() - 1);
		Integer x = heroCoordinates.getX() + this.direction.xBackwards;
		Integer y = heroCoordinates.getY() + this.direction.yBackwards;
		return moveAndUpdateEnemies(x, y, maze, enemies);
	}

	private Enemies moveAndUpdateEnemies(Integer x, Integer y, MazeGenerator maze, Enemies enemies) {
		if (moveIfValid(x, y, maze)) {
			Coordinates heroCoordinates = this.positions.get(this.positions.size() - 1);
			return checkEnemiesColision(enemies, heroCoordinates);
		} else {
			return enemies;
		}
	}

	private Enemies checkEnemiesColision(Enemies enemies, Coordinates heroCoordinates) {
		enemies.removeEnemyAt(heroCoordinates);
		return enemies;
	}

	private Boolean moveIfValid(Integer x, Integer y, MazeGenerator maze) {

		if (x < 0 || y < 0 || x > maze.getCoordinates().getX() - 1 || y > maze.getCoordinates().getY() - 1) {
			System.out.println("Invalid move");
			return false;
		} else {
			this.positions.add(new Coordinates(x, y));
			return true;
		}
	}

	private void updateDirection(Direction direction) {
		this.direction = direction;
		this.logo = this.direction.logo;

	}

}
