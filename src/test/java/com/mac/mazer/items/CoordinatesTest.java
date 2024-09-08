package com.mac.mazer.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinatesTest {

    private Coordinates coordinates;

    @BeforeEach
    public void init() {
        coordinates = new Coordinates(4, 5);
    }

    @Test
    public void testSuccessCreate() {
        assertEquals(4, (int) coordinates.getX());
        assertEquals(5, (int) coordinates.getY());
    }

    @Test
    public void testSuccessEquals() {
        assertEquals(coordinates, new Coordinates(4, 5));
    }
}
