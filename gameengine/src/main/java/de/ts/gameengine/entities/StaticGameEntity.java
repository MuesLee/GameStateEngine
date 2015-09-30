package de.ts.gameengine.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.concurrent.ArrayBlockingQueue;

import com.google.common.eventbus.Subscribe;

import de.ts.gameengine.collision.Collision;
import de.ts.gameengine.controller.GameController;
import de.ts.gameengine.entities.actions.EntityEvent;
import de.ts.gameengine.entities.actions.LongLastingModifier;


public abstract class StaticGameEntity {

	private long id;

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
		id = GameController.getNextIDForEntity();
		
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
		Rectangle topLine = new Rectangle(x, y, width, 0);
		
		return topLine;
	}
	
	public Rectangle getBotLine()
	{
		Rectangle botLine = new Rectangle(x, y+height, width, 0);
		
		return botLine;
	}
	public Rectangle getLeftLine()
	{
		Rectangle leftLine = new Rectangle(x, y, 0, height);
		
		return leftLine;
	}
	public Rectangle getRightLine()
	{
		Rectangle rightLine = new Rectangle(x+width, y, 0, height);
		
		return rightLine;
	}
	
	public void handleCollision(Collision collision)
	{
		
	}
	
	public boolean intersects(StaticGameEntity entity) {
		Rectangle recThis = getCollisionBox();
		Rectangle recOther = entity.getCollisionBox();

		return recThis.intersects(recOther);
	}

	public Rectangle getCollisionBox() {
		return new Rectangle(x - width, y - height,
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (id != other.id)
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
}
