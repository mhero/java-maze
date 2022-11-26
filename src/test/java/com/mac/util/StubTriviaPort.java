package com.mac.util;

import com.mac.mazer.items.trivia.Question;
import com.mac.mazer.items.trivia.Trivia;
import com.mac.mazer.items.trivia.TriviaPort;

import java.util.List;

/**
 * Deterministic trivia: always returns the same question with correctAnswer = 1.
 */
public class StubTriviaPort implements TriviaPort {

    public static final int CORRECT_ANSWER = 1;
    private static final Question FIXED = new Question(
            "Is water wet?", List.of("yes", "no"), CORRECT_ANSWER
    );

    @Override
    public Question getQuestion(Trivia.Topic topic) {
        return FIXED;
    }

    @Override
    public String getAnswer() {
        return "lucky!";
    }
}
