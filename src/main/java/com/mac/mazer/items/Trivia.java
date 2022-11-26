package com.mac.mazer.items;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Trivia {

    private static final Random RANDOM = new Random();

    private static final List<String> ANSWERS = List.of(
            "is it? lol",
            "are you sure? I am asking...I'm not sure either",
            "uhmmm..."
    );

    private static final Map<Topic, List<Question>> QUESTIONS_BY_TOPIC = Map.of(
            Topic.GAME_OF_THRONES, List.of(
                    new Question("Is Dany a wizard or a targaryen?", Arrays.asList("wizard", "targaryen"), 2),
                    new Question("Is Robert a chef or a king?", Arrays.asList("king", "chef"), 1)
            ),
            Topic.COOKING, List.of(
                    new Question("Is cake a fruit or a desert?", Arrays.asList("fruit", "desert"), 2),
                    new Question("Do you put salt or sugar in your coffee?", Arrays.asList("salt", "sugar"), 2)
            )
    );

    public Question getQuestion(Topic topic) {
        Topic resolved = (topic != null) ? topic : Topic.random();
        List<Question> questions = QUESTIONS_BY_TOPIC.get(resolved);
        return questions.get(RANDOM.nextInt(questions.size()));
    }

    public String getAnswer() {
        return ANSWERS.get(RANDOM.nextInt(ANSWERS.size()));
    }

    public enum Topic {
        GAME_OF_THRONES, COOKING;

        private static final Topic[] VALUES = values();
        private static final Random RNG = new Random();

        public static Topic random() {
            return VALUES[RNG.nextInt(VALUES.length)];
        }
    }
}
