package com.mac.mazer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Menu {
    private final String name;
    private final String text;
    private final LinkedHashMap<String, Runnable> actions = new LinkedHashMap<>();

    public Menu(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public void putAction(String label, Runnable action) {
        actions.put(label, action);
    }

    public String generateText() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(": ").append(text).append(":\n");
        List<String> labels = new ArrayList<>(actions.keySet());
        for (int i = 0; i < labels.size(); i++) {
            sb.append(String.format(" %d: %s%n", i + 1, labels.get(i)));
        }
        sb.append("Enter number and press return :)");
        return sb.toString();
    }

    public void executeAction(int choice) {
        int index = choice - 1;
        if (index < 0 || index >= actions.size()) {
            System.out.println("Ignoring menu choice: " + choice);
            return;
        }
        List<Runnable> runnable = new ArrayList<>(actions.values());
        runnable.get(index).run();
    }
}
