package com.mac.mazer;

import com.mac.mazer.items.Coordinates;
import com.mac.mazer.items.Maze;
import com.mac.mazer.items.characters.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testMoveForwardVertically() {
        hero.moveForward(mazeVertical, null);
        assertEquals(new Coordinates(0, 1), hero.getCurrentCoordinates());
    }

    @Test
    public void testMoveForwardHorizontally() {
        hero.turnLeft();
        hero.moveForward(mazeHorizontal, null);
        assertEquals(new Coordinates(1, 0), hero.getCurrentCoordinates());
    }
}
