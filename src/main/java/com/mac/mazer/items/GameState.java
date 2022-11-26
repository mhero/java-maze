package com.mac.mazer.items;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mac.mazer.items.characters.EnemiesHandler;
import com.mac.mazer.items.characters.Hero;
import com.mac.mazer.items.maze.Maze;

/**
 * Pure state bag. No logic, no I/O, fully serializable.
 * Passed around and returned by Game methods instead of Game mutating itself.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameState {

    public static final int MAZE_SIZE = 5;
    public static final int ENEMY_COUNT = 8;
    public static final int ENEMY_POWER = 20;
    public static final int HERO_POWER = 100;

    private Coordinates boardSize;
    private Maze maze;
    private EnemiesHandler enemies;
    private Hero hero;

    /**
     * For Jackson
     */
    public GameState() {
    }

    public GameState(Coordinates boardSize, Maze maze, EnemiesHandler enemies, Hero hero) {
        this.boardSize = boardSize;
        this.maze = maze;
        this.enemies = enemies;
        this.hero = hero;
    }

    public static GameState newGame() {
        Coordinates size = new Coordinates(MAZE_SIZE, MAZE_SIZE);
        return new GameState(
                size,
                new Maze(size),
                new EnemiesHandler(ENEMY_COUNT, size, ENEMY_POWER),
                new Hero(HERO_POWER)
        );
    }

    public Coordinates boardSize() {
        return boardSize;
    }

    public Maze maze() {
        return maze;
    }

    public EnemiesHandler enemies() {
        return enemies;
    }

    public Hero hero() {
        return hero;
    }

    /**
     * Returns a new state with the enemy list replaced.
     */
    public GameState withEnemies(EnemiesHandler updated) {
        return new GameState(boardSize, maze, updated, hero);
    }
}
