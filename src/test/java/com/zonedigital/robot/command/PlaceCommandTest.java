package com.zonedigital.robot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.zonedigital.robot.ZoneToyRobot;
import com.zonedigital.robot.command.impl.PlaceCommand;
import com.zonedigital.robot.command.output.RobotCommandOutput;
import com.zonedigital.robot.enums.ErrorCode;
import com.zonedigital.robot.enums.Facing;
import com.zonedigital.robot.movement.Table;
import com.zonedigital.robot.utils.ZoneToyRobotUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ZoneToyRobotUtils.class)
public class PlaceCommandTest {
	
	private ZoneToyRobot zoneToyRobot = new ZoneToyRobot();
	
	@Mock
	private Table table;
	
	@Test
	public void test_InvalidHorizontalCoordinates_PlaceCommandFailed() {
		
		PlaceCommand placeCommand = new PlaceCommand(-1, 2, Facing.EAST, table);
		when(table.isHorizantalCoordinateValid(-1)).thenReturn(false);
		RobotCommandOutput placeCommandOutput = placeCommand.executeCommand(zoneToyRobot);
		assertFalse(placeCommandOutput.isSuccess());
		assertEquals(ErrorCode.INVALID_X_Y, placeCommandOutput.getErrorCode());
	}
	
	@Test
	public void test_InvalidVerticalCoordinates_PlaceCommandFailed() {
		
		PlaceCommand placeCommand = new PlaceCommand(1, -2, Facing.EAST, table);
		when(table.isVerticalCoordinateValid(-2)).thenReturn(false);
		RobotCommandOutput placeCommandOutput = placeCommand.executeCommand(zoneToyRobot);
		assertFalse(placeCommandOutput.isSuccess());
		assertEquals(ErrorCode.INVALID_X_Y, placeCommandOutput.getErrorCode());
	}
	
	@Test
	public void test_ValidCoordinates_PlaceCommandSuccessful_ToyRobotPlaced() {
		mockStatic(ZoneToyRobotUtils.class);
		PlaceCommand placeCommand = new PlaceCommand(1, 1, Facing.EAST, table);
		when(table.isHorizantalCoordinateValid(1)).thenReturn(true);
		when(table.isVerticalCoordinateValid(1)).thenReturn(true);
		when(ZoneToyRobotUtils.isRobotAlreadyPlaced(zoneToyRobot)).thenReturn(false);
		List<List<Object>> tableTop = new ArrayList<List<Object>>();
		when(table.getTableTop()).thenReturn(tableTop);
		RobotCommandOutput placeCommandOutput = placeCommand.executeCommand(zoneToyRobot);
		assertTrue(placeCommandOutput.isSuccess());
	}
	
	@Test
	public void test_ValidCoordinates_SecondPlaceCommandSuccessful_ToyRobotPlacedToNewCoordinates() {
		mockStatic(ZoneToyRobotUtils.class);
		PlaceCommand placeCommand = new PlaceCommand(1, 1, Facing.EAST, table);
		when(table.isHorizantalCoordinateValid(1)).thenReturn(true);
		when(table.isVerticalCoordinateValid(1)).thenReturn(true);
		when(ZoneToyRobotUtils.isRobotAlreadyPlaced(zoneToyRobot)).thenReturn(true);
		List<List<Object>> tableTop = new ArrayList<List<Object>>();
		when(table.getTableTop()).thenReturn(tableTop);
		RobotCommandOutput placeCommandOutput = placeCommand.executeCommand(zoneToyRobot);
		assertTrue(placeCommandOutput.isSuccess());
	}

}
