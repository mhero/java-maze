package com.mac.mazer.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TriviaTest {
    private Trivia trivia;

    @BeforeEach
    public void init() {
        trivia = new Trivia();
    }

    @Test
    public void testSuccessQuestionWithoutTopics() {
        assertNotNull(trivia.getQuestion(null));
    }

    @Test
    public void testSuccessQuestionWithTopics() {
        for (Trivia.Topic topic : Trivia.Topic.values()) {
            assertNotNull(trivia.getQuestion(topic));
        }
    }

    @Test
    public void testSuccessAnswer() {
        assertNotNull(trivia.getAnswer());
    }

}
