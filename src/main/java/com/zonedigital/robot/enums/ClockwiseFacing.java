package com.zonedigital.robot.enums;

public enum ClockwiseFacing {
	
	NORTH("EAST"), 
	EAST("SOUTH"), 
	SOUTH("WEST"),
	WEST("NORTH");
	  
    private String clockwiseFacing; 
  
    public String getClockwiseFacing() 
    { 
        return this.clockwiseFacing; 
    } 
  
    private ClockwiseFacing(String clockwiseFacing) 
    { 
        this.clockwiseFacing = clockwiseFacing; 
    } 

}
