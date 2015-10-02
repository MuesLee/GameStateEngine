package de.ts.gameengine.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.ArrayBlockingQueue;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Polygon;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

import com.google.common.eventbus.Subscribe;

import de.ts.gameengine.controller.GameController;
import de.ts.gameengine.entities.events.EntityEvent;
import de.ts.gameengine.entities.events.LongLastingModifier;

public abstract class StaticGameEntity extends Body {

	private long objectId;

	private int healthPoints;
	private int maxHealthPoints;
	private int healthPointReg;

	protected String name;

	protected ArrayBlockingQueue<LongLastingModifier> activeEntityEvents;

	protected BufferedImage image;

	public StaticGameEntity() {
		super();
		this.activeEntityEvents = new ArrayBlockingQueue<>(2, true);
		setObjectId(GameController.getNextIDForEntity());

	}

	public void update() {

		for (LongLastingModifier event : activeEntityEvents) {
			event.decreaseDuration();
		}

		healthPoints = Math.min(healthPoints + healthPointReg, maxHealthPoints);
	}

	@Subscribe
	public void handleEntityEvents(EntityEvent event) {
		// TODO: EVENT HANDLING
		if (event instanceof LongLastingModifier) {
			activeEntityEvents.add((LongLastingModifier) event);
		}
	}

	public void draw(Graphics2D g2d) {

		int x = (int) transform.getTranslationX();
		int y = (int) transform.getTranslationY();
		
		g2d.drawImage(getdrawImage(), x, y, null);
		
	}
	
	protected double getX()
	{
		BodyFixture fixture = getFixture(0);
		Convex convex = fixture.getShape();
		if (convex instanceof Polygon) {
			if (convex instanceof Rectangle) {
				Rectangle r = (Rectangle) convex;
				Vector2 c = r.getCenter();
				return c.x;
			}
		}
		return -1;
	}
	
	protected double getY()
	{
		BodyFixture fixture = getFixture(0);
		Convex convex = fixture.getShape();
		if (convex instanceof Polygon) {
			if (convex instanceof Rectangle) {
				Rectangle r = (Rectangle) convex;
				Vector2 c = r.getCenter();
				return c.y;
			}
		}
		return -1;
	}
	
	protected BufferedImage getdrawImage()
	{
		return image;
	}

	protected void addEntityEventToQueue(LongLastingModifier event) {
		activeEntityEvents.add(event);
	}

	protected boolean removedEntityEventFromQueue(LongLastingModifier event) {
		return activeEntityEvents.remove(event);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (getObjectId() ^ (getObjectId() >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StaticGameEntity other = (StaticGameEntity) obj;
		if (getObjectId() != other.getObjectId())
			return false;
		return true;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public int getHealthPointReg() {
		return healthPointReg;
	}

	public void setHealthPointReg(int healthPointReg) {
		this.healthPointReg = healthPointReg;
	}

	public int getMaxHealthPoints() {
		return maxHealthPoints;
	}

	public void setMaxHealthPoints(int maxHealthPoints) {
		this.maxHealthPoints = maxHealthPoints;
	}

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}
}
