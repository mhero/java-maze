package com.mac.mazer.items.characters;

import java.util.ArrayList;

import com.mac.mazer.items.Coordinates;

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

	public Hero(int amount, Coordinates coordinates) {
		this.items = new ArrayList<>();
		this.items.add(new Coordinates(0, 0));
		updateDirection(Direction.S);

	}

	@Override
	public boolean isCharacterHere(Coordinates coordinates) {
		return this.items.get(this.items.size() - 1).equals(coordinates);
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

	public void moveForward(Coordinates mazeCoordinates) {
		Coordinates coordinates = this.items.get(this.items.size() - 1);
		Integer x = coordinates.getX() + this.direction.xForward;
		Integer y = coordinates.getY() + this.direction.yForward;
		moveIfValid(x, y, mazeCoordinates);
	}

	public void moveBackwards(Coordinates mazeCoordinates) {
		Coordinates coordinates = this.items.get(this.items.size() - 1);
		Integer x = coordinates.getX() + this.direction.xBackwards;
		Integer y = coordinates.getY() + this.direction.yBackwards;
		moveIfValid(x, y, mazeCoordinates);
	}

	private void moveIfValid(Integer x, Integer y, Coordinates mazeCoordinates) {
		if (x < 0 || y < 0 || x > mazeCoordinates.getX() - 1 || y > mazeCoordinates.getY() - 1) {
			System.out.println("Invalid move");
		} else {
			this.items.add(new Coordinates(x, y));
		}
	}

	private void updateDirection(Direction direction) {
		this.direction = direction;
		this.logo = this.direction.logo;

	}

}
