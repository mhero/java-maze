package com.mac.mazer.items;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mac.mazer.items.Coordinates;
import com.mac.mazer.items.Maze;

public class MazeTest {
	private Maze mazeH;
	private Maze mazeV;

	@Before
	public void init() {
		mazeH = new Maze(new Coordinates(1, 2));
		mazeV = new Maze(new Coordinates(2, 1));

	}

	@Test
	public void testSuccessCreate() {

		int[][] mazeHMock = { { 2, 1 } };
		Assert.assertArrayEquals(mazeH.getMaze(), mazeHMock);

		int[][] mazeVMock = { { 4 }, { 8 } };
		Assert.assertArrayEquals(mazeV.getMaze(), mazeVMock);
	}

	@Test
	public void testSuccessTrueFloor() {
		Boolean success = mazeH.hasFloorAt(0, 0);
		Assert.assertTrue(success);
	}

	@Test
	public void testSuccessFalseFloor() {
		Boolean success = mazeV.hasFloorAt(0, 0);
		Assert.assertTrue(success);
	}

	@Test
	public void testSuccessFalseWall() {
		Boolean success = mazeH.hasWallAt(0, 0);
		Assert.assertTrue(success);
	}

	@Test
	public void testSuccessTrueWall() {
		Boolean success = mazeV.hasWallAt(0, 0);
		Assert.assertTrue(success);
	}
	
	@Test
	public void testSuccessTrueLimits() {
		Boolean success = mazeV.outsideMazeLimits(1, 1);
		Assert.assertTrue(success);
		
		success = mazeH.outsideMazeLimits(2, 2);
		Assert.assertTrue(success);
	}
}
