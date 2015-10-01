package de.ts.gameengine.collision;

import de.ts.gameengine.entities.StaticGameEntity;

public class Collision implements Comparable<Collision> {

	private StaticGameEntity entityCollidedWith;
	private CollisionSide sideCollidedEntityHitsIn;
	private double distance;

	public Collision(StaticGameEntity entityCollidedWith, CollisionSide sideCollidedEntityHitsIn) {
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

	@Override
	public int compareTo(Collision o) {

		if (this.distance < o.distance)
			return -1;
		else if (this.distance == o.distance)
			return 0;
		else
			return 1;

	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
