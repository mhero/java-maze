package com.mac.mazer.items;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoordinatesTest {

	private Coordinates coordinates;

	@BeforeEach
	public void init() {
		coordinates = new Coordinates(4, 5);
	}

	@Test
	public void testSuccessCreate() {
		assertTrue(coordinates.getX().equals(4));
		assertTrue(coordinates.getY().equals(5));
	}

	@Test
	public void testSuccessEquals() {
		assertTrue(coordinates.equals(new Coordinates(4, 5)));
	}
}
