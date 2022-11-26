package com.mac.mazer.items.characters;

import com.mac.mazer.items.Coordinates;

import java.util.List;

public abstract class Character {
    protected List<Coordinates> positions;
    protected String logo;
    protected int power;

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

    /**
     * Returns updated power after encounter.
     * Positive delta = won (gain); negative delta = lost (drain).
     */
    public int powerAfterEncounter(Enemy enemy, boolean lost) {
        return lost ? this.power - enemy.currentPower() : this.power + enemy.currentPower();
    }

    public int currentPower() {
        return power;
    }
}
