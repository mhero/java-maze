package com.mac.mazer.items.characters;

import com.mac.mazer.items.Coordinates;
import com.mac.mazer.items.maze.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HeroTest {

    private Hero hero;
    private Maze mazeVertical;
    private Maze mazeHorizontal;

    @BeforeEach
    public void init() {
        hero = new Hero(100);
        mazeVertical = new Maze(new Coordinates(1, 2));
        mazeHorizontal = new Maze(new Coordinates(2, 1));
    }

    @Test
    public void testStartsAtOrigin() {
        assertEquals(new Coordinates(0, 0), hero.getCurrentCoordinates());
    }

    @Test
    public void testStartsWithCorrectPower() {
        assertEquals(100, hero.currentPower());
    }

    @Test
    public void testDefaultDirectionIsSouth() {
        assertEquals(Hero.Direction.S, hero.getDirection());
    }

    @Test
    public void testMoveForwardVertically() {
        Hero.MoveResult result = hero.tryMoveForward(mazeVertical);
        assertTrue(result.moved());
        assertEquals(new Coordinates(0, 1), hero.getCurrentCoordinates());
    }

    @Test
    public void testMoveForwardHorizontally() {
        hero.turnLeft();
        Hero.MoveResult result = hero.tryMoveForward(mazeHorizontal);
        assertTrue(result.moved());
        assertEquals(new Coordinates(1, 0), hero.getCurrentCoordinates());
    }

    @Test
    public void testBlockedMoveDoesNotChangePosition() {
        // 1x1 maze — any move is blocked
        Maze tiny = new Maze(new Coordinates(1, 1));
        Hero.MoveResult result = hero.tryMoveForward(tiny);
        assertFalse(result.moved());
        assertEquals(new Coordinates(0, 0), hero.getCurrentCoordinates());
    }

    @Test
    public void testTurnLeft() {
        hero.turnLeft(); // S -> E
        assertEquals(Hero.Direction.E, hero.getDirection());
    }

    @Test
    public void testTurnRight() {
        hero.turnRight(); // S -> W
        assertEquals(Hero.Direction.W, hero.getDirection());
    }

    @Test
    public void testRotate180() {
        hero.rotate180(); // S -> N
        assertEquals(Hero.Direction.N, hero.getDirection());
    }

    @Test
    public void testApplyPowerDelta() {
        hero.applyPowerDelta(50);
        assertEquals(50, hero.currentPower());
    }

    @Test
    public void testPowerAfterEncounterWon() {
        Enemy enemy = new Enemy(new Coordinates(1, 1), 30);
        int updated = hero.powerAfterEncounter(enemy, false); // won
        assertEquals(130, updated);
    }

    @Test
    public void testPowerAfterEncounterLost() {
        Enemy enemy = new Enemy(new Coordinates(1, 1), 30);
        int updated = hero.powerAfterEncounter(enemy, true); // lost
        assertEquals(70, updated);
    }
}
