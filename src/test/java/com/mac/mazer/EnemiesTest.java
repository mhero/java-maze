package com.mac.mazer;

import com.mac.mazer.items.Coordinates;
import com.mac.mazer.items.characters.EnemiesHandler;
import com.mac.mazer.items.characters.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemiesTest {

    private EnemiesHandler enemies;
    private static final int AMOUNT = 5;
    private static final int POWER = 100;
    private static final Coordinates BOUNDS = new Coordinates(10, 10);

    @BeforeEach
    public void init() {
        enemies = new EnemiesHandler(AMOUNT, BOUNDS, POWER);
    }

    @Test
    public void testCorrectCount() {
        assertEquals(AMOUNT, enemies.getEnemiesList().size());
    }

    @Test
    public void testEnemiesAtDeclaredPositions() {
        for (Enemy enemy : enemies.getEnemiesList()) {
            assertTrue(enemy.isCharacterHere(enemy.getPositions().get(0)));
        }
    }

    @Test
    public void testEnemiesWithinBounds() {
        for (Coordinates pos : enemies.getCoordinates()) {
            assertTrue(pos.x() < BOUNDS.x() && pos.y() < BOUNDS.y());
        }
    }
}
