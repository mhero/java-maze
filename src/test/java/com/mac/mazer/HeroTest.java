package com.mac.mazer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mac.mazer.items.Coordinates;
import com.mac.mazer.items.Maze;
import com.mac.mazer.items.characters.Hero;

public class HeroTest {

	private Hero hero;
	private Maze mazeH;
	private Maze mazeV;

	@BeforeEach
	public void init() {
		hero = new Hero(100);
		mazeH = new Maze(new Coordinates(1, 2));
		mazeV = new Maze(new Coordinates(2, 1));
	}

	@Test
	public void testSuccessCurrent() {
		Boolean success = hero.getCurrentCoordinates().equals(new Coordinates(0, 0));
		assertTrue(success);
	}

	@Test
	public void testSuccessMoveHorizontal() {
		hero.moveForward(mazeH, null);
		Boolean success = hero.getCurrentCoordinates().equals(new Coordinates(0, 1));
		assertTrue(success);
	}

	@Test
	public void testSuccessMoveVertical() {
		hero.turnLeft();
		hero.moveForward(mazeV, null);
		Boolean success = hero.getCurrentCoordinates().equals(new Coordinates(1, 0));
		assertTrue(success);
	}

}
