package de.ts.gameengine.entities.events;

public interface LongLastingModifier {

	public int getDuration();
	public int getMaxDuration();
	public void decreaseDuration();
	
}
