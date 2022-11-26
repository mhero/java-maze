package com.mac.mazer.items;

import com.mac.mazer.items.characters.EnemiesHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

    @Test
    public void testNewGameCreatesValidState() {
        GameState state = GameState.newGame();

        assertNotNull(state.hero());
        assertNotNull(state.maze());
        assertNotNull(state.enemies());
        assertNotNull(state.boardSize());
    }

    @Test
    public void testNewGameHeroStartsAtOrigin() {
        GameState state = GameState.newGame();
        assertEquals(new Coordinates(0, 0), state.hero().getCurrentCoordinates());
    }

    @Test
    public void testWithEnemiesReturnsNewState() {
        GameState original = GameState.newGame();
        EnemiesHandler newEnemies = new EnemiesHandler(
                2, new Coordinates(10, 10), 10
        );

        GameState updated = original.withEnemies(newEnemies);

        assertNotSame(original, updated);
        assertEquals(2, updated.enemies().getEnemiesList().size());
        // original is unchanged
        assertEquals(GameState.ENEMY_COUNT, original.enemies().getEnemiesList().size());
    }

    @Test
    public void testBoardSizeMatchesConstants() {
        GameState state = GameState.newGame();
        assertEquals(GameState.MAZE_SIZE, state.boardSize().x());
        assertEquals(GameState.MAZE_SIZE, state.boardSize().y());
    }
}
