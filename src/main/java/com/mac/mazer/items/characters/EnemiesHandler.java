package com.mac.mazer.items.characters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mac.mazer.items.Coordinates;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnemiesHandler {

    private List<Enemy> enemies = new ArrayList<>();

    public EnemiesHandler() {
    }

    public EnemiesHandler(int amount, Coordinates mazeSize, int maxPower) {
        this();

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

    public CollisionResult collide(Coordinates coordinates) {
        List<Enemy> remaining = new ArrayList<>(enemies);

        for (Enemy enemy : enemies) {
            if (enemy.isCharacterHere(coordinates)) {
                remaining.remove(enemy);

                EnemiesHandler updated = new EnemiesHandler();
                updated.enemies = remaining;

                return new CollisionResult(enemy, updated);
            }
        }

        return new CollisionResult(null, this);
    }

    @JsonIgnore
    public Character[] getEnemies() {
        return enemies.toArray(new Character[0]);
    }

    public List<Enemy> getEnemiesList() {
        return enemies;
    }

    public List<Coordinates> getCoordinates() {
        return enemies.stream()
                .flatMap(enemy -> enemy.getPositions().stream())
                .toList();
    }

    public record CollisionResult(Enemy enemy, EnemiesHandler remaining) {
        public boolean hasCollision() {
            return enemy != null;
        }
    }
}