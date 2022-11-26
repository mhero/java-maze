package com.mac.util;

import java.util.Scanner;

/**
 * Terminal implementation of IOPort.
 * Only this class knows about System.in / System.out.
 */
public class ConsoleIOPort implements IOPort {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    @Override
    public int readInt() {
        return scanner.nextInt();
    }

    @Override
    public void pressAnyKey(String message) {
        System.out.println(message);
        scanner.nextLine();
    }

    @Override
    public void clearScreen() {
        try {
            String os = System.getProperty("os.name");
            ProcessBuilder pb = os.contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            pb.inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
