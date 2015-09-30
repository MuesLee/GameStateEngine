package de.ts.gameengine.entities.movement;

import de.ts.gameengine.controls.AnalogControlAction;

public interface SpecialMoveActionHandler {

	SpecialMoveAction convertSpecialActionOfControlAction(AnalogControlAction controlAction);

}
