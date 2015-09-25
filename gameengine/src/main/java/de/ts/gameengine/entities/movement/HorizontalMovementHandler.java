package de.ts.gameengine.entities.movement;

import de.ts.gameengine.controls.AnalogControlAction;
import de.ts.gameengine.controls.AnalogDirection;

public class HorizontalMovementHandler  implements AnalogMoveActionHandler {

	@Override
	public AnalogDirection convertMovementOfControlAction(AnalogControlAction controlAction) {
		
			
			AnalogDirection direction = computeAnalogDirection(controlAction);
			
			return direction;
	}

	private AnalogDirection computeAnalogDirection(AnalogControlAction controlAction) {
		AnalogDirection direction = null;
		
		if(controlAction.isLeft())
		{
			direction = AnalogDirection.LEFT;
		}
		else if(controlAction.isRight())
		{
			direction = AnalogDirection.RIGHT;
			
		}
		return direction;
	}
	
}
