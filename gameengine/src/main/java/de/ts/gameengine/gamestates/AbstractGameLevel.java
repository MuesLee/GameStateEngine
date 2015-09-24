package de.ts.gameengine.gamestates;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import de.ts.gameengine.collision.Collision;
import de.ts.gameengine.collision.CollisionManager;
import de.ts.gameengine.entities.Enemy;
import de.ts.gameengine.entities.Player;
import de.ts.gameengine.view.Camera;
import de.ts.gameengine.view.TileMap;

public class AbstractGameLevel extends AbstractGameState {

	protected TileMap tileMap;
	protected CollisionManager collisionManager;

	protected Camera camera;

	protected ArrayList<Enemy> enemies;

	public AbstractGameLevel() {
		super();
		this.collisionManager = new CollisionManager(this);
		this.enemies = new ArrayList<Enemy>();
	}

	@Override
	public void draw(Graphics2D g2d) {

		drawBackground(g2d);

		g2d.translate(-camera.getX(), -camera.getY());

		drawTileMap(g2d);
		drawPlayer(g2d);
		drawEnemies(g2d);
	}

	private void drawEnemies(Graphics2D g2d) {
		for (Enemy enemy : enemies) {
			enemy.draw(g2d);
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
		background.update();
		camera.update();
		for (Player player : getPlayers()) {
			player.update();
		}
		for (Enemy enemy : enemies) {
			enemy.update();
		}
		computeCollisions();
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

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}
}
