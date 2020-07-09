package com.zonedigital.robot.enums;

public enum AntiClockwiseFacing {
	
	NORTH("WEST"), 
	WEST("SOUTH"), 
	SOUTH("EAST"),
	EAST("NORTH");
	  
    private String anticlockwiseFacing; 
  
    public String getAnticlockwiseFacing() 
    { 
        return this.anticlockwiseFacing; 
    } 
  
    private AntiClockwiseFacing(String anticlockwiseFacing) 
    { 
        this.anticlockwiseFacing = anticlockwiseFacing; 
    } 

}
