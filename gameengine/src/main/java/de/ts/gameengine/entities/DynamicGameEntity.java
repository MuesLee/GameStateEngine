package de.ts.gameengine.entities;

import java.awt.Graphics2D;

import de.ts.gameengine.collision.Collision;
import de.ts.gameengine.collision.CollisionSide;
import de.ts.gameengine.controls.AnalogControlAction;
import de.ts.gameengine.controls.AnalogDirection;
import de.ts.gameengine.entities.movement.AnalogMoveActionHandler;
import de.ts.gameengine.entities.movement.Diff;

public abstract class DynamicGameEntity extends StaticGameEntity {

	protected boolean standsOnSolidGround;
	protected boolean isJumping;
	protected AnalogDirection currentMoveDirection;
	
	protected AnalogDirection currentViewDirection = AnalogDirection.RIGHT;

	private AnalogControlAction controlActions;
	private AnalogMoveActionHandler movementHandler;
	
	protected double moveSpeed;
	protected double moveSpeedMax;
	protected double moveSpeedSlowDownRate;
	protected double moveSpeedIncreaseRate;
	
	protected double fallSpeed;
	protected double fallSpeedIncreaseRate;
	protected double fallSpeedMax;

	protected Animation animation;
	protected int currentAction;
	protected int previousAction;

	public DynamicGameEntity(AnalogMoveActionHandler movementHandler) {
		super();
		this.setMovementHandler(movementHandler);
		this.setMoveActions(new AnalogControlAction());
		setAnimation(new Animation());

	}

	@Override
	public void update() {

		move();
		getAnimation().update();
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(animation.getImage(), null, x, y);
	}

	private void move() {
		
		AnalogDirection convertMovementOfControlAction = movementHandler.convertMovementOfControlAction(controlActions);
		
		if(convertMovementOfControlAction != null)
		{
			currentViewDirection = convertMovementOfControlAction;
			
			moveSpeed = Math.min(moveSpeedMax, moveSpeed+moveSpeedIncreaseRate);
		}
		else 
		{
			moveSpeed = Math.max(0, moveSpeed-moveSpeedSlowDownRate);
		}
		
		Diff diff = currentViewDirection.getDiff();
		x = (int) (x + (diff.getVectorX()*moveSpeed));				
		y = (int) (y + (diff.getVectorY()*moveSpeed));				
	}
	
	@Override
	public void handleCollision(Collision collision) {
		super.handleCollision(collision);
		
		if(collision.getSideCollidedEntityHitsIn() == CollisionSide.BOT)
		{
			this.standsOnSolidGround = true;
		}
		
	}

	public double getMoveSpeedSlowDownRate() {
		return moveSpeedSlowDownRate;
	}

	public void setMoveSpeedSlowDownRate(double moveSpeedSlowDownRate) {
		this.moveSpeedSlowDownRate = moveSpeedSlowDownRate;
	}

	public AnalogControlAction getMoveActions() {
		return controlActions;
	}

	public void setMoveActions(AnalogControlAction moveActions) {
		this.controlActions = moveActions;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public int getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(int currentAction) {
		this.currentAction = currentAction;
	}

	public int getPreviousAction() {
		return previousAction;
	}

	public void setPreviousAction(int previousAction) {
		this.previousAction = previousAction;
	}

	public boolean isStandsOnSolidGround() {
		return standsOnSolidGround;
	}

	public void setStandsOnSolidGround(boolean standsOnSolidGround) {
		this.standsOnSolidGround = standsOnSolidGround;
	}

	public double getFallSpeedIncreaseRate() {
		return fallSpeedIncreaseRate;
	}

	public void setFallSpeedIncreaseRate(double fallSpeedIncreaseRate) {
		this.fallSpeedIncreaseRate = fallSpeedIncreaseRate;
	}

	public double getFallSpeedMax() {
		return fallSpeedMax;
	}

	public void setFallSpeedMax(double fallSpeedMax) {
		this.fallSpeedMax = fallSpeedMax;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}


	public double getFallSpeed() {
		return fallSpeed;
	}

	public void setFallSpeed(double fallSpeed) {
		this.fallSpeed = fallSpeed;
	}


	public AnalogDirection getCurrentMoveDirection() {
		return currentMoveDirection;
	}

	public void setCurrentMoveDirection(AnalogDirection currentMoveDirection) {
		this.currentMoveDirection = currentMoveDirection;
	}

	public AnalogMoveActionHandler getMovementHandler() {
		return movementHandler;
	}

	public void setMovementHandler(AnalogMoveActionHandler movementHandler) {
		this.movementHandler = movementHandler;
	}

	public AnalogDirection getCurrentViewDirection() {
		return currentViewDirection;
	}

	public void setCurrentViewDirection(AnalogDirection currentViewDirection) {
		this.currentViewDirection = currentViewDirection;
	}

	public double getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(double moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public double getMoveSpeedMax() {
		return moveSpeedMax;
	}

	public void setMoveSpeedMax(double moveSpeedMax) {
		this.moveSpeedMax = moveSpeedMax;
	}

	public double getMoveSpeedIncreaseRate() {
		return moveSpeedIncreaseRate;
	}

	public void setMoveSpeedIncreaseRate(double moveSpeedIncreaseRate) {
		this.moveSpeedIncreaseRate = moveSpeedIncreaseRate;
	}
	
	
}
