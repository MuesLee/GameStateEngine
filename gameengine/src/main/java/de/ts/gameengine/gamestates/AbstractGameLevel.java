package de.ts.gameengine.gamestates;

import java.awt.Graphics2D;
import java.util.ArrayList;

import org.dyn4j.dynamics.Step;
import org.dyn4j.dynamics.StepListener;
import org.dyn4j.dynamics.World;

import de.ts.gameengine.entities.DynamicGameEntity;
import de.ts.gameengine.entities.Player;
import de.ts.gameengine.view.Camera;
import de.ts.gameengine.view.TileMap;

public class AbstractGameLevel extends AbstractGameState implements StepListener {

	private int gravity = 5;
	protected TileMap tileMap;
	protected World world;

	protected Camera camera;

	private ArrayList<DynamicGameEntity> otherDynamicEntities;

	public AbstractGameLevel() {
		super();
		this.setOtherDynamicEntities(new ArrayList<DynamicGameEntity>());
		this.setWorld(new World());
		world.setGravity(World.EARTH_GRAVITY);
		world.addListener(this);
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
	public void update(double deltaU) {
		
		prepareEntitiesForUpdate();
		world.setUpdateRequired(true);
		world.update(deltaU);
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

	public int getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	@Override
	public void begin(Step step, World world) {
		System.out.println("UPDATE BEGUN");
		
	}

	@Override
	public void updatePerformed(Step step, World world) {
		System.out.println("UPDATE PERFORMED");
		
	}

	@Override
	public void postSolve(Step step, World world) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end(Step step, World world) {
		// TODO Auto-generated method stub
		
	}
}
