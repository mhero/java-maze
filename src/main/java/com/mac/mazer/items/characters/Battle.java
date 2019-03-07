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
		hero.updatePower(enemy, !answer(sc.nextInt(), question.getCorrectAnswer()));
	}

	private Boolean answer(Integer userAnswer, Integer correctAnswer) {
		String answer = (new Questions()).getAnswer();
		Random rand = new Random();
		if (userAnswer.equals(correctAnswer)) {
			return true;
		}
		if (rand.nextBoolean()) {
			System.out.println(answer);
			return true;
		} else {
			System.out.println("NOPE!");
			return false;
		}
	}
}
