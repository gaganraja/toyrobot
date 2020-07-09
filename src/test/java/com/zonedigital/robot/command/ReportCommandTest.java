package com.zonedigital.robot.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.zonedigital.robot.ZoneToyRobot;
import com.zonedigital.robot.command.impl.ReportCommand;
import com.zonedigital.robot.command.output.RobotCommandOutput;
import com.zonedigital.robot.enums.ErrorCode;
import com.zonedigital.robot.enums.Facing;
import com.zonedigital.robot.utils.ZoneToyRobotUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ZoneToyRobotUtils.class)
public class ReportCommandTest {
	
	private ZoneToyRobot zoneToyRobot = new ZoneToyRobot();
	
	@Test
	public void testReportCommandFailed_ToyRobot_Not_Placed() {
		mockStatic(ZoneToyRobotUtils.class);
		ReportCommand reportCommand = new ReportCommand();
		when(ZoneToyRobotUtils.isRobotAlreadyPlaced(zoneToyRobot)).thenReturn(false);
		RobotCommandOutput reportCommandOutput = reportCommand.executeCommand(zoneToyRobot);
		assertFalse(reportCommandOutput.isSuccess());
		assertEquals(ErrorCode.NOT_PLACED, reportCommandOutput.getErrorCode());
	}
	
	@Test
	public void testReportCommandProvidesLatestToyRobotPosition() {
		zoneToyRobot.setCurrentFacing(Facing.NORTH);
		zoneToyRobot.setCurrentX(2);
		zoneToyRobot.setCurrentY(3);
		ReportCommand reportCommand= new ReportCommand();
		RobotCommandOutput reportCommandOutput = reportCommand.executeCommand(zoneToyRobot);
		assertEquals(2, reportCommandOutput.getCurrentX());
		assertEquals(3, reportCommandOutput.getCurrentY());
		assertEquals(Facing.NORTH, reportCommandOutput.getCurrentFacing());
	}

}
