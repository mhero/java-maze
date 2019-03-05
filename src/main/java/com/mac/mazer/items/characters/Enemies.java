package com.mac.mazer.items.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mac.mazer.items.Coordinates;

public class Enemies {

	private List<Enemy> enemies;
	private List<Coordinates> coordinates;

	public Enemies(Integer amount, Coordinates coordinates, Integer maxPower) {
		this.enemies = new ArrayList<>();
		this.coordinates = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			int x = new Random().nextInt(coordinates.getX());
			int y = new Random().nextInt(coordinates.getY());
			int power = new Random().nextInt(maxPower);
			this.coordinates.add(new Coordinates(x, y));
			this.enemies.add(new Enemy(new Coordinates(x, y), power));
		}
	}

	public Character[] getEnemies() {
		Character[] enemies = new Character[this.enemies.size()];
		for (int i = 0; i < this.enemies.size(); i++) {
			enemies[i] = this.enemies.get(i);
		}
		return enemies;
	}

	public List<Coordinates> getCoordinates() {
		return coordinates;
	}

}
