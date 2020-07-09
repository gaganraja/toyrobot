package com.zonedigital.robot.main;

import java.util.Scanner;

import com.zonedigital.robot.ZoneToyRobot;
import com.zonedigital.robot.command.impl.LeftCommand;
import com.zonedigital.robot.command.impl.MoveCommand;
import com.zonedigital.robot.command.impl.PlaceCommand;
import com.zonedigital.robot.command.impl.ReportCommand;
import com.zonedigital.robot.command.impl.RightCommand;
import com.zonedigital.robot.command.output.RobotCommandOutput;
import com.zonedigital.robot.enums.Facing;
import com.zonedigital.robot.movement.Table;

public class ToyRobotRemoteControl {
	
	
	public static void main(String[] args) {
		
		System.out.println("Following are the valid inputs : ");
		System.out.println("0 : Create TableTop \n  1 : Place Command \n 2 : Move Command \n 3 : Left Command \n 4 : Right Command \n 5 : Report Command" );
		Table table = null;
		ZoneToyRobot zoneToyRobot = null;
		Scanner myObj = new Scanner(System.in);
		while(!"E".equals(myObj.nextLine())) {
			
			int input = -1;
			try {
				input = Integer.parseInt(myObj.nextLine());  // Read user input
			} catch (NumberFormatException ex) {
				System.out.println("Invalid Input, please enter numbers only");
				continue;
			}
			
			switch(input) {
				
				case 0 : 
					if (table != null) {
						System.out.println("Table Top has been already created for this session, please try some other input");
						break;
					}
					System.out.println("Enter comma-separated table top grid size e.g. 5,5 should be numbers only");
					String tableTopGridCoordinates = myObj.nextLine();
					if(validOccurancesOfComma(tableTopGridCoordinates, 1)) {
						String[] splittedGridCoordinates = tableTopGridCoordinates.split(",");
						int horizontalCoordinate = -1;
						try {
							horizontalCoordinate = Integer.parseInt(splittedGridCoordinates[0]);
						} catch (NumberFormatException ex) {
							System.out.println("Invalid Input : "  + splittedGridCoordinates[0] + " is not a number");
							break;
						}
						int verticalCoordinate = -1;
						try {
							verticalCoordinate = Integer.parseInt(splittedGridCoordinates[1]);
						} catch (NumberFormatException ex) {
							System.out.println("Invalid Input : "  + splittedGridCoordinates[1] + " is not a number");
							break;
						}
						try {
							table = new Table(horizontalCoordinate, verticalCoordinate);
							zoneToyRobot = new ZoneToyRobot();
							System.out.println("Table Top created successfully and robot is ready to take the command, please give command to Robot");
							break;
						}
						catch (IllegalArgumentException ex) {
							System.out.println("Negative coordinates not allowed");
						}
					}
					else {
						System.out.println("Invalid Input, please try again");
					}
					break;
				
				case 1 : 
					if(zoneToyRobot == null) {
						System.out.println("Table Top and Robot are not ready for this session, please press 0 to create them");
						break;
					}
					System.out.println("Robot is ready to take Place command, please enter comma-separated coordinates and facing e.g. 0,1,NORTH, only valid values"
							+ " for facing are NORTH, SOUTH, EAST, WEST");
					String placeCommandData = myObj.nextLine();
					if(validOccurancesOfComma(placeCommandData, 2)) {
						String[] splittedPlaceCommandData = placeCommandData.split(",");
						int xCoordinate = -1;
						try {
							xCoordinate = Integer.parseInt(splittedPlaceCommandData[0]);
						} catch (NumberFormatException ex) {
							System.out.println("Invalid Input : "  + splittedPlaceCommandData[0] + " is not a number");
							break;
						}
						int yCoordinate = -1;
						try {
							yCoordinate = Integer.parseInt(splittedPlaceCommandData[1]);
						} catch (NumberFormatException ex) {
							System.out.println("Invalid Input : "  + splittedPlaceCommandData[1] + " is not a number");
							break;
						}
						String facing = splittedPlaceCommandData[2];
						if(!"NORTH".equals(facing) && !"SOUTH".equals(facing) && !"EAST".equals(facing) && !"WEST".equals(facing)) {
							System.out.println("Only allowed facing are NORTH, SOUTH, EAST, WEST");
							break;
						}
						PlaceCommand placeCommand = new PlaceCommand(xCoordinate, yCoordinate, getFacingObj(facing), table);
						RobotCommandOutput placeCommandOutput = zoneToyRobot.takeCommand(placeCommand);
						if(!placeCommandOutput.isSuccess()) {
							System.out.println("Error while placing Robot, Details : " + placeCommandOutput.getErrorCode() 
								+ " " + placeCommandOutput.getErrorCode().getErrorDescription());
							break;
						}
						System.out.println("Robot placed at desired coordinates, please press 5 to see Robot's current position");
						break;
					}
					else {
						System.out.println("Invalid Input, please try again");
					}
				break;
				
				case 2 : 
					if(zoneToyRobot == null) {
						System.out.println("Table Top and Robot are not ready for this session, please press 0 to create them");
						break;
					}
					MoveCommand moveCommand = new MoveCommand(table);
					RobotCommandOutput moveCommandOutput = zoneToyRobot.takeCommand(moveCommand);
					if(!moveCommandOutput.isSuccess()) {
						System.out.println("Error while moving Robot, Error Code : " + moveCommandOutput.getErrorCode() 
						+ " Error Description : " + moveCommandOutput.getErrorCode().getErrorDescription());
						break;
					} else {
						System.out.println("Robot placed at desired coordinates, please press 5 to see Robot's current position");
						break;
					}
					
				case 3 : 
					if(zoneToyRobot == null) {
						System.out.println("Table Top and Robot are not ready for this session, please press 0 to create them");
						break;
					}
					LeftCommand leftCommand = new LeftCommand();
					RobotCommandOutput leftCommandOutput = zoneToyRobot.takeCommand(leftCommand);
					if(!leftCommandOutput.isSuccess()) {
						System.out.println("Error while rotating Robot to left, Error Code : " + leftCommandOutput.getErrorCode() 
						+ " Error Description : " + leftCommandOutput.getErrorCode().getErrorDescription());
						break;
					} else {
						System.out.println("Robot placed at desired coordinates, please press 5 to see Robot's current position");
						break;
					}	
					
				case 4 : 
					if(zoneToyRobot == null) {
						System.out.println("Table Top and Robot are not ready for this session, please press 0 to create them");
						break;
					}
					RightCommand rightCommand = new RightCommand();
					RobotCommandOutput rightCommandOutput = zoneToyRobot.takeCommand(rightCommand);
					if(!rightCommandOutput.isSuccess()) {
						System.out.println("Error while rotating Robot to right, Error Code : " + rightCommandOutput.getErrorCode() 
						+ " Error Description : " + rightCommandOutput.getErrorCode().getErrorDescription());
						break;
					} else {
						System.out.println("Robot placed at desired coordinates, please press 5 to see Robot's current position");
						break;
					}	
				
				case 5 : 
					if(zoneToyRobot == null) {
						System.out.println("Table Top and Robot are not ready for this session, please press 0 to create them");
						break;
					}
					ReportCommand reportCommand = new ReportCommand();
					RobotCommandOutput reportCommandOutput = zoneToyRobot.takeCommand(reportCommand);
					if(!reportCommandOutput.isSuccess()) {
						System.out.println("Error while reporting Robot coordinates, Error Code : " + reportCommandOutput.getErrorCode() 
						+ " Error Description : " + reportCommandOutput.getErrorCode().getErrorDescription());
						break;
					} else {
						System.out.println("Report => Output : " + reportCommandOutput.getCurrentX() + ", " + 
								reportCommandOutput.getCurrentY() + ", " + reportCommandOutput.getCurrentFacing().name());
						break;
					}
				
				default :
					System.out.println("Please enter a valid input");
					break;
			}
		}
		myObj.close();
	}

	private static Facing getFacingObj(String facing) {
		Facing[] values = Facing.values();
		for (Facing facingObj : values) {
			if(facingObj.name().equals(facing)) {
				return facingObj;
			}
		}
		return null;
	}

	private static boolean validOccurancesOfComma(String tableTopGridCoordinates, int noOfOccurancesAllowed) {
		if(tableTopGridCoordinates.startsWith(",") || tableTopGridCoordinates.endsWith(",")) {
			return false;
		}
		char[] charArray = tableTopGridCoordinates.toCharArray();
		int occurance = 0;
		for (char c : charArray) {
			if (c == ',') {
				++occurance;
			}
		}
		if(occurance != noOfOccurancesAllowed) {
			return false;
		}
		return true;
	}

}
