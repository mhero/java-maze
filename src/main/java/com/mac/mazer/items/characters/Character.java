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

	public List<Coordinates> getPositions() {
		return positions;
	}

	public static String getSpace(Coordinates coordinates, Character... characters) {
		for (Character character : characters) {
			if (character.isCharacterHere(coordinates)) {
				return character.getLogo();
			}
		}
		return " ";
	}
}
