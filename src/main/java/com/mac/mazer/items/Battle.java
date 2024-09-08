package com.mac.mazer.items;

import com.mac.mazer.items.characters.EnemiesHandler;
import com.mac.mazer.items.characters.Enemy;
import com.mac.mazer.items.characters.Hero;
import com.mac.util.Util;

import java.util.Random;
import java.util.Scanner;

public class Battle {

    private Scanner sc;

    public EnemiesHandler checkEnemiesInteraction(EnemiesHandler enemies, Hero hero) {
        if (enemies == null)
            return enemies;

        Enemy collidedEnemy = enemies.collided(hero.getCurrentCoordinates());
        if (collidedEnemy != null) {
            Question question = askQuestion();
            Integer answer = getAnswer();
            Boolean correctAnswer = isCorrectAnswer(answer, question.getCorrectAnswer());
            hero.updatePower(collidedEnemy, !correctAnswer);
            stats(collidedEnemy, !correctAnswer);
        }
        return enemies;
    }

    private Question askQuestion() {
        Question question = (new Trivia()).getQuestion(null);

        System.out.println(question.getQuestion());
        for (int i = 0; i < question.getOptionAnswers().size(); i++) {
            System.out.printf("%d. = %s%n", i + 1, question.getOptionAnswers().get(i));
        }

        return question;
    }

    private Integer getAnswer() {
        sc = new Scanner(System.in);
        return sc.nextInt();
    }

    private Boolean isCorrectAnswer(Integer userAnswer, Integer correctAnswer) {
        if (userAnswer.equals(correctAnswer))
            return true;
        else
            return randomizeAnswerResult();
    }

    private Boolean randomizeAnswerResult() {
        Random rand = new Random();
        String answer = (new Trivia()).getAnswer();
        System.out.println();
        if (rand.nextBoolean()) {
            System.out.println(answer);
            return true;
        } else {
            System.out.println("NOPE!");
            return false;
        }
    }

    private void stats(Enemy enemy, Boolean answer) {
        System.out.printf("you %s %d points%n", answer ? "lost" : "won", enemy.currentPower());
        Util.pressAnyKey("Press any key to continue...");
    }
}
