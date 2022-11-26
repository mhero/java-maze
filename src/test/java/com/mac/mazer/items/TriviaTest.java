package com.mac.mazer.items;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TriviaTest {

    private final Trivia trivia = new Trivia();

    @Test
    public void testQuestionWithoutTopic() {
        assertNotNull(trivia.getQuestion(null));
    }

    @Test
    public void testQuestionForEachTopic() {
        for (Trivia.Topic topic : Trivia.Topic.values()) {
            assertNotNull(trivia.getQuestion(topic));
        }
    }

    @Test
    public void testAnswer() {
        assertNotNull(trivia.getAnswer());
    }
}
