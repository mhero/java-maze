package com.mac.mazer.items.trivia;

/**
 * Trivia source abstraction.
 * Implementations: Trivia (in-memory), future: DB-backed, API-backed.
 */
public interface TriviaPort {
    Question getQuestion(Trivia.Topic topic);

    String getAnswer();
}
