package com.zonedigital.robot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zonedigital.robot.command.impl.PlaceCommand;
import com.zonedigital.robot.command.output.RobotCommandOutput;
import com.zonedigital.robot.enums.Facing;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ZoneToyRobotTest {
	
	@Mock
	private PlaceCommand mockPlaceCommand;
	
	private ZoneToyRobot zoneToyRobot = new ZoneToyRobot();
	
	private RobotCommandOutput robotCommandOutput = new RobotCommandOutput();
	
	@Test
	public void testRobotCommandOutputWhenCommandGivenToRobot_commandFailed() {
		robotCommandOutput.setSuccess(false);
		when(mockPlaceCommand.executeCommand(zoneToyRobot)).thenReturn(robotCommandOutput);
		RobotCommandOutput returnedRobotCommandOutput = zoneToyRobot.takeCommand(mockPlaceCommand);
		assertFalse(returnedRobotCommandOutput.isSuccess());
	}
	
	@Test
	public void testRobotCommandOutputWhenCommandGivenToRobot_commandPassed() {
		robotCommandOutput.setSuccess(true);
		when(mockPlaceCommand.executeCommand(zoneToyRobot)).thenReturn(robotCommandOutput);
		RobotCommandOutput returnedRobotCommandOutput = zoneToyRobot.takeCommand(mockPlaceCommand);
		assertTrue(returnedRobotCommandOutput.isSuccess());
	}
	
	@Test
	public void testCurrentX_Y_Facing_withoutPlacingToyRobot() {
		int currentX = zoneToyRobot.getCurrentX();
		assertEquals(-1, currentX);
		int currentY = zoneToyRobot.getCurrentY();
		assertEquals(-1, currentY);
		Facing currentFacing = zoneToyRobot.getCurrentFacing();
		assertNull(currentFacing);
	}
	
	@Test
	public void testCurrentX_Y_Facing_AfterPlacingToyRobot() {
		zoneToyRobot.setCurrentX(0);
		zoneToyRobot.setCurrentY(2);
		zoneToyRobot.setCurrentFacing(Facing.NORTH);
		int currentX = zoneToyRobot.getCurrentX();
		assertEquals(0, currentX);
		int currentY = zoneToyRobot.getCurrentY();
		assertEquals(2, currentY);
		Facing currentFacing = zoneToyRobot.getCurrentFacing();
		assertEquals(Facing.NORTH, currentFacing);
	}
	
}
