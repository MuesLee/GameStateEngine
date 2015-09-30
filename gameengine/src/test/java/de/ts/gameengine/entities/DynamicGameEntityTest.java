package de.ts.gameengine.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.ts.gameengine.controls.AnalogControlAction;
import de.ts.gameengine.entities.movement.AnalogMoveActionHandler;
import de.ts.gameengine.entities.movement.EightWayMovementHandler;
import de.ts.gameengine.entities.movement.GameInputHandler;
import de.ts.gameengine.entities.movement.JumpActionHandler;
import de.ts.gameengine.entities.movement.SpecialMoveActionHandler;

public class DynamicGameEntityTest {

	private static final Animation ANIMATION2 = new Animation();
	private static final int JUMP_SPEED_TAKE_OFF_SPEED = 15;
	private static final int JUMP_SPEED_INCREASE = 5;
	private static final int JUMP_SPEED_MAX = 10;
	private static final int MOVE_SPEED_SLOW_DOWN_RATE = 5;
	private static final int MOVE_SPEED_INCREASE_RATE = 5;
	private static final int MOVE_SPEED_MAX = 15;
	private DynamicGameEntity classUnderTest;
	private AnalogControlAction moveActions;
	
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;

	@Before
	public void init() throws Exception {
		AnalogMoveActionHandler handler = new EightWayMovementHandler();
		SpecialMoveActionHandler specialMoveActionHandler = new JumpActionHandler();
		GameInputHandler inputHandler = new GameInputHandler(handler, specialMoveActionHandler);
		Enemy enemy = new Enemy(inputHandler);
		this.classUnderTest = enemy;
		
		classUnderTest.setMoveSpeedMax(MOVE_SPEED_MAX);
		classUnderTest.setMoveSpeedIncreaseRate(MOVE_SPEED_INCREASE_RATE);
		classUnderTest.setMoveSpeedSlowDownRate(MOVE_SPEED_SLOW_DOWN_RATE);
		classUnderTest.setJumpSpeedIncreaseRate(JUMP_SPEED_INCREASE);
		classUnderTest.setJumpSpeedMax(JUMP_SPEED_MAX);
		classUnderTest.setJumpSpeedTakeOff(JUMP_SPEED_TAKE_OFF_SPEED);
		classUnderTest.setAnimation(ANIMATION2);
		
		moveActions = new AnalogControlAction();
		classUnderTest.setMoveActions(moveActions);
	}
	
	
	@Test
	public void testEntityMoves1StepRight() throws Exception {
		
		moveActions.setRight(true);
		classUnderTest.prepareUpdate();
		classUnderTest.update();

		int expected = MOVE_SPEED_INCREASE_RATE;
		int actual = classUnderTest.getX();

		assertEquals(expected, actual);
	}
	@Test
	public void testEntityMoves1Stepleft() throws Exception {
		
		moveActions.setLeft(true);
		classUnderTest.prepareUpdate();
		classUnderTest.update();
		
		int expected = -MOVE_SPEED_INCREASE_RATE;
		int actual = classUnderTest.getX();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEntityMoves2StepRight() throws Exception {
		
		moveActions.setRight(true);
		classUnderTest.prepareUpdate();
		classUnderTest.update();
		moveActions.setRight(true);
		classUnderTest.prepareUpdate();
		classUnderTest.update();

		int expected = MOVE_SPEED_INCREASE_RATE + 2 * MOVE_SPEED_INCREASE_RATE;
		int actual = classUnderTest.getX();

		assertEquals(expected, actual);
	}
	@Test
	public void testEntityMoves2StepLeft() throws Exception {
		
		moveActions.setLeft(true);
		classUnderTest.prepareUpdate();
		classUnderTest.update();
		moveActions.setLeft(true);
		classUnderTest.prepareUpdate();
		classUnderTest.update();
		
		int expected = -(MOVE_SPEED_INCREASE_RATE + 2 * MOVE_SPEED_INCREASE_RATE);
		int actual = classUnderTest.getX();
		
		assertEquals(expected, actual);
	}
	@Test
	public void testEntityMoves2StepRightThen1StepNoMove() throws Exception {
		
		moveActions.setRight(true);
		classUnderTest.prepareUpdate();
		classUnderTest.update();
		moveActions.setRight(true);
		classUnderTest.prepareUpdate();
		classUnderTest.update();
		
		classUnderTest.prepareUpdate();
		classUnderTest.update();

		int activeMovement = MOVE_SPEED_INCREASE_RATE + 2 * MOVE_SPEED_INCREASE_RATE;
		int passiveMovement = 2 * MOVE_SPEED_INCREASE_RATE-MOVE_SPEED_SLOW_DOWN_RATE;
		
		int expected = activeMovement + passiveMovement;
		int actual = classUnderTest.getX();

		assertEquals(expected, actual);
	}
	
	@Test
	public void testEntityMoves2StepLeftThen1StepNoMove() throws Exception {
		
		moveActions.setLeft(true);
		classUnderTest.prepareUpdate();
		classUnderTest.update();
		moveActions.setLeft(true);
		classUnderTest.prepareUpdate();
		classUnderTest.update();
		
		moveActions.setLeft(false);
		classUnderTest.prepareUpdate();
		classUnderTest.update();
		
		int activeMovement = MOVE_SPEED_INCREASE_RATE + 2 * MOVE_SPEED_INCREASE_RATE;
		int passiveMovement = 2 * MOVE_SPEED_INCREASE_RATE-MOVE_SPEED_SLOW_DOWN_RATE;
		
		int expected = -(activeMovement + passiveMovement);
		int actual = classUnderTest.getX();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEntityJumps1Step() throws Exception {
		int baseY = 100;
		
		moveActions.setJump(true);
		classUnderTest.setStandsOnSolidGround(true);
		classUnderTest.setY(baseY);
		
		classUnderTest.prepareUpdate();
		classUnderTest.update();
		
		int actual = classUnderTest.getY();
		
		int expected = baseY-JUMP_SPEED_TAKE_OFF_SPEED-JUMP_SPEED_INCREASE;
		
		assertEquals(expected, actual);
	}
	@Test
	public void testEntityJumps2Steps() throws Exception {
		int baseY = 100;
		classUnderTest.setY(baseY);
		classUnderTest.setStandsOnSolidGround(true);
		
		moveActions.setJump(true);
		classUnderTest.prepareUpdate();
		classUnderTest.update();
		
		moveActions.setJump(true);
		classUnderTest.prepareUpdate();
		classUnderTest.update();
		
		int actual = classUnderTest.getY();
		
		int expected = baseY-JUMP_SPEED_TAKE_OFF_SPEED-JUMP_SPEED_INCREASE-(Math.min(JUMP_SPEED_MAX, 3*JUMP_SPEED_INCREASE));
		
		assertEquals(expected, actual);
	}
	@Test
	public void testEntityJumps2StepWhileMovingRight2Steps() throws Exception {
		int baseY = 100;
		classUnderTest.setY(baseY);
		classUnderTest.setStandsOnSolidGround(true);
		
		moveActions.setJump(true);
		moveActions.setRight(true);
		classUnderTest.prepareUpdate();
		classUnderTest.update();
		
		moveActions.setJump(true);
		moveActions.setRight(true);
		classUnderTest.prepareUpdate();
		classUnderTest.update();
		
		int actualY = classUnderTest.getY();
		int actualX = classUnderTest.getX();
		
		int expectedY = baseY-JUMP_SPEED_TAKE_OFF_SPEED-JUMP_SPEED_INCREASE-(Math.min(JUMP_SPEED_MAX, 3*JUMP_SPEED_INCREASE));
		int expectedX = MOVE_SPEED_INCREASE_RATE + Math.min(MOVE_SPEED_INCREASE_RATE*2, MOVE_SPEED_MAX);
		
		assertEquals(expectedY, actualY);
		assertEquals(expectedX, actualX);
	}
}
