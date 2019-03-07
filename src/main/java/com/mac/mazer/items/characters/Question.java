package com.mac.mazer.items.characters;

import java.util.List;

public class Question {
	private String question;
	private List<String> anwers;
	private Integer correctAnswer;

	public Question(String question, List<String> anwers, Integer correctAnswer) {
		super();
		this.question = question;
		this.anwers = anwers;
		this.correctAnswer = correctAnswer;
	}

	public String getQuestion() {
		return question;
	}

	public List<String> getAnwers() {
		return anwers;
	}

	public Integer getCorrectAnswer() {
		return correctAnswer;
	}

}
