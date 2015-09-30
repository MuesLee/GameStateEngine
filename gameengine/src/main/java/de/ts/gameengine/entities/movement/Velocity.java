package de.ts.gameengine.entities.movement;

public class Velocity {

	private double vectorX;
	private double vectorY;
	
	public Velocity() {
	}
	
	public Velocity(double vectorX, double vectorY) {
		super();
		this.setVectorX(vectorX);
		this.setVectorY(vectorY);
	}
	public double getVectorY() {
		return vectorY;
	}
	public void setVectorY(double vectorY) {
		this.vectorY = vectorY;
	}
	public double getVectorX() {
		return vectorX;
	}
	public void setVectorX(double vectorX) {
		this.vectorX = vectorX;
	}
	
	/**
	 * Adds the given values to the velocity vector
	 * 
	 * @param diffX
	 * @param diffY
	 */
	public void update(double diffX, double diffY)
	{
		setVectorX(getVectorX() + diffX);
		setVectorY(getVectorY() + diffY);
	}
	
	
	
}
