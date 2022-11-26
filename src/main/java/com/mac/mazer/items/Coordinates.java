package com.mac.mazer.items;

public record Coordinates(int x, int y) {
    public Coordinates() {
        this(0, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinates(int x1, int y1))) return false;
        return this.x == x1 && this.y == y1;
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
    }
}
