package com.mac.mazer.items.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mac.mazer.items.Coordinates;

public class Enemies {

	private List<Enemy> enemies;

	public Enemies() {

	}

	public Enemies(Integer amount, Coordinates coordinates, Integer maxPower) {
		this.enemies = new ArrayList<>();

		for (int i = 0; i < amount; i++) {
			int x = new Random().nextInt(coordinates.getX()) + 1;
			int y = new Random().nextInt(coordinates.getY()) + 1;
			int power = new Random().nextInt(maxPower);
			this.enemies.add(new Enemy(new Coordinates(x, y), power));
		}
	}

	public Character[] getEnemiesArray() {
		Character[] enemies = new Character[this.enemies.size()];
		for (int i = 0; i < this.enemies.size(); i++) {
			enemies[i] = this.enemies.get(i);
		}
		return enemies;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public List<Coordinates> getCoordinates() {
		List<Coordinates> coordinates = new ArrayList<>();
		for (Enemy enemy : enemies) {
			coordinates.addAll(enemy.getPositions());
		}
		return coordinates;
	}

	public Enemy removeEnemyAt(Coordinates coordinates) {
		Enemy removedEnemy = null;
		List<Enemy> enemies = new ArrayList<>();
		for (Enemy enemy : this.enemies) {
			if (!enemy.isCharacterHere(coordinates)) {
				enemies.add(enemy);
			} else {
				removedEnemy = enemy;
			}
		}
		this.enemies = enemies;
		return removedEnemy;
	}

}
