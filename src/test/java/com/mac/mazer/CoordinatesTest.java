package com.mac.mazer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mac.mazer.items.Coordinates;

public class CoordinatesTest {

	private Coordinates coordinates;

	@Before
	public void init() {
		coordinates = new Coordinates(4, 5);
	}

	@Test
	public void testSuccessCreate() {
		Assert.assertTrue(coordinates.getX().equals(4));
		Assert.assertTrue(coordinates.getY().equals(5));
	}

	@Test
	public void testSuccessEquals() {
		Assert.assertTrue(coordinates.equals(new Coordinates(4, 5)));
	}
}
