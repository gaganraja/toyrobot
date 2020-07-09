package com.zonedigital.robot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.zonedigital.robot.Coordinates;
import com.zonedigital.robot.ZoneToyRobot;
import com.zonedigital.robot.command.impl.MoveCommand;
import com.zonedigital.robot.command.output.RobotCommandOutput;
import com.zonedigital.robot.enums.ErrorCode;
import com.zonedigital.robot.enums.Facing;
import com.zonedigital.robot.movement.Table;
import com.zonedigital.robot.utils.ZoneToyRobotUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ZoneToyRobotUtils.class)
public class MoveCommandTest {
	
	private ZoneToyRobot zoneToyRobot = new ZoneToyRobot();
	
	@Mock
	private Table table;
	
	@Test
	public void testMoveCommandFailed_ToyRobot_Not_Placed() {
		mockStatic(ZoneToyRobotUtils.class);
		MoveCommand moveCommand = new MoveCommand(table);
		when(ZoneToyRobotUtils.isRobotAlreadyPlaced(zoneToyRobot)).thenReturn(false);
		RobotCommandOutput moveCommandOutput = moveCommand.executeCommand(zoneToyRobot);
		assertFalse(moveCommandOutput.isSuccess());
		assertEquals(ErrorCode.NOT_PLACED, moveCommandOutput.getErrorCode());
	}
	
	@Test
	public void testMoveCommandFailed_ToyRobot_NextHorizontalCoordinateInvalid() {
		mockStatic(ZoneToyRobotUtils.class);
		MoveCommand moveCommand = new MoveCommand(table);
		when(ZoneToyRobotUtils.isRobotAlreadyPlaced(zoneToyRobot)).thenReturn(true);
		zoneToyRobot.setCurrentFacing(Facing.EAST);
		zoneToyRobot.setCurrentX(4);
		zoneToyRobot.setCurrentY(1);
		Coordinates coordinates = new Coordinates();
		coordinates.setxCoordinate(5);
		coordinates.setyCoordinate(1);
		when(ZoneToyRobotUtils.getNextCoordinatesToMove(Facing.EAST, 4, 1)).thenReturn(coordinates);
		when(table.isHorizantalCoordinateValid(5)).thenReturn(false);
		RobotCommandOutput moveCommandOutput = moveCommand.executeCommand(zoneToyRobot);
		assertFalse(moveCommandOutput.isSuccess());
		assertEquals(ErrorCode.INVALID_X_Y, moveCommandOutput.getErrorCode());
	}
	
	@Test
	public void testMoveCommandFailed_ToyRobot_NextVerticalCoordinateInvalid() {
		mockStatic(ZoneToyRobotUtils.class);
		MoveCommand moveCommand = new MoveCommand(table);
		when(ZoneToyRobotUtils.isRobotAlreadyPlaced(zoneToyRobot)).thenReturn(true);
		zoneToyRobot.setCurrentFacing(Facing.NORTH);
		zoneToyRobot.setCurrentX(1);
		zoneToyRobot.setCurrentY(4);
		Coordinates coordinates = new Coordinates();
		coordinates.setxCoordinate(1);
		coordinates.setyCoordinate(5);
		when(ZoneToyRobotUtils.getNextCoordinatesToMove(Facing.NORTH, 1, 4)).thenReturn(coordinates);
		when(table.isVerticalCoordinateValid(5)).thenReturn(false);
		RobotCommandOutput moveCommandOutput = moveCommand.executeCommand(zoneToyRobot);
		assertFalse(moveCommandOutput.isSuccess());
		assertEquals(ErrorCode.INVALID_X_Y, moveCommandOutput.getErrorCode());
	}
	
	@Test
	public void testMoveCommandSuccess_ToyRobot_ValidCoordinate_AlreadyPlaced() {
		mockStatic(ZoneToyRobotUtils.class);
		MoveCommand moveCommand = new MoveCommand(table);
		when(ZoneToyRobotUtils.isRobotAlreadyPlaced(zoneToyRobot)).thenReturn(true);
		zoneToyRobot.setCurrentFacing(Facing.NORTH);
		zoneToyRobot.setCurrentX(1);
		zoneToyRobot.setCurrentY(3);
		Coordinates coordinates = new Coordinates();
		coordinates.setxCoordinate(1);
		coordinates.setyCoordinate(4);
		when(ZoneToyRobotUtils.getNextCoordinatesToMove(Facing.NORTH, 1, 3)).thenReturn(coordinates);
		when(table.isVerticalCoordinateValid(4)).thenReturn(true);
		when(table.isHorizantalCoordinateValid(1)).thenReturn(true);
		RobotCommandOutput moveCommandOutput = moveCommand.executeCommand(zoneToyRobot);
		assertTrue(moveCommandOutput.isSuccess());
	}

}
