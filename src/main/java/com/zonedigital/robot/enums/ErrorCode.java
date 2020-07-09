package com.zonedigital.robot.enums;

public enum ErrorCode {
	
	
	NOT_PLACED("Toy Robot has not been placed"), 
	INVALID_X_Y("Invalid horizontal/vertical coordinate, Toy robot may fall off table"), 
	DEFAULT("No Error");
	  
    private String errorDescription; 
  
    public String getErrorDescription() 
    { 
        return this.errorDescription; 
    } 
  
    private ErrorCode(String errorDescription) 
    { 
        this.errorDescription = errorDescription; 
    } 
	
}
