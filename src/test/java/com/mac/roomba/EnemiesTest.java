package com.mac.roomba;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mac.mazer.items.Coordinates;
import com.mac.mazer.items.characters.Enemies;

public class EnemiesTest {
	private Enemies enemies;
	private Integer amount;
	private Coordinates coordinates;

	@Before
	public void init() {
		amount = 5;
		coordinates = new Coordinates(10, 10);
	}

	@Test
	public void testSuccessCreate() {
		enemies = new Enemies(amount, coordinates);
		Boolean success = (enemies.getItems().size() == amount);
		Assert.assertTrue(success);
	}

	@Test
	public void testSuccessBounds() {
		enemies = new Enemies(amount, coordinates);

		for (Coordinates enemy : enemies.getItems()) {
			Boolean success = (enemy.getX() < coordinates.getX()) && (enemy.getY() < coordinates.getY());
			Assert.assertTrue(success);
		}

	}

	@Test
	public void testSuccessIsEnemy() {
		enemies = new Enemies(amount, coordinates);

		for (Coordinates enemy : enemies.getItems()) {
			Boolean success = enemies.isCharacterHere(enemy);
			Assert.assertTrue(success);
		}

	}
}
