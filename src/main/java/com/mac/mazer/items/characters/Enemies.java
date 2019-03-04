package com.mac.mazer.items.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mac.mazer.items.Coordinates;

public class Enemies extends Character {
	public Enemies(int amount, Coordinates coordinates) {
		this.items = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			int x = new Random().nextInt(coordinates.getX());
			int y = new Random().nextInt(coordinates.getY());

			this.items.add(new Coordinates(x, y));
		}
		this.logo = "X";
	}

	public List<Coordinates> getEnemies() {
		return this.items;
	}

}
