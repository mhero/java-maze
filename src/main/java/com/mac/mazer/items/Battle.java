package com.mac.mazer.items;

import com.mac.mazer.items.characters.EnemiesHandler;
import com.mac.mazer.items.characters.Enemy;
import com.mac.mazer.items.characters.Hero;
import java.util.List;
import com.mac.util.Util;

import java.util.Random;
import java.util.Scanner;

public class Battle {

    public EnemiesHandler checkEnemiesInteraction(EnemiesHandler enemies, Hero hero) {
        if (enemies == null) return null;

        Enemy collidedEnemy = enemies.collided(hero.getCurrentCoordinates());
        if (collidedEnemy != null) {
            Question question = askQuestion();
            int answer = readAnswer();
            boolean won = resolveAnswer(answer, question.correctAnswer());
            hero.updatePower(collidedEnemy, !won);
            printBattleResult(collidedEnemy, !won);
        }
        return enemies;
    }

    private Question askQuestion() {
        Question question = new Trivia().getQuestion(null);
        System.out.println(question.question());
        List<String> options = question.optionAnswers();
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("%d. = %s%n", i + 1, options.get(i));
        }
        return question;
    }

    private int readAnswer() {
        return new Scanner(System.in).nextInt();
    }

    private boolean resolveAnswer(int userAnswer, int correctAnswer) {
        if (userAnswer == correctAnswer) return true;
        boolean lucky = new Random().nextBoolean();
        System.out.println(lucky ? new Trivia().getAnswer() : "NOPE!");
        return lucky;
    }

    private void printBattleResult(Enemy enemy, boolean lost) {
        System.out.printf("you %s %d points%n", lost ? "lost" : "won", enemy.currentPower());
        Util.pressAnyKey("Press any key to continue...");
    }
}
