package com.mac.mazer.items.characters;

import java.util.ArrayList;

import com.mac.mazer.items.Coordinates;

public class Hero extends Character {

	private Direction direction;

	public enum Direction {
		N("▲", 0, -1), S("▼", 0, 1), E("►", 1, 0), W("◄", -1, 0);

		private final String logo;
		private Direction left;
		private Direction right;
		private Integer x;
		private Integer y;

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

		private Direction(String logo, Integer x, Integer y) {
			this.logo = logo;
			this.x = x;
			this.y = y;
		}

	}

	public Hero(int amount, Coordinates coordinates) {
		this.items = new ArrayList<>();
		this.items.add(new Coordinates(0, 0));
		updateDirection(Direction.S);

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
		Integer x = coordinates.getX() + this.direction.x;
		Integer y = coordinates.getY() + this.direction.y;

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
