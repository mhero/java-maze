package com.mac.mazer.items;

import java.util.List;

public class Question {
    private final String question;
    private final List<String> optionAnswers;
    private final Integer correctAnswer;

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
