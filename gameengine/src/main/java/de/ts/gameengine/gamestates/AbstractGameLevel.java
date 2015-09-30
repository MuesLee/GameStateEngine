package de.ts.gameengine.gamestates;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import de.ts.gameengine.collision.Collision;
import de.ts.gameengine.collision.CollisionManager;
import de.ts.gameengine.entities.DynamicGameEntity;
import de.ts.gameengine.entities.Player;
import de.ts.gameengine.view.Camera;
import de.ts.gameengine.view.TileMap;

public class AbstractGameLevel extends AbstractGameState {

	protected TileMap tileMap;
	protected CollisionManager collisionManager;

	protected Camera camera;

	private ArrayList<DynamicGameEntity> otherDynamicEntities;

	public AbstractGameLevel() {
		super();
		this.collisionManager = new CollisionManager(this);
		this.setOtherDynamicEntities(new ArrayList<DynamicGameEntity>());
	}

	@Override
	public void draw(Graphics2D g2d) {

		drawBackground(g2d);

		g2d.translate(-camera.getX(), -camera.getY());

		drawTileMap(g2d);
		drawPlayer(g2d);
		drawOtherEntities(g2d);
	}

	private void drawOtherEntities(Graphics2D g2d) {
		for (DynamicGameEntity entity : getOtherDynamicEntities()) {
			entity.draw(g2d);
		}
	}

	protected void drawTileMap(Graphics2D g2d) {
		tileMap.draw(g2d);
	}

	protected void drawPlayer(Graphics2D g2d) {
		for (Player player : getPlayers()) {
			player.draw(g2d);
		}
	}

	@Override
	public void update() {
		
		prepareEntitiesForUpdate();
		
		computeCollisions();
		
		background.update();
		camera.update();
		
		updateEntities();
	}

	private void updateEntities() {
		for (Player player : getPlayers()) {
			player.update();
		}
		for (DynamicGameEntity entity : getOtherDynamicEntities()) {
			entity.update();
		}
	}

	private void prepareEntitiesForUpdate() {
		for (Player player : getPlayers()) {
			player.prepareUpdate();
		}
		for (DynamicGameEntity entity : getOtherDynamicEntities()) {
			entity.prepareUpdate();
		}
	}

	private void computeCollisions() {
		for (Player player : players) {
			List<Collision> collisions = collisionManager.retrieveCollisions(player);
		
			for (Collision collision : collisions) {
				
				player.handleCollision(collision);
			}
		}
	}

	@Override
	public void init() {
		
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public ArrayList<DynamicGameEntity> getOtherDynamicEntities() {
		return otherDynamicEntities;
	}

	public void setOtherDynamicEntities(ArrayList<DynamicGameEntity> otherDynamicEntities) {
		this.otherDynamicEntities = otherDynamicEntities;
	}
}
