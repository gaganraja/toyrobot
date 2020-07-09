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
import com.zonedigital.robot.command.impl.LeftCommand;
import com.zonedigital.robot.command.output.RobotCommandOutput;
import com.zonedigital.robot.enums.ErrorCode;
import com.zonedigital.robot.enums.Facing;
import com.zonedigital.robot.utils.ZoneToyRobotUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ZoneToyRobotUtils.class)
public class LeftCommandTest {
	
	private ZoneToyRobot zoneToyRobot = new ZoneToyRobot();
	
	private LeftCommand leftCommand = new LeftCommand();
	
	
	@Test
	public void testLeftCommandUnsuccessful_ToyRobot_Not_AlreadyPlaced()
	{
		mockStatic(ZoneToyRobotUtils.class);
		when(ZoneToyRobotUtils.isRobotAlreadyPlaced(zoneToyRobot)).thenReturn(false);
		RobotCommandOutput leftCommandOutput = leftCommand.executeCommand(zoneToyRobot);
		assertFalse(leftCommandOutput.isSuccess());
		assertEquals(ErrorCode.NOT_PLACED, leftCommandOutput.getErrorCode());
	}
	
	@Test
	public void testLeftCommandSuccessful_ToyRobot_RotatedAnticlockwise() {
		mockStatic(ZoneToyRobotUtils.class);
		when(ZoneToyRobotUtils.isRobotAlreadyPlaced(zoneToyRobot)).thenReturn(true);
		zoneToyRobot.setCurrentFacing(Facing.NORTH);
		RobotCommandOutput leftCommandOutput = leftCommand.executeCommand(zoneToyRobot);
		assertTrue(leftCommandOutput.isSuccess());
		assertEquals(Facing.WEST, zoneToyRobot.getCurrentFacing());
	}
	
	@Test
	public void testLeftCommandSuccessful_ToyRobot_RotatedAnticlockwise_CoordinatesRemainSame() {
		mockStatic(ZoneToyRobotUtils.class);
		when(ZoneToyRobotUtils.isRobotAlreadyPlaced(zoneToyRobot)).thenReturn(true);
		zoneToyRobot.setCurrentFacing(Facing.NORTH);
		zoneToyRobot.setCurrentX(1);
		zoneToyRobot.setCurrentY(2);
		leftCommand.executeCommand(zoneToyRobot);
		assertEquals(1, zoneToyRobot.getCurrentX());
		assertEquals(2, zoneToyRobot.getCurrentY());
	}

}
