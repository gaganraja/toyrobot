package com.zonedigital.robot;

import com.zonedigital.robot.command.RobotCommand;
import com.zonedigital.robot.command.output.RobotCommandOutput;
import com.zonedigital.robot.enums.Facing;

public class ZoneToyRobot {
	
	private int currentX = -1;
	
	private int currentY = -1;
	
	private Facing currentFacing;

	public RobotCommandOutput takeCommand(RobotCommand robotCommand) {
		return robotCommand.executeCommand(this);
	}
	
	public int getCurrentX() {
		return currentX;
	}

	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}

	public int getCurrentY() {
		return currentY;
	}

	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}
	
	public Facing getCurrentFacing() {
		return currentFacing;
	}

	public void setCurrentFacing(Facing currentFacing) {
		this.currentFacing = currentFacing;
	}


}
