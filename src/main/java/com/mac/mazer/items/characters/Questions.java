package com.mac.mazer.items.characters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Questions {

	List<Question> questions;
	List<String> answers;

	public Questions() {
		questions = new ArrayList<>();
		questions.add(new Question("Is Hary Potter a wizard or a cheff", Arrays.asList("wizard", "cheff"), 1));
		questions.add(new Question("Is cake a fruit or a desert", Arrays.asList("fruit", "desert"), 2));

		answers = Arrays.asList("is it? lol", "are you sure? I am asking...I'm not sure either");
	}

	public Question getQuestion() {
		Random random = new Random();
		return questions.get(random.nextInt(questions.size()));
	}

	public String getAnswer() {
		Random random = new Random();
		return answers.get(random.nextInt(answers.size()));
	}

}
