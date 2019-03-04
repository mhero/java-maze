package com.mac.mazer.items.characters;

import java.util.List;

import com.mac.mazer.items.Coordinates;

public class Character {
	protected List<Coordinates> items;
	protected String logo;

	public boolean isCharacterHere(Coordinates coordinates) {
		return items.contains(coordinates);
	}

	public String getLogo() {
		return logo;
	}
}
