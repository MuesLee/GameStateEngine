package de.ts.gameengine.entities;

import java.awt.image.BufferedImage;

import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import de.ts.gameengine.controls.AnalogControlAction;
import de.ts.gameengine.controls.AnalogDirection;
import de.ts.gameengine.entities.movement.AnalogMoveActionHandler;
import de.ts.gameengine.entities.movement.GameInputHandler;
import de.ts.gameengine.entities.movement.SpecialMoveAction;
import de.ts.gameengine.entities.movement.SpecialMoveActionHandler;
import de.ts.gameengine.entities.movement.Velocity;

public abstract class DynamicGameEntity extends StaticGameEntity {

	protected AnalogDirection currentMoveDirection;
	protected AnalogDirection currentViewDirection = AnalogDirection.RIGHT;

	private AnalogControlAction controlActions;
	private GameInputHandler gameInputHandler;

	private boolean ignoresGravity;
	private Velocity velocity;
	protected boolean standsOnSolidGround;

	protected double moveSpeed;
	protected double moveSpeedMax;
	protected double moveSpeedSlowDownRate;
	protected double moveSpeedIncreaseRate;

	protected boolean isJumping;
	private boolean isReadyToJump;
	protected double jumpSpeed;
	protected double jumpSpeedMax;
	protected double jumpSpeedIncreaseRate;
	protected double jumpSpeedTakeOff;

	protected Animation animation;
	protected int currentAction;
	protected int previousAction;

	public DynamicGameEntity(GameInputHandler gameInputHandler) {
		super();
		this.setGameInputHandler(gameInputHandler);
		this.setMoveActions(new AnalogControlAction());
		setAnimation(new Animation());
		this.setVelocity(new Velocity(0, 0));
		setLinearVelocity(new Vector2(0.0, 0.0));
		setAngularVelocity(0.0);
		setMass(MassType.NORMAL);
	}

	/**
	 * Handle User Inputs and compute Velocity
	 */
	public void prepareUpdate() {
		prepareRegularMovement();

		prepareSpecialAction();

		Vector2 force = new Vector2(velocity.getVectorX(), velocity.getVectorY());
		applyForce(force);
		this.velocity.reset();
	}

	private void prepareRegularMovement() {

		AnalogMoveActionHandler movementHandler = gameInputHandler.getAnalogMoveActionHandler();
		AnalogDirection convertMovementOfControlAction = movementHandler.convertMovementOfControlAction(controlActions);

		if (convertMovementOfControlAction != null) {
			currentViewDirection = convertMovementOfControlAction;

			moveSpeed = Math.min(moveSpeedMax, moveSpeed + moveSpeedIncreaseRate);
		} else {
			moveSpeed = Math.max(0, moveSpeed - moveSpeedSlowDownRate);
		}

		Velocity diff = currentViewDirection.getDiff();
		double velocityX = diff.getVectorX() * moveSpeed;
		double velocityY = diff.getVectorY() * moveSpeed;
		velocity.update(velocityX, velocityY);
	}

	/**
	 * Update Animation and move according to velocity
	 */
	@Override
	public void update() {
		getAnimation().update();
	}

	private void prepareSpecialAction() {
		SpecialMoveActionHandler specialMoveActionHandler = gameInputHandler.getSpecialMoveActionHandler();
		SpecialMoveAction specialMoveAction = specialMoveActionHandler
				.convertSpecialActionOfControlAction(controlActions);

		double velocityY = 0;
		double velocityX = 0;

		if (specialMoveAction == SpecialMoveAction.JUMP) {
			if (standsOnSolidGround) {
				setJumping(true);
				setStandsOnSolidGround(false);
				velocityY -= jumpSpeedTakeOff;
				jumpSpeed = Math.min(jumpSpeedMax, jumpSpeed += jumpSpeedIncreaseRate);
			} else {
				if (isJumping() && isReadyToJump()) {
					jumpSpeed = Math.min(jumpSpeedMax, jumpSpeed += jumpSpeedIncreaseRate);
					if (jumpSpeed == jumpSpeedMax) {
						setReadyToJump(false);
					}
				}
			}
		} else {
			setJumping(false);
			jumpSpeed = 0;
		}

		velocityY -= jumpSpeed;
		velocity.update(velocityX, velocityY);
	}

	public Velocity getVelocity() {
		return velocity;
	}

	@Override
	protected BufferedImage getdrawImage() {
		return animation.getImage();
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
		if (standsOnSolidGround) {
			setReadyToJump(true);
		}

	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public AnalogDirection getCurrentMoveDirection() {
		return currentMoveDirection;
	}

	public void setCurrentMoveDirection(AnalogDirection currentMoveDirection) {
		this.currentMoveDirection = currentMoveDirection;
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

	public GameInputHandler getGameInputHandler() {
		return gameInputHandler;
	}

	public void setGameInputHandler(GameInputHandler gameInputHandler) {
		this.gameInputHandler = gameInputHandler;
	}

	public double getJumpSpeed() {
		return jumpSpeed;
	}

	public void setJumpSpeed(double jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}

	public double getJumpSpeedMax() {
		return jumpSpeedMax;
	}

	public void setJumpSpeedMax(double jumpSpeedMax) {
		this.jumpSpeedMax = jumpSpeedMax;
	}

	public double getJumpSpeedIncreaseRate() {
		return jumpSpeedIncreaseRate;
	}

	public void setJumpSpeedIncreaseRate(double jumpSpeedIncreaseRate) {
		this.jumpSpeedIncreaseRate = jumpSpeedIncreaseRate;
	}

	public double getJumpSpeedTakeOff() {
		return jumpSpeedTakeOff;
	}

	public void setJumpSpeedTakeOff(double jumpSpeedTakeOff) {
		this.jumpSpeedTakeOff = jumpSpeedTakeOff;
	}

	public boolean isReadyToJump() {
		return isReadyToJump;
	}

	public void setReadyToJump(boolean isReadyToJump) {
		this.isReadyToJump = isReadyToJump;
	}

	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

	public boolean isIgnoresGravity() {
		return ignoresGravity;
	}

	public void setIgnoresGravity(boolean ignoresGravity) {
		this.ignoresGravity = ignoresGravity;
	}

}
