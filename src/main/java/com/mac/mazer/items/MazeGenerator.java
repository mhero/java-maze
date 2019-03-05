package com.mac.mazer.items;

import java.util.Arrays;
import java.util.Collections;

import com.mac.mazer.items.characters.Character;

public class MazeGenerator {
	private final Coordinates coordinates;
	private final int[][] maze;

	public MazeGenerator(Coordinates coordinates) {
		this.coordinates = coordinates;
		this.maze = new int[this.coordinates.getX()][this.coordinates.getY()];
		generateMaze(0, 0);
	}

	public int[][] getMaze() {
		return maze;
	}

	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	private void generateMaze(int cx, int cy) {
		DIR[] dirs = DIR.values();
		Collections.shuffle(Arrays.asList(dirs));
		Integer x = this.coordinates.getX();
		Integer y = this.coordinates.getY();

		for (DIR dir : dirs) {
			int nx = cx + dir.dx;
			int ny = cy + dir.dy;
			if (between(nx, x) && between(ny, y) && (this.maze[nx][ny] == 0)) {
				this.maze[cx][cy] |= dir.bit;
				this.maze[nx][ny] |= dir.opposite.bit;
				generateMaze(nx, ny);
			}
		}
	}

	private static boolean between(int v, int upper) {
		return (v >= 0) && (v < upper);
	}

	private enum DIR {
		N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
		private final int bit;
		private final int dx;
		private final int dy;
		private DIR opposite;

		// use the static initializer to resolve forward references
		static {
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}

		private DIR(int bit, int dx, int dy) {
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}
	};

	public Boolean outsideMazeLimits(Integer x, Integer y) {
		return x < 0 || y < 0 || x > getCoordinates().getX() - 1 || y > getCoordinates().getY() - 1;
	}

	public Boolean hasFloor(Integer x, Integer y) {
		return (maze[x][y] & 1) == 0;
	}

	public void display(Character... characters) {

		int x = coordinates.getX();
		int y = coordinates.getY();

		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				System.out.print((maze[j][i] & 1) == 0 ? "+---" : "+   ");
			}
			System.out.println("+");
			for (int j = 0; j < x; j++) {
				String event = (maze[j][i] & 8) == 0 ? "| x " : "  x ";
				if (j == x - 1 && i == y - 1) {
					event = (maze[j][i] & 8) == 0 ? "| END" : " END";
					System.out.print(event);
				} else {
					System.out.print(event.replace("x", getSpace(new Coordinates(j, i), characters)));
				}
			}
			System.out.println("|");
		}
		for (int j = 0; j < x; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
	}

	public String getSpace(Coordinates coordinates, Character... characters) {
		for (Character character : characters) {
			if (character.isCharacterHere(coordinates)) {
				return character.getLogo();
			}
		}
		return " ";
	}
}
