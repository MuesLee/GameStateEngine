package de.ts.gameengine.entities;

import java.awt.Graphics2D;

import de.ts.gameengine.collision.Collision;
import de.ts.gameengine.collision.CollisionSide;

public abstract class DynamicGameEntity extends StaticGameEntity {

	protected boolean isFacingRight;
	protected boolean standsOnSolidGround;
	protected boolean isJumping;

	private MoveActions moveActions;

	protected double moveSpeed;
	protected double moveSpeedMax;
	protected double moveSpeedSlowDownRate;
	protected double moveSpeedIncreaseRate;
	protected double jumpSpeed;
	protected double jumpSpeedTakeOffSpeed;
	protected double jumpSpeedIncreaseRate;
	protected double jumpSpeedMax;
	
	protected double fallSpeed;
	protected double fallSpeedIncreaseRate;
	protected double fallSpeedMax;

	protected Animation animation;
	protected int currentAction;
	protected int previousAction;

	public DynamicGameEntity() {
		super();

		this.setMoveActions(new MoveActions());
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

		// X Axis
		if (getMoveActions().isMovingRight()) {
			moveSpeed = Math.min(moveSpeedMax, moveSpeed
					+ moveSpeedIncreaseRate);
			x += moveSpeed;
		} else if (getMoveActions().isMovingLeft()) {
			moveSpeed = Math.min(moveSpeedMax, moveSpeed
					+ moveSpeedIncreaseRate);
			x -= moveSpeed;
		} else {
			moveSpeed = 0;
		}
		// Y Axis
		if (getMoveActions().isJumping()) {
			if (standsOnSolidGround) {
				jumpSpeed = Math.min(getJumpSpeedMax(), jumpSpeed
						+ jumpSpeedTakeOffSpeed);
				y-=jumpSpeed;
				isJumping = true;
			}
			else {
				if(isJumping)
				{
					jumpSpeed = Math.min(getJumpSpeedMax(), jumpSpeed
							+ jumpSpeedIncreaseRate);
					if(jumpSpeed == jumpSpeedMax)
					{
						isJumping = false;
					}
					y-=jumpSpeed;
				}
			}
		} else {
			if(!standsOnSolidGround)
			{
				setFallSpeed(Math.min(fallSpeedMax, getFallSpeed()
						+ fallSpeedIncreaseRate));
				y+= fallSpeed;
			}
			else {
				setFallSpeed(0);
			}
		}
	}
	
	@Override
	public void handleCollision(Collision collision) {
		super.handleCollision(collision);
		
		if(collision.getSideCollidedEntityHitsIn() == CollisionSide.BOT)
		{
			this.standsOnSolidGround = true;
		}
		
	}

	public boolean isFacingRight() {
		return isFacingRight;
	}

	public void setFacingRight(boolean isFacingRight) {
		this.isFacingRight = isFacingRight;
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

	public double getMoveSpeedSlowDownRate() {
		return moveSpeedSlowDownRate;
	}

	public void setMoveSpeedSlowDownRate(double moveSpeedSlowDownRate) {
		this.moveSpeedSlowDownRate = moveSpeedSlowDownRate;
	}

	public double getJumpSpeedMax() {
		return jumpSpeedMax;
	}

	public void setJumpSpeedMax(double jumpSpeedMax) {
		this.jumpSpeedMax =jumpSpeedMax;
	}

	public double getJumpSpeed() {
		return jumpSpeed;
	}

	public void setJumpSpeed(double jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}

	public double getMoveSpeedIncreaseRate() {
		return moveSpeedIncreaseRate;
	}

	public void setMoveSpeedIncreaseRate(double moveSpeedIncreaseRate) {
		this.moveSpeedIncreaseRate = moveSpeedIncreaseRate;
	}

	public double getJumpSpeedIncrease() {
		return jumpSpeedIncreaseRate;
	}

	public void setJumpSpeedIncrease(double jumpSpeedIncrease) {
		this.jumpSpeedIncreaseRate = jumpSpeedIncrease;
	}

	public MoveActions getMoveActions() {
		return moveActions;
	}

	public void setMoveActions(MoveActions moveActions) {
		this.moveActions = moveActions;
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

	public double getJumpSpeedTakeOffSpeed() {
		return jumpSpeedTakeOffSpeed;
	}

	public void setJumpSpeedTakeOffSpeed(double jumpSpeedTakeOffSpeed) {
		this.jumpSpeedTakeOffSpeed = jumpSpeedTakeOffSpeed;
	}

	public double getFallSpeed() {
		return fallSpeed;
	}

	public void setFallSpeed(double fallSpeed) {
		this.fallSpeed = fallSpeed;
	}
}
