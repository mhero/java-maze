package com.mac.mazer.items;

import java.util.Random;
import java.util.Scanner;

import com.mac.mazer.items.characters.EnemiesHandler;
import com.mac.mazer.items.characters.Enemy;
import com.mac.mazer.items.characters.Hero;
import com.mac.util.Util;

public class Battle {

	private Scanner sc;

	public EnemiesHandler checkEnemiesInteraction(EnemiesHandler enemies, Hero hero) {
		if (enemies == null)
			return enemies;

		Enemy collidedEnemy = enemies.collided(hero.getCurrentCoordinates());
		if (collidedEnemy != null) {
			Question question = askQuestion(collidedEnemy, hero);
			Boolean answer = getAnswer(question);
			hero.updatePower(collidedEnemy, !answer);
			stats(collidedEnemy, !answer);
		}
		return enemies;
	}

	private Question askQuestion(Enemy enemy, Hero hero) {
		Question question = (new Trivia()).getQuestion(null);

		System.out.println(question.getQuestion());
		for (int i = 0; i < question.getOptionAnswers().size(); i++) {
			System.out.println(String.format("%d. = %s", i + 1, question.getOptionAnswers().get(i)));
		}

		return question;
	}

	private Boolean getAnswer(Question question) {
		sc = new Scanner(System.in);
		return isCorrectAnswer(sc.nextInt(), question.getCorrectAnswer());
	}

	private Boolean isCorrectAnswer(Integer userAnswer, Integer correctAnswer) {
		String answer = (new Trivia()).getAnswer();
		Random rand = new Random();
		if (userAnswer.equals(correctAnswer)) {
			return true;
		}
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
		System.out.println(String.format("you %s %d points", answer ? "lost" : "won", enemy.currentPower()));
		Util.pressAnyKey("Press any key to continue...");
	}
}
