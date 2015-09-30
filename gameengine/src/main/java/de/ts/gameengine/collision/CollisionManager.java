package de.ts.gameengine.collision;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import de.ts.gameengine.entities.DynamicGameEntity;
import de.ts.gameengine.entities.StaticGameEntity;
import de.ts.gameengine.entities.movement.Velocity;
import de.ts.gameengine.gamestates.AbstractGameLevel;
import de.ts.gameengine.view.Camera;

public class CollisionManager {

	public static int SEARCH_RANGE_X = 100;
	public static int SEARCH_RANGE_Y = 50;

	private Quadtree dynamicQuadtree;

	private Quadtree staticQuadtree;

	private AbstractGameLevel gameState;

	public CollisionManager(AbstractGameLevel gameState) {

		this.gameState = gameState;
	}

	public void initDynamicQuadtree() {
		Camera camera = gameState.getCamera();

		this.dynamicQuadtree = createNewQuadtree(camera.getViewRectangle());
	}

	public void initStaticQuadtree(Rectangle bounds, List<StaticGameEntity> entities) {
		this.staticQuadtree = createNewQuadtree(bounds);

		for (StaticGameEntity staticGameEntity : entities) {
			staticQuadtree.insert(staticGameEntity);
		}

	}

	private Quadtree createNewQuadtree(Rectangle bounds) {
		return new Quadtree(0, bounds, 5, 3);
	}

	/**
	 * Fügt eine Entity dem dynamischen Quadtree hinzu.
	 * 
	 * @param entity
	 */
	public void addDynamicEntity(DynamicGameEntity entity) {
		dynamicQuadtree.insert(entity);
	}

	/**
	 * Fügt eine statische, unbewegliche Entity dem statischen Quadtree hinzu.
	 * 
	 * @param entity
	 */
	public void addStaticEntity(StaticGameEntity entity) {
		staticQuadtree.insert(entity);
	}

	public void clearDynamicQuadtree() {
		this.dynamicQuadtree = createNewQuadtree(gameState.getCamera().getViewRectangle());
	}

	/**
	 * Gibt eine Liste mit Entities zurück, die mit der übergebenen Entity
	 * kollidieren. Zusätzlich wird für jede Kollision ein Attribut
	 * zurückgeliefert, welches angibt, an welcher Seite des übergebenen Objekts
	 * diese Kollision besteht.
	 * 
	 * Kollisionen werden überprüft für Entities im statischen und dynamischen
	 * Quadtree.
	 * 
	 * @param entity
	 * @return Zuordnung berührter Objekte und der Seite, mit der dieses Objekt
	 *         berührt wurde.
	 */
	public List<Collision> retrieveCollisions(DynamicGameEntity entity) {

		List<Collision> collidedEntities = new ArrayList<>();

		Rectangle entityMovementArea = getVisitedAreaForEntity(entity);

		Rectangle searchArea = getSearchRectangleForEntity(entity);
		
		List<StaticGameEntity> possibleCollisions = dynamicQuadtree.retrieve(null, searchArea);

		possibleCollisions = staticQuadtree.retrieve(possibleCollisions, entityMovementArea);

		Rectangle entityBotLine = entity.getBotLine();
		Rectangle entityTopLine = entity.getTopLine();
		Rectangle entityLeftLine = entity.getLeftLine();
		Rectangle entityRightLine = entity.getRightLine();

		for (StaticGameEntity other : possibleCollisions) {

			//
			if (other.intersects(entityTopLine)) {
				collidedEntities.add(new Collision(other, CollisionSide.TOP));
			} else if (other.intersects(entityBotLine)) {
				collidedEntities.add(new Collision(other, CollisionSide.BOT));

			} else if (other.intersects(entityRightLine)) {
				collidedEntities.add(new Collision(other, CollisionSide.RIGHT));

			} else if (other.intersects(entityLeftLine)) {
				collidedEntities.add(new Collision(other, CollisionSide.LEFT));
			}
		}

		return collidedEntities;

	}

	/**
	 * Creates and returns a new Rectangle which contains all points the given entity may touch on the upcoming move if unhindered
	 * 
	 * @param entity
	 * @return
	 */
	Rectangle getVisitedAreaForEntity(DynamicGameEntity entity) {
		Rectangle visitedArea = null;
		
		Velocity velocity = entity.getVelocity();
		Rectangle collisionBoxNow = entity.getCollisionBox();
		Rectangle collisionBoxLater = entity.getCollisionBox();
		collisionBoxLater.translate((int) velocity.getVectorX(), (int)velocity.getVectorY());
		
		visitedArea = collisionBoxNow.union(collisionBoxLater);
		
		return visitedArea;
	}

	/**
	 * Creates and returns a new rectangle which represents the collision box of the given entity extended by a constant amount in all directions.
	 * 
	 * @param entity
	 * @return
	 */
	Rectangle getSearchRectangleForEntity(DynamicGameEntity entity) {
		
		
		int x = entity.getX();
		int y = entity.getY();
		
		int width = entity.getWidth();
		int height = entity.getHeight();
		
		Rectangle searchArea = new Rectangle(x-SEARCH_RANGE_X, y-SEARCH_RANGE_Y, width+2*SEARCH_RANGE_X, height+2*SEARCH_RANGE_Y);
		
		return searchArea;
	}
}
