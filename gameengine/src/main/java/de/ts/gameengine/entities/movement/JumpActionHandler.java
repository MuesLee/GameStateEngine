package de.ts.gameengine.entities.movement;

import de.ts.gameengine.controls.AnalogControlAction;

public class JumpActionHandler  implements SpecialMoveActionHandler {

	@Override
	public SpecialMoveAction convertSpecialActionOfControlAction(AnalogControlAction controlAction) {
		
		if(controlAction.isJump())
		{
			return SpecialMoveAction.JUMP;
		}
		
		return null;
	}

}
