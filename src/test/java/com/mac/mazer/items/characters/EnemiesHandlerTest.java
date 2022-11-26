package com.mac.mazer.items.characters;

import com.mac.mazer.items.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemiesHandlerTest {

    private static final int AMOUNT = 5;
    private static final int POWER = 100;
    private static final Coordinates BOUNDS = new Coordinates(10, 10);
    private EnemiesHandler enemies;

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

    @Test
    public void testNoDuplicatePositions() {
        long distinct = enemies.getCoordinates().stream().distinct().count();
        assertEquals(AMOUNT, distinct);
    }

    @Test
    public void testCollideRemovesEnemy() {
        Enemy target = enemies.getEnemiesList().get(0);
        Coordinates pos = target.getPositions().get(0);

        EnemiesHandler.CollisionResult result = enemies.collide(pos);

        assertTrue(result.hasCollision());
        assertEquals(pos, result.enemy().getPositions().get(0));
        assertEquals(AMOUNT - 1, result.remaining().getEnemiesList().size());
    }

    @Test
    public void testCollideAtEmptySpaceIsNoop() {
        Coordinates empty = new Coordinates(0, 0); // hero start, never an enemy
        EnemiesHandler.CollisionResult result = enemies.collide(empty);

        assertFalse(result.hasCollision());
        assertNull(result.enemy());
        assertEquals(AMOUNT, result.remaining().getEnemiesList().size());
    }

    @Test
    public void testOriginalHandlerUnchangedAfterCollide() {
        Enemy target = enemies.getEnemiesList().get(0);
        enemies.collide(target.getPositions().get(0));
        // original enemies list must be unchanged (immutable copy semantics)
        assertEquals(AMOUNT, enemies.getEnemiesList().size());
    }
}
