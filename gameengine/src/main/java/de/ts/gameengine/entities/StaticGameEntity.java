package de.ts.gameengine.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.concurrent.ArrayBlockingQueue;

import org.dyn4j.dynamics.Body;

import com.google.common.eventbus.Subscribe;

import de.ts.gameengine.controller.GameController;
import de.ts.gameengine.entities.events.EntityEvent;
import de.ts.gameengine.entities.events.LongLastingModifier;


public abstract class StaticGameEntity extends Body {

	private long objectId;

	private int healthPoints;
	private int maxHealthPoints;
	private int healthPointReg;
	
	protected int x;
	protected int y;

	protected int width;
	protected int height;

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

		healthPoints = Math.min(healthPoints+healthPointReg, maxHealthPoints);
	}
	
	@Subscribe
	public void handleEntityEvents(EntityEvent event)
	{
		//TODO: EVENT HANDLING
		if(event instanceof LongLastingModifier)
		{
			activeEntityEvents.add((LongLastingModifier) event);
		}
	}
	

	public void draw(Graphics2D g2d) {

		g2d.drawImage(image, null, x, y);

	}
	
	
	public Rectangle getTopLine()
	{
		Rectangle topLine = new Rectangle(x, y, width, 1);
		
		return topLine;
	}
	
	public Rectangle getBotLine()
	{
		Rectangle botLine = new Rectangle(x, y+height, width, 1);
		
		return botLine;
	}
	public Rectangle getLeftLine()
	{
		Rectangle leftLine = new Rectangle(x, y, 1, height);
		
		return leftLine;
	}
	public Rectangle getRightLine()
	{
		Rectangle rightLine = new Rectangle(x+width, y, 1, height);
		
		return rightLine;
	}
	
	public boolean intersects(StaticGameEntity entity) {
		Rectangle recThis = getCollisionBox();
		Rectangle recOther = entity.getCollisionBox();

		return recThis.intersects(recOther);
	}

	/**
	 * Creates a new rectangle with entities coordinates, width and height
	 * 
	 * @return
	 */
	public Rectangle getCollisionBox() {
		return new Rectangle(x, y,
				width, height);
	}

	public boolean intersects(Rectangle r) {
		return getCollisionBox().intersects(r);
	}

	public boolean contains(StaticGameEntity o) {
		Rectangle r1 = getCollisionBox();
		Rectangle r2 = o.getCollisionBox();
		return r1.contains(r2);
	}

	public boolean contains(Rectangle r) {
		return getCollisionBox().contains(r);
	}
	
	protected void addEntityEventToQueue(LongLastingModifier event)
	{
		activeEntityEvents.add(event);
	}
	protected boolean removedEntityEventFromQueue(LongLastingModifier event)
	{
		return activeEntityEvents.remove(event);
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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
