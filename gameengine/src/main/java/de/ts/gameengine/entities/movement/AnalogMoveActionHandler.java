package de.ts.gameengine.entities.movement;

import de.ts.gameengine.controls.AnalogControlAction;
import de.ts.gameengine.controls.AnalogDirection;

public interface AnalogMoveActionHandler {

		public AnalogDirection convertMovementOfControlAction(AnalogControlAction controlAction);
	
}
