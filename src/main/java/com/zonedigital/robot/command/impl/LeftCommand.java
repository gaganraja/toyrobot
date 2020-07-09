package com.zonedigital.robot.command.impl;

import com.zonedigital.robot.ZoneToyRobot;
import com.zonedigital.robot.command.RobotCommand;
import com.zonedigital.robot.command.output.RobotCommandOutput;
import com.zonedigital.robot.enums.AntiClockwiseFacing;
import com.zonedigital.robot.enums.ErrorCode;
import com.zonedigital.robot.enums.Facing;
import com.zonedigital.robot.utils.ZoneToyRobotUtils;

public class LeftCommand implements RobotCommand {
	
	public RobotCommandOutput executeCommand(ZoneToyRobot toyRobot) {
		RobotCommandOutput robotCommandOutput = new RobotCommandOutput();
		if(!ZoneToyRobotUtils.isRobotAlreadyPlaced(toyRobot)) {
			robotCommandOutput.setSuccess(false);
			robotCommandOutput.setErrorCode(ErrorCode.NOT_PLACED);
			return robotCommandOutput;
		}
		Facing currentFacing = toyRobot.getCurrentFacing();
		AntiClockwiseFacing[] antiClockwiseFacingvalues = AntiClockwiseFacing.values();
		for (AntiClockwiseFacing antiClockwiseFacingValue : antiClockwiseFacingvalues) {
			if(antiClockwiseFacingValue.name().equals(currentFacing.name())) 
			{
				String antiClockwiseFacing = antiClockwiseFacingValue.getAnticlockwiseFacing();
				Facing[] facingValues = Facing.values();
				for (Facing facingValue : facingValues) {
					if(facingValue.name().equals(antiClockwiseFacing)) {
						toyRobot.setCurrentFacing(facingValue);
						robotCommandOutput.setSuccess(true);
						return robotCommandOutput;
					}
				}
			}
		}
		return null;
	}

}
