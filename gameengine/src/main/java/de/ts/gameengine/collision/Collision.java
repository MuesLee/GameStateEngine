package de.ts.gameengine.collision;

import de.ts.gameengine.entities.StaticGameEntity;

public class Collision {
	
	private StaticGameEntity entityCollidedWith;
	private CollisionSide sideCollidedEntityHitsIn;
	
	
	
	public Collision(StaticGameEntity entityCollidedWith,
			CollisionSide sideCollidedEntityHitsIn) {
		super();
		this.entityCollidedWith = entityCollidedWith;
		this.sideCollidedEntityHitsIn = sideCollidedEntityHitsIn;
	}
	public CollisionSide getSideCollidedEntityHitsIn() {
		return sideCollidedEntityHitsIn;
	}
	public void setSideCollidedEntityHitsIn(CollisionSide sideCollidedEntityHitsIn) {
		this.sideCollidedEntityHitsIn = sideCollidedEntityHitsIn;
	}
	public StaticGameEntity getEntityCollidedWith() {
		return entityCollidedWith;
	}
	public void setEntityCollidedWith(StaticGameEntity entityCollidedWith) {
		this.entityCollidedWith = entityCollidedWith;
	}
	
}
