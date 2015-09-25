package de.ts.gameengine.entities.movement;

import de.ts.gameengine.controls.AnalogDirection;

public class AnalogMoveAction {

	private Diff diff;
	
	private AnalogDirection direction;

	public AnalogMoveAction(Diff diff, AnalogDirection direction) {
		super();
		this.diff = diff;
		this.direction = direction;
	}
	
	public AnalogMoveAction() {
	}

	public Diff getDiff() {
		return diff;
	}

	public void setDiff(Diff diff) {
		this.diff = diff;
	}

	public AnalogDirection getDirection() {
		return direction;
	}

	public void setDirection(AnalogDirection direction) {
		this.direction = direction;
	}
	
	
	
}
