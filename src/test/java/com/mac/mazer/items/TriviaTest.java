package com.mac.mazer.items;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TriviaTest {
	private Trivia trivia;

	@Before
	public void init() {
		trivia = new Trivia();
	}

	@Test
	public void testSuccessQuestionWithoutTopics() {
		Assert.assertTrue(trivia.getQuestion(null) != null);
	}

	@Test
	public void testSuccessQuestionWithTopics() {
		for (Trivia.Topic topic : Trivia.Topic.values()) {
			Assert.assertTrue(trivia.getQuestion(topic) != null);
		}
	}

	@Test
	public void testSuccessAnswer() {
		Assert.assertTrue(trivia.getAnswer() != null);
	}

}
