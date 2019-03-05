package com.mac.mazer.items.characters;

import java.util.List;

import com.mac.mazer.items.Coordinates;

public abstract class Character {
	protected List<Coordinates> positions;
	protected String logo;
	protected Integer power;

	public boolean isCharacterHere(Coordinates coordinates) {
		return positions.contains(coordinates);
	}

	public String getLogo() {
		return logo;
	}

	public List<Coordinates> getItems() {
		return positions;
	}
}
