package com.mac.mazer;

import com.mac.mazer.items.Game;
import com.mac.mazer.items.trivia.Trivia;
import com.mac.util.ConsoleIOPort;
import com.mac.util.IOPort;
import com.mac.util.JsonFileGameStore;

public class App {
    public static void main(String[] args) {
        IOPort io = new ConsoleIOPort();
        Trivia trivia = new Trivia();
        Game game = new Game(io, trivia);
        JsonFileGameStore store = new JsonFileGameStore("game.json");
        new MenuBuilder(io, game, store);
    }
}
