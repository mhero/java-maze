package com.mac.roomba;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mac.mazer.items.Coordinates;
import com.mac.mazer.items.characters.Enemies;

public class EnemiesTest {
	private Enemies enemies;
	private Integer amount;
	private Integer power;
	private Coordinates coordinates;

	@Before
	public void init() {
		amount = 5;
		coordinates = new Coordinates(10, 10);
		power = 100;
	}

	@Test
	public void testSuccessCreate() {
		enemies = new Enemies(amount, coordinates, power);
		Boolean success = (enemies.getEnemies().length == amount);
		Assert.assertTrue(success);
	}

	@Test
	public void testSuccessBounds() {
		enemies = new Enemies(amount, coordinates, power);

		for (Coordinates enemy : enemies.getCoordinates()) {
			Boolean success = (enemy.getX() < coordinates.getX()) && (enemy.getY() < coordinates.getY());
			Assert.assertTrue(success);
		}

	}
}
