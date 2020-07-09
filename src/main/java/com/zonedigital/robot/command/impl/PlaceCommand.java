package com.zonedigital.robot.command.impl;

import com.zonedigital.robot.ZoneToyRobot;
import com.zonedigital.robot.command.RobotCommand;
import com.zonedigital.robot.command.output.RobotCommandOutput;
import com.zonedigital.robot.enums.ErrorCode;
import com.zonedigital.robot.enums.Facing;
import com.zonedigital.robot.movement.Table;
import com.zonedigital.robot.utils.ZoneToyRobotUtils;

public class PlaceCommand implements RobotCommand {
	
	private int placeX;
	
	private int placeY;
	
	private Facing facing;
	
	private Table table;
	
	public PlaceCommand (int placeX, int placeY, Facing facing, Table table) {
		this.placeX = placeX;
		this.placeY = placeY;
		this.facing = facing;
		this.table = table;
	}

	public RobotCommandOutput executeCommand(ZoneToyRobot toyRobot) {
		RobotCommandOutput robotCommandOutput = new RobotCommandOutput();
		if(!table.isHorizantalCoordinateValid(this.placeX) || !table.isVerticalCoordinateValid(this.placeY)) {
			robotCommandOutput.setSuccess(false);
			robotCommandOutput.setErrorCode(ErrorCode.INVALID_X_Y);
			return robotCommandOutput;
		}
		if(!ZoneToyRobotUtils.isRobotAlreadyPlaced(toyRobot)) {
			ZoneToyRobotUtils.placeToyRobot(toyRobot, this.table.getTableTop(), this.placeX, this.placeY);
		}
		else {
			ZoneToyRobotUtils.removeRobotFromCurrentPlace(toyRobot, this.table.getTableTop());
			ZoneToyRobotUtils.placeToyRobot(toyRobot, this.table.getTableTop(), this.placeX, this.placeY);
		}
		ZoneToyRobotUtils.setToyRobotCurrentCoordinates(toyRobot, placeX, placeY, facing);
		robotCommandOutput.setSuccess(true);
		return robotCommandOutput;
	}

}
