package com.mac.mazer.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Trivia {

	public enum Topic {
		GAME_OF_THRONES, COOKING;

		private static final List<Topic> VALUES = Arrays.asList(values());
		private static final int SIZE = VALUES.size();
		private static final Random RANDOM = new Random();

		public static Topic randomTopic() {
			return VALUES.get(RANDOM.nextInt(SIZE));
		}
	}

	List<String> answers;
	Map<Topic, List<Question>> questionsByTopic;

	public Trivia() {
		questionsByTopic = new HashMap<>();

		List<Question> gameOfThronesQuestions = new ArrayList<>();
		gameOfThronesQuestions
				.add(new Question("Is Dany a wizard or a targaryen?", Arrays.asList("wizard", "targaryen"), 2));
		gameOfThronesQuestions.add(new Question("Is Robert a chef or a king?", Arrays.asList("king", "chef"), 1));
		questionsByTopic.put(Topic.GAME_OF_THRONES, gameOfThronesQuestions);

		List<Question> cookingsQuestions = new ArrayList<>();
		cookingsQuestions.add(new Question("Is cake a fruit or a desert?", Arrays.asList("fruit", "desert"), 2));
		cookingsQuestions
				.add(new Question("Do you put salt or sugar in your coffee?", Arrays.asList("salt", "sugar"), 2));

		questionsByTopic.put(Topic.COOKING, cookingsQuestions);

		answers = Arrays.asList("is it? lol", "are you sure? I am asking...I'm not sure either", "uhmmm...");
	}

	public Question getQuestion(Topic topic) {
		if (topic == null) {
			topic = Topic.randomTopic();
		}
		Random random = new Random();
		List<Question> questions = questionsByTopic.get(topic);
		return questions.get(random.nextInt(questions.size()));
	}

	public String getAnswer() {
		Random random = new Random();
		return answers.get(random.nextInt(answers.size()));
	}

}
