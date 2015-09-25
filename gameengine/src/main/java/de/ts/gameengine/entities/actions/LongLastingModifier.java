package de.ts.gameengine.entities.actions;

public interface LongLastingModifier {

	public int getDuration();
	public int getMaxDuration();
	public void decreaseDuration();
	
}
