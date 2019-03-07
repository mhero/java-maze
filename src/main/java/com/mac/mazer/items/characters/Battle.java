package com.mac.mazer.items.characters;

import java.util.Random;
import java.util.Scanner;

import com.mac.mazer.items.Coordinates;

public class Battle {

	private Scanner sc;

	public Enemies checkEnemiesColision(Enemies enemies, Hero hero, Coordinates heroCoordinates) {
		Enemy enemy = enemies.removeEnemyAt(heroCoordinates);
		if (enemy != null) {
			question(enemy, hero);
		}
		return enemies;
	}

	private void question(Enemy enemy, Hero hero) {
		Question question = (new Questions()).getQuestion();

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
		String answer = (new Questions()).getAnswer();
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
