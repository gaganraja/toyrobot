# Toy Robot

Development approach
---------------------

A robot ZoneToyRobot takes command from ToyRobotRemoteControl and based on the type of command delegates command handling to specific command out of the following :

PlaceCommand : Places Robot at particular co-ordinates facing in a particular direction (N,S,E,W)
MoveCommand : Moves Robot a step forward in the direction that its facing
LeftCommand : Rotates Robot at 90 deg. in anticlockwise direction, for e.g. if Robot was facing NORTH it will face WEST after LeftCommand
RightCommand : Rotates Robot at 90 deg. in clockwise direction, for e.g. if Robot was facing NORTH it will face EAST after RightCommand
ReportCommand : Outputs Robot's current location and facing.

If not done in following sequence, validations will prompt user to do it correctly :

1)Provide input to create a table top e.g. 5,5 or 4,3, or 6,7 etc. When a table top is created, robot is created and activated too.
2)Place Robot on some coordinate. Validation make sure that coordinates are valid and within the boundaries.
3)Move, Turn Left, Turn Right and generate Report of Robot's current position.

Validation stops user to move robot forward/backward, up/down if Robot is on the edge and will fall off the table if moved further.

To take a command, Robot expects command instruction and object (in our case table top) on which it has to move to be passed to it wrapped inside the command.

 ToyRobotRemoteControl takes input from command line and move Robot on the table top.


This project has been created as a maven project, after downloading this project, at the root run following command to build the project : 

mvn package

This should run all the unit tests and build the project and package it in zone-toy-robot-0.0.1-SNAPSHOT.jar

Note : Not sure why but locally build was failing sometimes with error : "Source option 5 is no longer supported. Use 6 or later". But once i refreshed project in my
IDE and ran it again it ran successfully. I'm assuming it is some issue in my local dev environment and should work fine in other environments because for me
also it worked once i refreshed the project.

java version : 11

Once the jar has been created, run following command : 

java -jar {/pathtojar}/zone-toy-robot-0.0.1-SNAPSHOT.jar

This will get com.zonedigital.robot.main.ToyRobotRemoteControl ready to take inputs

Sample input (Considering table top as a 5,5 grid) User can create grid of any size, negative numbers not allowed
------------------------------------------------------------------------------------------------------------------------

User will see following message as the first thing : 

Following are the valid inputs : 
0 : Create TableTop 
  1 : Place Command 
 2 : Move Command 
 3 : Left Command 
 4 : Right Command 
 5 : Report Command
 
Click Enter and input 1
User will get message that Table/Robot does not exist
Click Enter and input 0
User will be asked to enter value for grid. Here don't click enter just input 5,5
User will get success message.
Now click enter and either input 1 to Place Robot Or 2,3,4 or 5 (to see the error message that none of these commands will work as Robot has not been placed yet)
If input was 1, without clicking enter, input lets say 0,0,NORTH
Now click enter, it should show success message
Click enter again and input 5 to see current position of Robot
Click enter again and input 2 to move robot, click enter again and input 2 and then click enter and input 5, it should show 0,2, NORTH
Once Robot has reached 0,4,NORTH, input 2 will show error because next movement will make robot fall off.
User can then click on 4 to rotate Robot to EAST and make it move until it reaches last coordinate, after that user will start getting error message that robot cannot move further.
User can then again change Robot's direction and repeat various actions as above.

To Exit, without clicking enter, please input "E".
----------------------------------------------------

Note : Before inputting any command/create related number i.e. 0,1,2,3,4 or 5 please first click enter. However before inputting table top coordinates or place coordinates don't click enter (Click enter after inputting coordinates for the next action).
 