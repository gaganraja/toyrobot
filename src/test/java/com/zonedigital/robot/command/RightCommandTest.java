package com.zonedigital.robot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.zonedigital.robot.ZoneToyRobot;
import com.zonedigital.robot.command.impl.RightCommand;
import com.zonedigital.robot.command.output.RobotCommandOutput;
import com.zonedigital.robot.enums.ErrorCode;
import com.zonedigital.robot.enums.Facing;
import com.zonedigital.robot.utils.ZoneToyRobotUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ZoneToyRobotUtils.class)
public class RightCommandTest {
	
	private ZoneToyRobot zoneToyRobot = new ZoneToyRobot();
	
	private RightCommand rightCommand = new RightCommand();
	
	
	@Test
	public void testRightCommandUnsuccessful_ToyRobot_Not_AlreadyPlaced()
	{
		mockStatic(ZoneToyRobotUtils.class);
		when(ZoneToyRobotUtils.isRobotAlreadyPlaced(zoneToyRobot)).thenReturn(false);
		RobotCommandOutput rightCommandOutput = rightCommand.executeCommand(zoneToyRobot);
		assertFalse(rightCommandOutput.isSuccess());
		assertEquals(ErrorCode.NOT_PLACED, rightCommandOutput.getErrorCode());
	}
	
	@Test
	public void testRightCommandSuccessful_ToyRobot_Rotatedclockwise() {
		mockStatic(ZoneToyRobotUtils.class);
		when(ZoneToyRobotUtils.isRobotAlreadyPlaced(zoneToyRobot)).thenReturn(true);
		zoneToyRobot.setCurrentFacing(Facing.NORTH);
		RobotCommandOutput rightCommandOutput = rightCommand.executeCommand(zoneToyRobot);
		assertTrue(rightCommandOutput.isSuccess());
		assertEquals(Facing.EAST, zoneToyRobot.getCurrentFacing());
	}
	
	@Test
	public void testRightCommandSuccessful_ToyRobot_Rotatedclockwise_CoordinatesRemainSame() {
		mockStatic(ZoneToyRobotUtils.class);
		when(ZoneToyRobotUtils.isRobotAlreadyPlaced(zoneToyRobot)).thenReturn(true);
		zoneToyRobot.setCurrentFacing(Facing.NORTH);
		zoneToyRobot.setCurrentX(1);
		zoneToyRobot.setCurrentY(2);
		rightCommand.executeCommand(zoneToyRobot);
		assertEquals(1, zoneToyRobot.getCurrentX());
		assertEquals(2, zoneToyRobot.getCurrentY());
	}


}
