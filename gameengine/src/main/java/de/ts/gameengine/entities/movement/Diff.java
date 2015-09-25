package de.ts.gameengine.entities.movement;

public class Diff {

	private double vectorX;
	private double vectorY;
	
	public Diff() {
	}
	
	public Diff(double vectorX, double vectorY) {
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
	
	
	
}
