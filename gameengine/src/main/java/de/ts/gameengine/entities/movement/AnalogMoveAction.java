package de.ts.gameengine.entities.movement;

import de.ts.gameengine.controls.AnalogDirection;

public class AnalogMoveAction {

	private Velocity diff;
	
	private AnalogDirection direction;

	public AnalogMoveAction(Velocity diff, AnalogDirection direction) {
		super();
		this.diff = diff;
		this.direction = direction;
	}
	
	public AnalogMoveAction() {
	}

	public Velocity getDiff() {
		return diff;
	}

	public void setDiff(Velocity diff) {
		this.diff = diff;
	}

	public AnalogDirection getDirection() {
		return direction;
	}

	public void setDirection(AnalogDirection direction) {
		this.direction = direction;
	}
	
	
	
}
