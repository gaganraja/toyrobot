package com.zonedigital.robot.movement;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(JUnitPlatform.class)
public class TableTest {
	
	@Test
	public void testTableTopCreationFailed_InvalidGridSizes() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		      new Table(0, -1);
		    });
	}
	
	@Test
	public void testTableTopCreationSuccessful_ValidGridSizes() {
		Table table = new Table(5, 5);
		List<List<Object>> tableTop = table.getTableTop();
		int horizontalMovement = tableTop.size();
		assertEquals(5, horizontalMovement);
		
		for (List<Object> verticalMovement : tableTop) {
			assertEquals(5, verticalMovement.size());
		}
	}
	
	@Test
	public void testTableTopXCoordinateInvalid_IfNegative_IfGreaterOrEqualToMaxHorizontalMovement() {
		Table table = new Table(5, 5);
		assertFalse(table.isHorizantalCoordinateValid(-1));
		assertFalse(table.isHorizantalCoordinateValid(5));
		assertFalse(table.isHorizantalCoordinateValid(6));
	}
	
	@Test
	public void testTableTopXCoordinateValid_IfPositive_IfLessThanMaxHorizontalMovement() {
		Table table = new Table(5, 5);
		assertTrue(table.isHorizantalCoordinateValid(3));
		assertTrue(table.isHorizantalCoordinateValid(4));
	}
	
	@Test
	public void testTableTopYCoordinateInvalid_IfNegative_IfGreaterOrEqualToMaxVerticalMovement() {
		Table table = new Table(5, 5);
		assertFalse(table.isVerticalCoordinateValid(-1));
		assertFalse(table.isVerticalCoordinateValid(5));
		assertFalse(table.isVerticalCoordinateValid(6));
	}
	
	@Test
	public void testTableTopYCoordinateValid_IfPositive_IfLessThanMaxVerticalMovement() {
		Table table = new Table(5, 5);
		assertTrue(table.isVerticalCoordinateValid(3));
		assertTrue(table.isVerticalCoordinateValid(4));
	}
	
}
