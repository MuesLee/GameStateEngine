package de.ts.gameengine.controls;

import de.ts.gameengine.entities.movement.Velocity;

public enum AnalogDirection {
	UP(0, -1), UP_RIGHT(0.5, -0.5), RIGHT(1, 0), DOWN_RIGHT(0.5, 0.5), DOWN(0, 1), DOWN_LEFT(-0.5, 0.5), LEFT(-1,
			0), UP_LEFT(-0.5, -0.5);

	private double diffY;
	private double diffX;

	private AnalogDirection(double diffX, double diffY) {
		this.diffY = diffY;
		this.diffX = diffX;
	}

	public Velocity getDiff() {
		Velocity diff = new Velocity(this.diffX, this.diffY);
		return diff;
	}

}
