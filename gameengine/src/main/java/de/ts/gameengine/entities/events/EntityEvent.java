package de.ts.gameengine.entities.events;

import java.util.List;

import de.ts.gameengine.entities.StaticGameEntity;

public class EntityEvent {
	
	private StaticGameEntity source;
	
	private List<StaticGameEntity> target;

	public List<StaticGameEntity> getTarget() {
		return target;
	}

	public void setTarget(List<StaticGameEntity> target) {
		this.target = target;
	}

	public StaticGameEntity getSource() {
		return source;
	}

	public void setSource(StaticGameEntity source) {
		this.source = source;
	}

}
