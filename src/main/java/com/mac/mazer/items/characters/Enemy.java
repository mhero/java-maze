package com.mac.mazer.items.characters;

import java.util.ArrayList;

import com.mac.mazer.items.Coordinates;

public class Enemy extends Character {
	public Enemy(Coordinates coordinates, Integer power) {
		this.positions = new ArrayList<>();
		this.positions.add(coordinates);
		this.power = power;
		this.logo = "X";
	}
}
