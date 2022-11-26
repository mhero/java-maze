package com.mac.mazer.items;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinatesTest {

    private final Coordinates coordinates = new Coordinates(4, 5);

    @Test
    public void testGetters() {
        assertEquals(4, coordinates.x());
        assertEquals(5, coordinates.y());
    }

    @Test
    public void testEquality() {
        assertEquals(coordinates, new Coordinates(4, 5));
    }

    @Test
    public void testInequality() {
        assertNotEquals(coordinates, new Coordinates(4, 6));
    }

    @Test
    public void testHashCodeConsistency() {
        assertEquals(coordinates.hashCode(), new Coordinates(4, 5).hashCode());
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(new Coordinates(0, 0), new Coordinates());
    }

    @Test
    public void testToString() {
        assertTrue(coordinates.toString().contains("4"));
        assertTrue(coordinates.toString().contains("5"));
    }
}
