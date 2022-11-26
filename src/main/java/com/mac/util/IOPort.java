package com.mac.util;

/**
 * All user I/O flows through this interface.
 * Swap the implementation for REST, WebSocket, test, etc.
 */
public interface IOPort {
    void print(String message);

    void println(String message);

    void printf(String format, Object... args);

    int readInt();

    void pressAnyKey(String message);

    void clearScreen();
}
