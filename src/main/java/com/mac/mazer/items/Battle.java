package com.mac.mazer.items;

import com.mac.mazer.items.characters.Enemy;
import com.mac.mazer.items.characters.Hero;
import com.mac.mazer.items.trivia.Question;
import com.mac.mazer.items.trivia.TriviaPort;
import com.mac.util.IOPort;

import java.util.List;
import java.util.Random;

/**
 * Handles trivia battle logic.
 * Knows nothing about enemies list management — that belongs to Game.
 */
public class Battle {

    private final TriviaPort trivia;
    private final IOPort io;
    private final Random random;

    public Battle(TriviaPort trivia, IOPort io) {
        this(trivia, io, new Random());
    }

    /**
     * Package-private constructor for tests — allows injecting deterministic Random.
     */
    Battle(TriviaPort trivia, IOPort io, Random random) {
        this.trivia = trivia;
        this.io = io;
        this.random = random;
    }

    /**
     * Runs one battle encounter. Returns the hero's updated power.
     */
    public int fight(Hero hero, Enemy enemy) {
        Question question = askQuestion();
        int answer = io.readInt();
        boolean won = resolveAnswer(answer, question.correctAnswer());
        int newPower = hero.powerAfterEncounter(enemy, !won);
        printBattleResult(enemy, !won);
        return newPower;
    }

    private Question askQuestion() {
        Question question = trivia.getQuestion(null);
        io.println(question.question());
        List<String> options = question.optionAnswers();
        for (int i = 0; i < options.size(); i++) {
            io.printf("%d. = %s%n", i + 1, options.get(i));
        }
        return question;
    }

    private boolean resolveAnswer(int userAnswer, int correctAnswer) {
        if (userAnswer == correctAnswer) return true;
        boolean lucky = random.nextBoolean();
        io.println(lucky ? trivia.getAnswer() : "NOPE!");
        return lucky;
    }

    private void printBattleResult(Enemy enemy, boolean lost) {
        io.printf("you %s %d points%n", lost ? "lost" : "won", enemy.currentPower());
        io.pressAnyKey("Press any key to continue...");
    }
}
