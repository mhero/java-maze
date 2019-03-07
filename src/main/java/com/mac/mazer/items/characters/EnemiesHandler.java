package com.mac.mazer.items.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mac.mazer.items.Coordinates;

public class EnemiesHandler {

	private List<Enemy> enemies;

	public EnemiesHandler() {

	}

	public EnemiesHandler(Integer amount, Coordinates mazeCoordinates, Integer maxPower) {
		this.enemies = new ArrayList<>();

		for (int i = 0; i < amount;) {
			int x = new Random().nextInt(mazeCoordinates.getX() - 2) + 1;
			int y = new Random().nextInt(mazeCoordinates.getY() - 2) + 1;
			int power = new Random().nextInt(maxPower);
			if (!getCoordinates().contains(new Coordinates(x, y))) {
				this.enemies.add(new Enemy(new Coordinates(x, y), power));
				i++;
			}
		}
	}

	public Character[] getEnemies() {
		Character[] enemies = new Character[this.enemies.size()];
		for (int i = 0; i < this.enemies.size(); i++) {
			enemies[i] = this.enemies.get(i);
		}
		return enemies;
	}

	public List<Enemy> getEnemiesList() {
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
