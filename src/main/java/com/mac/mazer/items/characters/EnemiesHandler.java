package com.mac.mazer.items.characters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mac.mazer.items.Coordinates;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
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

    /**
     * Returns a new EnemiesHandler with the collided enemy removed, or this if no collision.
     */
    public CollisionResult collide(Coordinates coordinates) {
        Enemy hit = null;
        List<Enemy> remaining = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (enemy.isCharacterHere(coordinates)) {
                hit = enemy;
            } else {
                remaining.add(enemy);
            }
        }
        EnemiesHandler updated = new EnemiesHandler();
        updated.enemies = List.copyOf(remaining);
        return new CollisionResult(hit, updated);
    }

    public Character[] getEnemies() {
        return enemies.toArray(new Character[0]);
    }

    public List<Enemy> getEnemiesList() {
        return List.copyOf(enemies);
    }

    public List<Coordinates> getCoordinates() {
        List<Coordinates> coords = new ArrayList<>();
        for (Enemy enemy : enemies) {
            coords.addAll(enemy.getPositions());
        }
        return coords;
    }

    public record CollisionResult(Enemy enemy, EnemiesHandler remaining) {
        public boolean hasCollision() {
            return enemy != null;
        }
    }
}
