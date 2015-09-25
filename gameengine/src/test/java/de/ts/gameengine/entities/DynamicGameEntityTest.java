package de.ts.gameengine.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.ts.gameengine.controls.AnalogControlAction;
import de.ts.gameengine.entities.movement.EightWayMovementHandler;

public class DynamicGameEntityTest {

	private static final int FALL_SPEED_MAX = 10;
	private static final int FALL_SPEED_INCREASE_RATE = 5;
	private static final Animation ANIMATION2 = new Animation();
	private static final int JUMP_SPEED_INCREASE = 5;
	private static final int JUMP_SPEED_TAKE_OFF_SPEED = 10;
	public static final int JUMP_SPEED_MAX = 10;
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
		EightWayMovementHandler handler = new EightWayMovementHandler();
		Enemy enemy = new Enemy(handler);
		this.classUnderTest = enemy;
		
		classUnderTest.setMoveSpeedMax(MOVE_SPEED_MAX);
		classUnderTest.setMoveSpeedIncreaseRate(MOVE_SPEED_INCREASE_RATE);
		classUnderTest.setMoveSpeedSlowDownRate(MOVE_SPEED_SLOW_DOWN_RATE);
		classUnderTest.setJumpSpeedMax(JUMP_SPEED_MAX);
		classUnderTest.setJumpSpeedTakeOffSpeed(JUMP_SPEED_TAKE_OFF_SPEED);
		classUnderTest.setJumpSpeedIncrease(JUMP_SPEED_INCREASE);
		classUnderTest.setAnimation(ANIMATION2);
		classUnderTest.setFallSpeedIncreaseRate(FALL_SPEED_INCREASE_RATE);
		classUnderTest.setFallSpeedMax(FALL_SPEED_MAX);
		
		moveActions = new AnalogControlAction();
		classUnderTest.setMoveActions(moveActions);
	}
	
	
	@Test
	public void testEntityMoves1StepRight() throws Exception {
		
		moveActions.setRight(true);
		classUnderTest.update();

		int expected = MOVE_SPEED_INCREASE_RATE;
		int actual = classUnderTest.getX();

		assertEquals(expected, actual);
	}
	@Test
	public void testEntityMoves1Stepleft() throws Exception {
		
		moveActions.setLeft(true);
		classUnderTest.update();
		
		int expected = -MOVE_SPEED_INCREASE_RATE;
		int actual = classUnderTest.getX();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEntityMoves2StepRight() throws Exception {
		
		moveActions.setRight(true);
		classUnderTest.update();
		moveActions.setRight(true);
		classUnderTest.update();

		int expected = MOVE_SPEED_INCREASE_RATE + 2 * MOVE_SPEED_INCREASE_RATE;
		int actual = classUnderTest.getX();

		assertEquals(expected, actual);
	}
	@Test
	public void testEntityMoves2StepLeft() throws Exception {
		
		moveActions.setLeft(true);
		classUnderTest.update();
		moveActions.setLeft(true);
		classUnderTest.update();
		
		int expected = -(MOVE_SPEED_INCREASE_RATE + 2 * MOVE_SPEED_INCREASE_RATE);
		int actual = classUnderTest.getX();
		
		assertEquals(expected, actual);
	}
	@Test
	public void testEntityMoves2StepRightThen1StepNoMove() throws Exception {
		
		moveActions.setRight(true);
		classUnderTest.update();
		moveActions.setRight(true);
		classUnderTest.update();
		
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
		classUnderTest.update();
		moveActions.setLeft(true);
		classUnderTest.update();
		
		moveActions.setLeft(false);
		
		classUnderTest.update();
		
		int activeMovement = MOVE_SPEED_INCREASE_RATE + 2 * MOVE_SPEED_INCREASE_RATE;
		int passiveMovement = 2 * MOVE_SPEED_INCREASE_RATE-MOVE_SPEED_SLOW_DOWN_RATE;
		
		int expected = -(activeMovement + passiveMovement);
		int actual = classUnderTest.getX();
		
		assertEquals(expected, actual);
	}
	@Test
	public void testEntityJumps1Step() throws Exception {
		
		moveActions.setJump(true);
		classUnderTest.setStandsOnSolidGround(true);
		
		classUnderTest.update();
		
		int expected = -(JUMP_SPEED_TAKE_OFF_SPEED+JUMP_SPEED_INCREASE);
		int actual = classUnderTest.getY();
		
		assertEquals(expected, actual);
	}
	@Test
	public void testEntityJumps2Step() throws Exception {
		
		classUnderTest.setStandsOnSolidGround(true);
		moveActions.setJump(true);
		classUnderTest.update();
		moveActions.setJump(true);
		classUnderTest.setStandsOnSolidGround(false);
		classUnderTest.update();
		
		int expected = -(JUMP_SPEED_TAKE_OFF_SPEED+JUMP_SPEED_INCREASE+2*JUMP_SPEED_INCREASE);
		int actual = classUnderTest.getY();
		
		assertEquals(expected, actual);
	}
	@Test
	public void testEntityJumps1StepWhileRight() throws Exception {
		
		moveActions.setJump(true);
		classUnderTest.setStandsOnSolidGround(true);
		moveActions.setRight(true);
		
		classUnderTest.update();
		
		int expectedY = -(JUMP_SPEED_TAKE_OFF_SPEED+JUMP_SPEED_INCREASE);
		int actualY = classUnderTest.getY();
		int expectedX = MOVE_SPEED_INCREASE_RATE;
		int actualX = classUnderTest.getX();
		
		assertEquals(expectedY, actualY);
		assertEquals(expectedX, actualX);
	}
	@Test
	public void testEntityFalls1Step() throws Exception {
		
		moveActions.setUp(false);
		classUnderTest.setStandsOnSolidGround(false);
		int entityBaseY = 20;
		classUnderTest.setY(entityBaseY);
		
		classUnderTest.update();
		
		int expected = entityBaseY-FALL_SPEED_INCREASE_RATE;
		int actual = classUnderTest.getY();
		
		assertEquals(expected, actual);
	}

}
