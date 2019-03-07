package com.mac.mazer.items;

import java.util.Random;
import java.util.Scanner;

import com.mac.mazer.items.characters.EnemiesHandler;
import com.mac.mazer.items.characters.Enemy;
import com.mac.mazer.items.characters.Hero;

public class Battle {

	private Scanner sc;

	public EnemiesHandler checkEnemiesColision(EnemiesHandler enemies, Hero hero) {
		if (enemies == null)
			return enemies;
		Enemy enemy = enemies.removeEnemyAt(hero.getCurrentCoordinates());
		if (enemy != null) {
			question(enemy, hero);
		}
		return enemies;
	}

	private void question(Enemy enemy, Hero hero) {
		Question question = (new Trivia()).getQuestion(null);

		System.out.println(question.getQuestion());
		for (int i = 0; i < question.getAnwers().size(); i++) {
			System.out.println(String.format("%d. = %s", i + 1, question.getAnwers().get(i)));

		}
		sc = new Scanner(System.in);
		Boolean answer = !answer(sc.nextInt(), question.getCorrectAnswer());
		hero.updatePower(enemy, answer);
		stats(enemy, answer);
	}

	private Boolean answer(Integer userAnswer, Integer correctAnswer) {
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
		System.out.println("Press any key to continue...");
		sc = new Scanner(System.in);
		sc.nextLine();
	}
}
