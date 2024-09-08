package com.mac.mazer.items.characters;

import com.mac.mazer.items.Coordinates;

import java.util.List;

public abstract class Character {
    protected List<Coordinates> positions;
    protected String logo;
    protected Integer power;

    public static String getSpace(Coordinates coordinates, Character... characters) {
        for (Character character : characters) {
            if (character.isCharacterHere(coordinates)) {
                return character.getLogo();
            }
        }
        return " ";
    }

    public boolean isCharacterHere(Coordinates coordinates) {
        return positions.contains(coordinates);
    }

    public String getLogo() {
        return logo;
    }

    public List<Coordinates> getPositions() {
        return positions;
    }

    public void updatePower(Enemy enemy, Boolean take) {
        if (take) {
            this.power -= enemy.power;
        } else {
            this.power += enemy.power;
        }
    }

    public Integer currentPower() {
        return this.power;
    }
}
