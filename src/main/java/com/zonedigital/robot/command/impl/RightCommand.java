package com.zonedigital.robot.command.impl;

import com.zonedigital.robot.ZoneToyRobot;
import com.zonedigital.robot.command.RobotCommand;
import com.zonedigital.robot.command.output.RobotCommandOutput;
import com.zonedigital.robot.enums.ClockwiseFacing;
import com.zonedigital.robot.enums.ErrorCode;
import com.zonedigital.robot.enums.Facing;
import com.zonedigital.robot.utils.ZoneToyRobotUtils;

public class RightCommand implements RobotCommand {

	public RobotCommandOutput executeCommand(ZoneToyRobot toyRobot) {
		RobotCommandOutput robotCommandOutput = new RobotCommandOutput();
		if(!ZoneToyRobotUtils.isRobotAlreadyPlaced(toyRobot)) {
			robotCommandOutput.setSuccess(false);
			robotCommandOutput.setErrorCode(ErrorCode.NOT_PLACED);
			return robotCommandOutput;
		}
		Facing currentFacing = toyRobot.getCurrentFacing();
		ClockwiseFacing[] clockwiseFacingvalues = ClockwiseFacing.values();
		for (ClockwiseFacing clockwiseFacingValue : clockwiseFacingvalues) {
			if(clockwiseFacingValue.name().equals(currentFacing.name())) 
			{
				String clockwiseFacing = clockwiseFacingValue.getClockwiseFacing();
				Facing[] facingValues = Facing.values();
				for (Facing facingValue : facingValues) {
					if(facingValue.name().equals(clockwiseFacing)) {
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

