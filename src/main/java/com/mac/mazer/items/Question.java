package com.mac.mazer.items;

import java.util.List;

public class Question {
	private String question;
	private List<String> optionAnswers;
	private Integer correctAnswer;

	public Question(String question, List<String> optionAnswers, Integer correctAnswer) {
		super();
		this.question = question;
		this.optionAnswers = optionAnswers;
		this.correctAnswer = correctAnswer;
	}

	public String getQuestion() {
		return question;
	}

	public List<String> getOptionAnswers() {
		return optionAnswers;
	}

	public Integer getCorrectAnswer() {
		return correctAnswer;
	}

}
