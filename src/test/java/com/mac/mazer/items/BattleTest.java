package com.mac.mazer.items;

import com.mac.mazer.items.characters.Enemy;
import com.mac.mazer.items.characters.Hero;
import com.mac.util.StubIOPort;
import com.mac.util.StubTriviaPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BattleTest {

    private StubIOPort io;
    private StubTriviaPort trivia;
    private Hero hero;
    private Enemy enemy;

    @BeforeEach
    public void init() {
        io = new StubIOPort();
        trivia = new StubTriviaPort();
        hero = new Hero(100);
        enemy = new Enemy(new Coordinates(1, 1), 30);
    }

    @Test
    public void testCorrectAnswerGrantsPower() {
        io.addInput(StubTriviaPort.CORRECT_ANSWER);
        Battle battle = new Battle(trivia, io);

        int newPower = battle.fight(hero, enemy);

        assertEquals(130, newPower); // 100 + 30
    }

    @Test
    public void testWrongAnswerAndUnluckyDrainsPower() {
        io.addInput(99); // wrong
        // force Random to always return false (unlucky)
        Battle battle = new Battle(trivia, io, new Random() {
            @Override
            public boolean nextBoolean() {
                return false;
            }
        });

        int newPower = battle.fight(hero, enemy);

        assertEquals(70, newPower); // 100 - 30
    }

    @Test
    public void testWrongAnswerButLuckyGrantsPower() {
        io.addInput(99); // wrong
        // force Random to always return true (lucky)
        Battle battle = new Battle(trivia, io, new Random() {
            @Override
            public boolean nextBoolean() {
                return true;
            }
        });

        int newPower = battle.fight(hero, enemy);

        assertEquals(130, newPower); // 100 + 30
    }

    @Test
    public void testOutputContainsQuestion() {
        io.addInput(StubTriviaPort.CORRECT_ANSWER);
        new Battle(trivia, io).fight(hero, enemy);

        assertTrue(io.getOutput().stream().anyMatch(s -> s.contains("Is water wet?")));
    }

    @Test
    public void testOutputContainsBattleResult() {
        io.addInput(StubTriviaPort.CORRECT_ANSWER);
        new Battle(trivia, io).fight(hero, enemy);

        assertTrue(io.getOutput().stream().anyMatch(s -> s.contains("won") || s.contains("lost")));
    }
}
