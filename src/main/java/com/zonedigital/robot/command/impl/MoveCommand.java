package com.zonedigital.robot.command.impl;

import com.zonedigital.robot.Coordinates;
import com.zonedigital.robot.ZoneToyRobot;
import com.zonedigital.robot.command.RobotCommand;
import com.zonedigital.robot.command.output.RobotCommandOutput;
import com.zonedigital.robot.enums.ErrorCode;
import com.zonedigital.robot.enums.Facing;
import com.zonedigital.robot.movement.Table;
import com.zonedigital.robot.utils.ZoneToyRobotUtils;

public class MoveCommand implements RobotCommand {
	
	private Table table;
	
	public MoveCommand(Table table) {
		this.table = table;
	}

	public RobotCommandOutput executeCommand(ZoneToyRobot toyRobot) {
		RobotCommandOutput robotCommandOutput = new RobotCommandOutput();
		if(!ZoneToyRobotUtils.isRobotAlreadyPlaced(toyRobot)) {
			robotCommandOutput.setSuccess(false);
			robotCommandOutput.setErrorCode(ErrorCode.NOT_PLACED);
			return robotCommandOutput;
		}
		Facing currentFacing = toyRobot.getCurrentFacing();
		int currentX = toyRobot.getCurrentX();
		int currentY = toyRobot.getCurrentY();
		Coordinates nextCoordinatesToMove = ZoneToyRobotUtils.getNextCoordinatesToMove(currentFacing, currentX, currentY);
		int nextXCoordinate = nextCoordinatesToMove.getxCoordinate();
		int nextYCoordinate = nextCoordinatesToMove.getyCoordinate();
		if(!table.isHorizantalCoordinateValid(nextXCoordinate) || (!table.isVerticalCoordinateValid(nextYCoordinate))) 
		{
			robotCommandOutput.setSuccess(false);
			robotCommandOutput.setErrorCode(ErrorCode.INVALID_X_Y);
			return robotCommandOutput;
		}
		else
		{
			ZoneToyRobotUtils.removeRobotFromCurrentPlace(toyRobot, this.table.getTableTop());
			ZoneToyRobotUtils.placeToyRobot(toyRobot, this.table.getTableTop(), nextXCoordinate, nextYCoordinate);
			ZoneToyRobotUtils.setToyRobotCurrentCoordinates(toyRobot, nextXCoordinate, nextYCoordinate, toyRobot.getCurrentFacing());
		}
		robotCommandOutput.setSuccess(true);
		return robotCommandOutput;
	}

}
