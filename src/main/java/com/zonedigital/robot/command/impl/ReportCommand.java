package com.zonedigital.robot.command.impl;

import com.zonedigital.robot.ZoneToyRobot;
import com.zonedigital.robot.command.RobotCommand;
import com.zonedigital.robot.command.output.RobotCommandOutput;
import com.zonedigital.robot.enums.ErrorCode;
import com.zonedigital.robot.utils.ZoneToyRobotUtils;

public class ReportCommand implements RobotCommand {

	public RobotCommandOutput executeCommand(ZoneToyRobot toyRobot) {
		RobotCommandOutput robotCommandOutput = new RobotCommandOutput();
		if(!ZoneToyRobotUtils.isRobotAlreadyPlaced(toyRobot)) {
			robotCommandOutput.setSuccess(false);
			robotCommandOutput.setErrorCode(ErrorCode.NOT_PLACED);
			return robotCommandOutput;
		}
		robotCommandOutput.setSuccess(true);
		robotCommandOutput.setCurrentX(toyRobot.getCurrentX());
		robotCommandOutput.setCurrentY(toyRobot.getCurrentY());
		robotCommandOutput.setCurrentFacing(toyRobot.getCurrentFacing());
		return robotCommandOutput;
	}

}
