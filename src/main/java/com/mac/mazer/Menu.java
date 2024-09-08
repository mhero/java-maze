package com.mac.mazer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Menu {
    private final String name;
    private final String text;
    private final LinkedHashMap<String, Runnable> actionsMap = new LinkedHashMap<>();

    public Menu(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public void putAction(String name, Runnable action) {
        actionsMap.put(name, action);
    }

    public String generateText() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(": ");
        sb.append(text).append(":\n");
        List<String> actionNames = new ArrayList<>(actionsMap.keySet());
        for (int i = 0; i < actionNames.size(); i++) {
            sb.append(String.format(" %d: %s%n", i + 1, actionNames.get(i)));
        }
        sb.append("Enter number and press return :)");
        return sb.toString();
    }

    public void executeAction(int actionNumber) {
        int effectiveActionNumber = actionNumber - 1;
        if (effectiveActionNumber < 0 || effectiveActionNumber >= actionsMap.size()) {
            System.out.println("Ignoring menu choice: " + actionNumber);
        } else {
            List<Runnable> actions = new ArrayList<>(actionsMap.values());
            actions.get(effectiveActionNumber).run();
        }
    }

}
