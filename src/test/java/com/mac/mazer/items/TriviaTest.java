package com.mac.mazer.items;

import com.mac.mazer.items.trivia.Question;
import com.mac.mazer.items.trivia.Trivia;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testQuestionHasOptionsAndCorrectAnswer() {
        Question q = trivia.getQuestion(null);
        assertFalse(q.optionAnswers().isEmpty());
        assertTrue(q.correctAnswer() >= 1);
        assertTrue(q.correctAnswer() <= q.optionAnswers().size());
    }

    @Test
    public void testAnswer() {
        assertNotNull(trivia.getAnswer());
        assertFalse(trivia.getAnswer().isBlank());
    }

    @Test
    public void testRandomTopicNeverNull() {
        for (int i = 0; i < 20; i++) {
            assertNotNull(Trivia.Topic.random());
        }
    }
}
