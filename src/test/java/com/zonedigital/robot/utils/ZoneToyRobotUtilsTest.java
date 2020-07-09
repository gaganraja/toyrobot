package com.zonedigital.robot.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.zonedigital.robot.Coordinates;
import com.zonedigital.robot.ZoneToyRobot;
import com.zonedigital.robot.enums.Facing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(JUnitPlatform.class)
public class ZoneToyRobotUtilsTest {
	
	
	@Test
	public void testYCoordinate_IncreasedBy1_IfToyRobotFacingNORTHIsMoved() {
		
		int currentX = 1;
		
		int currentY = 2;
		
		Coordinates nextCoordinatesToMove = ZoneToyRobotUtils.getNextCoordinatesToMove(Facing.NORTH, currentX, currentY);
		assertEquals(currentX, nextCoordinatesToMove.getxCoordinate());
		assertEquals(currentY + 1, nextCoordinatesToMove.getyCoordinate());
	}
	
	@Test
	public void testYCoordinate_DecreasedBy1_IfToyRobotFacingSOUTHIsMoved() {
		
		int currentX = 1;
		
		int currentY = 2;
		
		Coordinates nextCoordinatesToMove = ZoneToyRobotUtils.getNextCoordinatesToMove(Facing.SOUTH, currentX, currentY);
		assertEquals(currentX, nextCoordinatesToMove.getxCoordinate());
		assertEquals(currentY - 1, nextCoordinatesToMove.getyCoordinate());
	}
	
	@Test
	public void testXCoordinate_IncreasedBy1_IfToyRobotFacingEASTIsMoved() {
		
		int currentX = 1;
		
		int currentY = 2;
		
		Coordinates nextCoordinatesToMove = ZoneToyRobotUtils.getNextCoordinatesToMove(Facing.EAST, currentX, currentY);
		assertEquals(currentX + 1, nextCoordinatesToMove.getxCoordinate());
		assertEquals(currentY, nextCoordinatesToMove.getyCoordinate());
	}
	
	@Test
	public void testXCoordinate_DecreasedBy1_IfToyRobotFacingWESTIsMoved() {
		
		int currentX = 1;
		
		int currentY = 2;
		
		Coordinates nextCoordinatesToMove = ZoneToyRobotUtils.getNextCoordinatesToMove(Facing.WEST, currentX, currentY);
		assertEquals(currentX - 1, nextCoordinatesToMove.getxCoordinate());
		assertEquals(currentY, nextCoordinatesToMove.getyCoordinate());
	}
	
	@Test
	public void testToyRobotAlreadyPlaced_IfCoordinatesAreNonNegative()
	{
		ZoneToyRobot zoneToyRobot = new ZoneToyRobot();
		zoneToyRobot.setCurrentX(1);
		zoneToyRobot.setCurrentY(1);
		assertTrue(ZoneToyRobotUtils.isRobotAlreadyPlaced(zoneToyRobot));
	}
	
	@Test
	public void testToyRobotRemovedFromCurrentPlace()
	{
		List<List<Object>> tableTop = new ArrayList<List<Object>>();
		for (int i = 0 ; i <= 5 - 1; i++) {
			List<Object> objectList = new ArrayList<Object>();
			for (int j = 0 ; j <= 5 - 1; j++) {
				objectList.add(null);
			}
			tableTop.add(i, objectList);
		}
		ZoneToyRobot zoneToyRobot = new ZoneToyRobot();
		zoneToyRobot.setCurrentX(3);
		zoneToyRobot.setCurrentY(4);
		tableTop.get(3).add(4, zoneToyRobot);
		
		ZoneToyRobot zoneToyRobotBeforeMethodCall = (ZoneToyRobot)tableTop.get(3).get(4);
		assertNotNull(zoneToyRobotBeforeMethodCall);
		
		ZoneToyRobotUtils.removeRobotFromCurrentPlace(zoneToyRobot, tableTop);
		
		ZoneToyRobot zoneToyRobotAfterMethodCall = (ZoneToyRobot)tableTop.get(3).get(4);
		assertNull(zoneToyRobotAfterMethodCall);
	}
	
	@Test
	public void testToyRobotPlacedAtDesiredCoordinates()
	{
		List<List<Object>> tableTop = new ArrayList<List<Object>>();
		for (int i = 0 ; i <= 5 - 1; i++) {
			List<Object> objectList = new ArrayList<Object>();
			for (int j = 0 ; j <= 5 - 1; j++) {
				objectList.add(null);
			}
			tableTop.add(i, objectList);
		}
		
		ZoneToyRobot zoneToyRobotBeforeMethodCall = (ZoneToyRobot)tableTop.get(3).get(4);
		assertNull(zoneToyRobotBeforeMethodCall);
		
		ZoneToyRobot zoneToyRobot = new ZoneToyRobot();
		ZoneToyRobotUtils.placeToyRobot(zoneToyRobot, tableTop, 3, 4);
		
		
		ZoneToyRobot zoneToyRobotAfterMethodCall = (ZoneToyRobot)tableTop.get(3).get(4);
		assertNotNull(zoneToyRobotAfterMethodCall);
		assertEquals(zoneToyRobot, zoneToyRobotAfterMethodCall);
	}
	
	@Test
	public void testSetToyRobotCurrentCoordinates() {
		
		ZoneToyRobot zoneToyRobot = new ZoneToyRobot();
		
		assertEquals(-1, zoneToyRobot.getCurrentX());
		assertEquals(-1, zoneToyRobot.getCurrentY());
		assertNull(zoneToyRobot.getCurrentFacing());
		
		ZoneToyRobotUtils.setToyRobotCurrentCoordinates(zoneToyRobot, 3, 4, Facing.NORTH);
		
		assertEquals(3, zoneToyRobot.getCurrentX());
		assertEquals(4, zoneToyRobot.getCurrentY());
		assertEquals(Facing.NORTH, zoneToyRobot.getCurrentFacing());
	}

}
