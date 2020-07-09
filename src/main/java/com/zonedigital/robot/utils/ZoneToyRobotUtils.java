package com.zonedigital.robot.utils;

import java.util.List;

import com.zonedigital.robot.Coordinates;
import com.zonedigital.robot.ZoneToyRobot;
import com.zonedigital.robot.enums.Facing;

public class ZoneToyRobotUtils {
	
	
	public static Coordinates getNextCoordinatesToMove(Facing currentFacing, int currentX, int currentY) {
		Coordinates nextCoordinates = new Coordinates();
		if(Facing.NORTH == currentFacing)
		{
			nextCoordinates.setxCoordinate(currentX);
			nextCoordinates.setyCoordinate(currentY + 1);
		}
		else if(Facing.SOUTH == currentFacing)
		{
			nextCoordinates.setxCoordinate(currentX);
			nextCoordinates.setyCoordinate(currentY - 1);
		}
		else if(Facing.EAST == currentFacing)
		{
			nextCoordinates.setxCoordinate(currentX + 1);
			nextCoordinates.setyCoordinate(currentY);
		}
		else if(Facing.WEST == currentFacing)
		{
			nextCoordinates.setxCoordinate(currentX - 1);
			nextCoordinates.setyCoordinate(currentY);
		}
		return nextCoordinates;
	}
	
	public static void removeRobotFromCurrentPlace(ZoneToyRobot toyRobot, List<List<Object>> tableTop) {
		List<Object> objectList = tableTop.get(toyRobot.getCurrentX());
		objectList.set(toyRobot.getCurrentY(), null);
	}

	public static void placeToyRobot(ZoneToyRobot toyRobot, List<List<Object>> tableTop, int placeX, int placeY) {
		List<Object> objectList = tableTop.get(placeX);
		objectList.set(placeY, toyRobot);
	}
	
	public static boolean isRobotAlreadyPlaced(ZoneToyRobot toyRobot) {
		return toyRobot.getCurrentX() != -1 && toyRobot.getCurrentY() != -1;
	}
	
	public static void setToyRobotCurrentCoordinates(ZoneToyRobot toyRobot, int currentX, int currentY, Facing facing) {
		toyRobot.setCurrentFacing(facing);
		toyRobot.setCurrentX(currentX);
		toyRobot.setCurrentY(currentY);
	}

}
