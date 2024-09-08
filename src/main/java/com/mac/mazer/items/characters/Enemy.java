package com.mac.mazer.items.characters;

import com.mac.mazer.items.Coordinates;

import java.util.ArrayList;

public class Enemy extends Character {
    public Enemy() {

    }

    public Enemy(Coordinates coordinates, Integer power) {
        this.positions = new ArrayList<>();
        this.positions.add(coordinates);
        this.power = power;
        this.logo = "X";
    }
}
