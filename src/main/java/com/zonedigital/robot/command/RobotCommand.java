package com.zonedigital.robot.command;

import com.zonedigital.robot.ZoneToyRobot;
import com.zonedigital.robot.command.output.RobotCommandOutput;

public interface RobotCommand {
	
	RobotCommandOutput executeCommand(ZoneToyRobot toyRobot);

}
