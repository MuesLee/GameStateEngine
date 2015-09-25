package de.ts.gameengine.entities.movement;

import de.ts.gameengine.controls.AnalogControlAction;
import de.ts.gameengine.controls.AnalogDirection;

public class EightWayMovementHandler implements AnalogMoveActionHandler  {

	
	@Override
	public AnalogDirection convertMovementOfControlAction(AnalogControlAction controlAction) {
		
			
			AnalogDirection direction = computeAnalogDirection(controlAction);
			
			return direction;
	}

	private AnalogDirection computeAnalogDirection(AnalogControlAction controlAction) {
		AnalogDirection direction = null;
		
		if(controlAction.isDown())
		{
			if(controlAction.isLeft())
			{
			direction = AnalogDirection.DOWN_LEFT;	
			}
			else if(controlAction.isRight())
			{
				direction = AnalogDirection.DOWN_RIGHT;	
			}
			else {
				direction = AnalogDirection.DOWN;
			}
		}
		else if(controlAction.isUp())
		{
			if(controlAction.isLeft())
			{
				direction = AnalogDirection.UP_LEFT;
			}
			else if(controlAction.isRight())
			{
				direction = AnalogDirection.UP_RIGHT;
				
			}
			else {
				direction = AnalogDirection.UP;
			}
		}
		else if(controlAction.isLeft())
		{
			direction = AnalogDirection.LEFT;
		}
		else if(controlAction.isRight()){
			direction = AnalogDirection.RIGHT;
		}
		return direction;
	}
}
