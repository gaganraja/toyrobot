package com.zonedigital.robot.command.output;

import com.zonedigital.robot.enums.ErrorCode;
import com.zonedigital.robot.enums.Facing;

public class RobotCommandOutput {
	
	private boolean success;
	
	private int currentX;
	
	private int currentY;
	
	private Facing currentFacing;
	
	private ErrorCode errorCode = ErrorCode.DEFAULT; 

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
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
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}


}
