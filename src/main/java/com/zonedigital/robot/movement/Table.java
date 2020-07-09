package com.zonedigital.robot.movement;

import java.util.ArrayList;
import java.util.List;

public class Table {
	
	private int maxHorizontalMovement;
	
	private int maxVerticalMovement;
	
	private List<List<Object>> tableTop;
	
	public Table(int maxHorizontalMovement, int maxVerticalMovement) {
		
		if(maxHorizontalMovement <= 0 || maxVerticalMovement <=0) {
			throw new IllegalArgumentException("Illegal Arguments, Table creation failed");
		}
		
		this.maxHorizontalMovement = maxHorizontalMovement;
		this.maxVerticalMovement = maxVerticalMovement;
		tableTop = new ArrayList<List<Object>>();
		for (int i = 0 ; i <= maxHorizontalMovement - 1; i++) {
			List<Object> objectList = new ArrayList<Object>();
			for (int j = 0 ; j <= maxVerticalMovement - 1; j++) {
				objectList.add(null);
			}
			tableTop.add(i, objectList);
		}
	}
	
	public boolean isHorizantalCoordinateValid(int xCoordinate) {
		
		if(xCoordinate < 0) {
			return false;
		}
		
		if(xCoordinate >= this.maxHorizontalMovement) {
			return false;
		}
		
		return true;
		
	}
	
	public boolean isVerticalCoordinateValid(int yCoordinate) {
		
		if(yCoordinate < 0) {
			return false;
		}
		
		if(yCoordinate >= this.maxVerticalMovement) {
			return false;
		}
		
		return true;
		
	}
	
	public List<List<Object>> getTableTop() {
		return tableTop;
	}

}
