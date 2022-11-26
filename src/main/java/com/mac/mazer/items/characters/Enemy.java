package com.mac.mazer.items.characters;

import com.mac.mazer.items.Coordinates;

import java.util.List;

public class Enemy extends Character {

    public Enemy() {
    }

    public Enemy(Coordinates coordinates, int power) {
        this.positions = List.of(coordinates);
        this.power = power;
        this.logo = "X";
    }
}
