package com.mac.mazer.items;

import com.mac.mazer.items.characters.Character;
import com.mac.mazer.items.characters.EnemiesHandler;
import com.mac.mazer.items.characters.Hero;
import com.mac.util.Util;

public class Game {

    private static final int MAZE_SIZE = 5;
    private static final int ENEMY_AMOUNT = 8;
    private static final int ENEMY_POWER = 20;
    private static final int HERO_POWER = 100;

    private final Coordinates boardSize;
    private final Maze maze;
    private EnemiesHandler enemies;
    private final Hero hero;

    public Game() {
        boardSize = new Coordinates(MAZE_SIZE, MAZE_SIZE);
        maze = new Maze(boardSize);
        enemies = new EnemiesHandler(ENEMY_AMOUNT, boardSize, ENEMY_POWER);
        hero = new Hero(HERO_POWER);
    }

    public void display() {
        Character[] heroArr = {hero};
        Character[] all = Util.concatenate(enemies.getEnemies(), heroArr);
        clearScreen();
        hero.displayCurrentStats();
        maze.display(all);
    }

    public void turnLeft() { hero.turnLeft(); }
    public void turnRight() { hero.turnRight(); }
    public void rotate180() { hero.rotate180(); }

    public void moveForward() {
        enemies = hero.moveForward(maze, enemies);
    }

    public boolean gameFinished() {
        Coordinates exit = new Coordinates(boardSize.x() - 1, boardSize.y() - 1);
        return hero.getCurrentCoordinates().equals(exit);
    }

    public void finishGame() {
        int score = hero.currentPower();
        System.out.printf("Your score is %d%n", score);
        System.out.println("You finished the puzzle!");
        if (score < 0) {
            System.out.println("...but with a negative score...so...haha!");
        }
        Util.pressAnyKey("Press any key to play again...");
    }

    private void clearScreen() {
        try {
            String os = System.getProperty("os.name");
            ProcessBuilder pb = os.contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            pb.inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
