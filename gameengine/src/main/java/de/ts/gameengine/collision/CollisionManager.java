package de.ts.gameengine.collision;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import de.ts.gameengine.entities.DynamicGameEntity;
import de.ts.gameengine.entities.StaticGameEntity;
import de.ts.gameengine.gamestates.AbstractGameLevel;
import de.ts.gameengine.view.Camera;

public class CollisionManager {

	private Quadtree dynamicQuadtree;

	private Quadtree staticQuadtree;
	
	private AbstractGameLevel gameState;

	public CollisionManager(AbstractGameLevel gameState) {

		this.gameState = gameState;
	}
	
	public void initDynamicQuadtree()
	{
		Camera camera = gameState.getCamera();
		
		this.dynamicQuadtree = createNewQuadtree(camera.getViewRectangle());
	}
	
	
	public void initStaticQuadtree(Rectangle bounds, List<StaticGameEntity> entities)
	{
		this.staticQuadtree = createNewQuadtree(bounds);
		
		for (StaticGameEntity staticGameEntity : entities) {
			staticQuadtree.insert(staticGameEntity);
		}
		
	}
	

	private Quadtree createNewQuadtree(Rectangle bounds) {
		return new Quadtree(0, bounds,5,3);
	}

	/**
	 * F�gt eine Entity dem dynamischen Quadtree hinzu. 
	 * 
	 * @param entity
	 */
	public void addDynamicEntity(DynamicGameEntity entity) {
		dynamicQuadtree.insert(entity);
	}
	
	/**
	 * F�gt eine statische, unbewegliche Entity dem statischen Quadtree hinzu.
	 * 
	 * @param entity
	 */
	public void addStaticEntity(StaticGameEntity entity)
	{
		staticQuadtree.insert(entity);
	}

	public void clearDynamicQuadtree() {
		this.dynamicQuadtree = createNewQuadtree(gameState.getCamera().getViewRectangle());
	}

	/**
	 * Gibt eine Liste mit Entities zur�ck, die mit der �bergebenen Entity
	 * kollidieren. Zus�tzlich wird f�r jede Kollision ein Attribut
	 * zur�ckgeliefert, welches angibt, an welcher Seite des �bergebenen Objekts
	 * diese Kollision besteht.
	 * 
	 * Kollisionen werden �berpr�ft f�r Entities im statischen und dynamischen Quadtree.
	 * 
	 * @param entity
	 * @return Zuordnung ber�hrter Objekte und der Seite, mit der dieses Objekt ber�hrt wurde.
	 */
	public List<Collision> retrieveCollisions(
			StaticGameEntity entity) {

		List<Collision> collidedEntities = new ArrayList<>();

		List<StaticGameEntity> possibleCollisions = dynamicQuadtree.retrieve(null,
				entity.getCollisionBox());
		
		possibleCollisions = staticQuadtree.retrieve(possibleCollisions, entity.getCollisionBox());

		Rectangle entityBotLine = entity.getBotLine();
		Rectangle entityTopLine = entity.getTopLine();
		Rectangle entityLeftLine = entity.getLeftLine();
		Rectangle entityRightLine = entity.getRightLine();

		for (StaticGameEntity other : possibleCollisions) {

	
			//
			if (other.intersects(entityTopLine)) {
				collidedEntities.add (new Collision(other, CollisionSide.TOP));
			} else if (other.intersects(entityBotLine)) {
				collidedEntities.add (new Collision(other, CollisionSide.BOT));
				
			} else if (other.intersects(entityRightLine)) {
				collidedEntities.add (new Collision(other, CollisionSide.RIGHT));
				
			} else if (other.intersects(entityLeftLine)) {
				collidedEntities.add (new Collision(other, CollisionSide.LEFT));
			}
		}

		return collidedEntities;

	}

}
