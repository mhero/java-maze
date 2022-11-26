package com.mac.mazer.items;

import java.util.List;

public record Question(String question, List<String> optionAnswers, int correctAnswer) {
    public Question(String question, List<String> optionAnswers, int correctAnswer) {
        this.question = question;
        this.optionAnswers = List.copyOf(optionAnswers);
        this.correctAnswer = correctAnswer;
    }
}
