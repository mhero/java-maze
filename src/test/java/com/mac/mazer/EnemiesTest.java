package com.mac.mazer;

import com.mac.mazer.items.Coordinates;
import com.mac.mazer.items.characters.EnemiesHandler;
import com.mac.mazer.items.characters.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemiesTest {
    private EnemiesHandler enemies;
    private Integer amount;
    private Integer power;
    private Coordinates coordinates;

    @BeforeEach
    public void init() {
        amount = 5;
        coordinates = new Coordinates(10, 10);
        power = 100;
        enemies = new EnemiesHandler(amount, coordinates, power);
    }

    @Test
    public void testSuccessCreate() {
        Boolean success = (enemies.getEnemiesList().size() == amount);
        assertTrue(success);
    }

    @Test
    public void testSuccessPosition() {
        for (Enemy enemy : enemies.getEnemiesList()) {
            Boolean success = enemy.isCharacterHere(enemy.getPositions().get(0));
            assertTrue(success);
        }

    }

    @Test
    public void testSuccessBounds() {
        for (Coordinates enemy : enemies.getCoordinates()) {
            Boolean success = (enemy.getX() < coordinates.getX()) && (enemy.getY() < coordinates.getY());
            assertTrue(success);
        }

    }
}
