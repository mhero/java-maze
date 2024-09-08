package com.mac.mazer.items;

public class Coordinates {
    private Integer x;
    private Integer y;

    public Coordinates() {

    }

    public Coordinates(Integer x, Integer y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;

        if (object instanceof Coordinates) {
            isEqual = (this.x == ((Coordinates) object).x) && (this.y == ((Coordinates) object).y);
        }

        return isEqual;
    }

    @Override
    public String toString() {
        return "x: " + this.x + " y: " + this.y;
    }

}
