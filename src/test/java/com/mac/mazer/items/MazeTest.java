package com.mac.mazer.items;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest {
	private Maze mazeH;
	private Maze mazeV;

	@BeforeEach
	public void init() {
		mazeH = new Maze(new Coordinates(1, 2));
		mazeV = new Maze(new Coordinates(2, 1));
	}

	@Test
	public void testSuccessCreate() {

		int[][] mazeHMock = { { 2, 1 } };
		assertArrayEquals(mazeH.getMaze(), mazeHMock);

		int[][] mazeVMock = { { 4 }, { 8 } };
		assertArrayEquals(mazeV.getMaze(), mazeVMock);
	}

	@Test
	public void testSuccessTrueFloor() {
		Boolean success = mazeH.hasFloorAt(0, 0);
		assertTrue(success);
	}

	@Test
	public void testSuccessFalseFloor() {
		Boolean success = mazeV.hasFloorAt(0, 0);
		assertTrue(success);
	}

	@Test
	public void testSuccessFalseWall() {
		Boolean success = mazeH.hasWallAt(0, 0);
		assertTrue(success);
	}

	@Test
	public void testSuccessTrueWall() {
		Boolean success = mazeV.hasWallAt(0, 0);
		assertTrue(success);
	}

	@Test
	public void testSuccessTrueLimits() {
		Boolean success = mazeV.outsideMazeLimits(1, 1);
		assertTrue(success);

		success = mazeH.outsideMazeLimits(2, 2);
		assertTrue(success);
	}
}
