package com.mac.mazer.items;

import com.mac.mazer.items.maze.Maze;
import com.mac.util.StubIOPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {

    private Maze mazeH;
    private Maze mazeV;

    @BeforeEach
    public void init() {
        mazeH = new Maze(new Coordinates(1, 2));
        mazeV = new Maze(new Coordinates(2, 1));
    }

    @Test
    public void testMazeGeneration() {
        assertArrayEquals(new int[][]{{2, 1}}, mazeH.getMaze());
        assertArrayEquals(new int[][]{{4}, {8}}, mazeV.getMaze());
    }

    @Test
    public void testHasFloorAt() {
        assertTrue(mazeH.hasFloorAt(0, 0));
        assertTrue(mazeV.hasFloorAt(0, 0));
    }

    @Test
    public void testHasWallAt() {
        assertTrue(mazeH.hasWallAt(0, 0));
        assertTrue(mazeV.hasWallAt(0, 0));
    }

    @Test
    public void testOutsideMazeLimits() {
        assertTrue(mazeV.outsideMazeLimits(1, 1));
        assertTrue(mazeH.outsideMazeLimits(2, 2));
        assertFalse(mazeV.outsideMazeLimits(0, 0));
        assertFalse(mazeH.outsideMazeLimits(0, 0));
    }

    @Test
    public void testDisplayProducesOutput() {
        StubIOPort io = new StubIOPort();
        mazeH.display(io);
        assertFalse(io.getOutput().isEmpty());
    }

    @Test
    public void testDisplayShowsExitMarker() {
        StubIOPort io = new StubIOPort();
        mazeH.display(io);
        boolean hasEnd = io.getOutput().stream().anyMatch(s -> s.contains("END"));
        assertTrue(hasEnd);
    }
}
