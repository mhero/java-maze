package com.mac.mazer.items;

import com.mac.mazer.items.characters.Character;
import com.mac.mazer.items.characters.EnemiesHandler;
import com.mac.mazer.items.characters.Hero;
import com.mac.mazer.items.trivia.TriviaPort;
import com.mac.util.IOPort;
import com.mac.util.Util;

/**
 * Stateless game service. Every method takes a GameState and returns a new one.
 * Safe to call from multiple threads — no shared mutable state.
 */
public class Game {

    private final IOPort io;
    private final Battle battle;

    public Game(IOPort io, TriviaPort trivia) {
        this.io = io;
        this.battle = new Battle(trivia, io);
    }

    /**
     * Visible for tests.
     */
    Game(IOPort io, Battle battle) {
        this.io = io;
        this.battle = battle;
    }

    public void display(GameState state) {
        Hero hero = state.hero();
        Character[] heroArr = {hero};
        Character[] all = Util.concatenate(state.enemies().getEnemies(), heroArr);
        io.clearScreen();
        io.println("hero power: " + hero.currentPower());
        io.println("hero position: " + hero.getCurrentCoordinates());
        state.maze().display(io, all);
    }

    public GameState turnLeft(GameState state) {
        state.hero().turnLeft();
        return state;
    }

    public GameState turnRight(GameState state) {
        state.hero().turnRight();
        return state;
    }

    public GameState rotate180(GameState state) {
        state.hero().rotate180();
        return state;
    }

    public GameState moveForward(GameState state) {
        Hero hero = state.hero();
        Hero.MoveResult move = hero.tryMoveForward(state.maze());

        if (!move.moved()) {
            io.println("Invalid move");
            return state;
        }

        EnemiesHandler.CollisionResult collision = state.enemies().collide(hero.getCurrentCoordinates());
        if (collision.hasCollision()) {
            int newPower = battle.fight(hero, collision.enemy());
            hero.applyPowerDelta(newPower);
            return state.withEnemies(collision.remaining());
        }

        return state;
    }

    public boolean isFinished(GameState state) {
        Coordinates exit = new Coordinates(state.boardSize().x() - 1, state.boardSize().y() - 1);
        return state.hero().getCurrentCoordinates().equals(exit);
    }

    public void showFinishScreen(GameState state) {
        int score = state.hero().currentPower();
        io.printf("Your score is %d%n", score);
        io.println("You finished the puzzle!");
        if (score < 0) {
            io.println("...but with a negative score...so...haha!");
        }
        io.pressAnyKey("Press any key to play again...");
    }
}
