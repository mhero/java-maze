package com.mac.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * In-memory IOPort for tests.
 * Feed inputs via addInput(), inspect outputs via getOutput().
 */
public class StubIOPort implements IOPort {

    private final Deque<Integer> inputs = new ArrayDeque<>();
    private final List<String> output = new ArrayList<>();

    public void addInput(int value) {
        inputs.add(value);
    }

    public List<String> getOutput() {
        return List.copyOf(output);
    }

    public String getLastLine() {
        return output.isEmpty() ? "" : output.get(output.size() - 1);
    }

    @Override
    public void print(String message) {
        output.add(message);
    }

    @Override
    public void println(String message) {
        output.add(message);
    }

    @Override
    public void printf(String format, Object... args) {
        output.add(String.format(format, args));
    }

    @Override
    public int readInt() {
        return inputs.poll();
    }

    @Override
    public void pressAnyKey(String message) {
        output.add(message);
    }

    @Override
    public void clearScreen() { /* no-op */ }
}
