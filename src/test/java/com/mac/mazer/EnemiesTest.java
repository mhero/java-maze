package com.mac.mazer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mac.mazer.items.Coordinates;
import com.mac.mazer.items.characters.EnemyBuilder;
import com.mac.mazer.items.characters.Enemy;

public class EnemiesTest {
	private EnemyBuilder enemies;
	private Integer amount;
	private Integer power;
	private Coordinates coordinates;

	@Before
	public void init() {
		amount = 5;
		coordinates = new Coordinates(10, 10);
		power = 100;
		enemies = new EnemyBuilder(amount, coordinates, power);
	}

	@Test
	public void testSuccessCreate() {
		Boolean success = (enemies.getEnemies().size() == amount);
		Assert.assertTrue(success);
	}

	@Test
	public void testSuccessPosition() {
		for (Enemy enemy : enemies.getEnemies()) {
			Boolean success = enemy.isCharacterHere(enemy.getPositions().get(0));
			Assert.assertTrue(success);
		}

	}

	@Test
	public void testSuccessBounds() {
		for (Coordinates enemy : enemies.getCoordinates()) {
			Boolean success = (enemy.getX() < coordinates.getX()) && (enemy.getY() < coordinates.getY());
			Assert.assertTrue(success);
		}

	}
}
