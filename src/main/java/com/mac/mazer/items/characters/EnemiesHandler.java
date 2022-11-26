package com.mac.mazer.items.characters;

import com.mac.mazer.items.Coordinates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class EnemiesHandler {

    private List<Enemy> enemies;

    public EnemiesHandler() {
    }

    public EnemiesHandler(int amount, Coordinates mazeSize, int maxPower) {
        this.enemies = new ArrayList<>(amount);
        Random random = new Random();
        Set<Coordinates> occupied = new HashSet<>();

        while (enemies.size() < amount) {
            int x = random.nextInt(mazeSize.x() - 2) + 1;
            int y = random.nextInt(mazeSize.y() - 2) + 1;
            Coordinates pos = new Coordinates(x, y);
            if (occupied.add(pos)) {
                enemies.add(new Enemy(pos, random.nextInt(maxPower)));
            }
        }
    }

    public Character[] getEnemies() {
        return enemies.toArray(new Character[0]);
    }

    public List<Enemy> getEnemiesList() {
        return enemies;
    }

    public List<Coordinates> getCoordinates() {
        List<Coordinates> coords = new ArrayList<>();
        for (Enemy enemy : enemies) {
            coords.addAll(enemy.getPositions());
        }
        return coords;
    }

    public Enemy collided(Coordinates coordinates) {
        Enemy hit = null;
        List<Enemy> remaining = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (enemy.isCharacterHere(coordinates)) {
                hit = enemy;
            } else {
                remaining.add(enemy);
            }
        }
        this.enemies = remaining;
        return hit;
    }
}
