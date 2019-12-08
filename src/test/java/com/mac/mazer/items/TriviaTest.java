package com.mac.mazer.items;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TriviaTest {
	private Trivia trivia;

	@BeforeEach
	public void init() {
		trivia = new Trivia();
	}

	@Test
	public void testSuccessQuestionWithoutTopics() {
		assertTrue(trivia.getQuestion(null) != null);
	}

	@Test
	public void testSuccessQuestionWithTopics() {
		for (Trivia.Topic topic : Trivia.Topic.values()) {
			assertTrue(trivia.getQuestion(topic) != null);
		}
	}

	@Test
	public void testSuccessAnswer() {
		assertTrue(trivia.getAnswer() != null);
	}

}
