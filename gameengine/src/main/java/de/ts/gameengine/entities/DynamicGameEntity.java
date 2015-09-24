package de.ts.gameengine.entities;

import java.awt.Graphics2D;

import de.ts.gameengine.collision.Collision;
import de.ts.gameengine.collision.CollisionSide;

public abstract class DynamicGameEntity extends StaticGameEntity {

	protected boolean isFacingRight;
	protected boolean standsOnSolidGround;
	protected boolean isJumping;

	private ControlAction moveActions;

	protected double moveSpeedX;
	protected double moveSpeedXMax;
	protected double moveSpeedY;
	protected double moveSpeedYMax;
	protected double moveSpeedSlowDownRate;
	protected double moveSpeedXIncreaseRate;
	protected double moveSpeedYIncreaseRate;
	
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

		this.setMoveActions(new ControlAction());
		setAnimation(new Animation());

	}

	@Override
	public void update() {

		move();
		moveActions.reset();
		getAnimation().update();
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(animation.getImage(), null, x, y);
	}

	private void move() {

		// X Axis
		if (getMoveActions().isRight()) {
			moveSpeedX = Math.min(moveSpeedXMax, moveSpeedX
					+ moveSpeedXIncreaseRate);
			x += moveSpeedX;
		} else if (getMoveActions().isLeft()) {
			moveSpeedX = Math.min(moveSpeedXMax, moveSpeedX
					+ moveSpeedXIncreaseRate);
			x -= moveSpeedX;
		} else {
			moveSpeedX = Math.max(0, moveSpeedX-moveSpeedSlowDownRate);
		}
		// Y Axis
		if (getMoveActions().isUp()) {
			moveSpeedY = Math.min(moveSpeedYMax, moveSpeedY
					+ moveSpeedYIncreaseRate);
			y += moveSpeedY;
		} else if (getMoveActions().isDown()) {
			moveSpeedY = Math.min(moveSpeedYMax, moveSpeedY
					+ moveSpeedYIncreaseRate);
			y -= moveSpeedY;
		} else {
			moveSpeedY = Math.max(0, moveSpeedY-moveSpeedSlowDownRate);
		}
		
		if (getMoveActions().isJump()) {
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

	public double getJumpSpeedIncrease() {
		return jumpSpeedIncreaseRate;
	}

	public void setJumpSpeedIncrease(double jumpSpeedIncrease) {
		this.jumpSpeedIncreaseRate = jumpSpeedIncrease;
	}

	public ControlAction getMoveActions() {
		return moveActions;
	}

	public void setMoveActions(ControlAction moveActions) {
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

	public double getMoveSpeedX() {
		return moveSpeedX;
	}

	public void setMoveSpeedX(double moveSpeedX) {
		this.moveSpeedX = moveSpeedX;
	}

	public double getMoveSpeedXMax() {
		return moveSpeedXMax;
	}

	public void setMoveSpeedXMax(double moveSpeedXMax) {
		this.moveSpeedXMax = moveSpeedXMax;
	}

	public double getMoveSpeedY() {
		return moveSpeedY;
	}

	public void setMoveSpeedY(double moveSpeedY) {
		this.moveSpeedY = moveSpeedY;
	}

	public double getMoveSpeedYMax() {
		return moveSpeedYMax;
	}

	public void setMoveSpeedYMax(double moveSpeedYMax) {
		this.moveSpeedYMax = moveSpeedYMax;
	}

	public double getJumpSpeedIncreaseRate() {
		return jumpSpeedIncreaseRate;
	}

	public void setJumpSpeedIncreaseRate(double jumpSpeedIncreaseRate) {
		this.jumpSpeedIncreaseRate = jumpSpeedIncreaseRate;
	}

	public double getMoveSpeedXIncreaseRate() {
		return moveSpeedXIncreaseRate;
	}

	public void setMoveSpeedXIncreaseRate(double moveSpeedXIncreaseRate) {
		this.moveSpeedXIncreaseRate = moveSpeedXIncreaseRate;
	}

	public double getMoveSpeedYIncreaseRate() {
		return moveSpeedYIncreaseRate;
	}

	public void setMoveSpeedYIncreaseRate(double moveSpeedYIncreaseRate) {
		this.moveSpeedYIncreaseRate = moveSpeedYIncreaseRate;
	}
}
