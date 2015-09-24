package de.ts.gameengine.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DynamicGameEntityTest {

	private DynamicGameEntity classUnderTest;
	private ControlAction moveActions;
	
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;

	@Before
	public void init() throws Exception {
		this.classUnderTest = new Enemy();
		
		classUnderTest.setMoveSpeedXMax(15);
		classUnderTest.setMoveSpeedYMax(15);
		classUnderTest.setMoveSpeedXIncreaseRate(5);
		classUnderTest.setMoveSpeedYIncreaseRate(5);
		classUnderTest.setMoveSpeedSlowDownRate(5);
		classUnderTest.setJumpSpeedMax(10);
		classUnderTest.setJumpSpeedTakeOffSpeed(10);
		classUnderTest.setJumpSpeedIncrease(5);
		classUnderTest.setAnimation(new Animation());
		classUnderTest.setFallSpeedIncreaseRate(5);
		classUnderTest.setFallSpeedMax(10);
		
		moveActions = new ControlAction();
		classUnderTest.setMoveActions(moveActions);
	}
	
	
	@Test
	public void testEntityMoves1StepRight() throws Exception {
		
		moveActions.setRight(true);

		classUnderTest.update();

		int expected = 5;
		int actual = classUnderTest.getX();

		assertEquals(expected, actual);
	}
	@Test
	public void testEntityMoves1Stepleft() throws Exception {
		
		moveActions.setLeft(true);
		
		classUnderTest.update();
		
		int expected = -5;
		int actual = classUnderTest.getX();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEntityMoves2StepRight() throws Exception {
		
		moveActions.setRight(true);

		classUnderTest.update();
		classUnderTest.update();

		int expected = 15;
		int actual = classUnderTest.getX();

		assertEquals(expected, actual);
	}
	@Test
	public void testEntityMoves2StepLeft() throws Exception {
		
		moveActions.setLeft(true);
		
		classUnderTest.update();
		classUnderTest.update();
		
		int expected = -15;
		int actual = classUnderTest.getX();
		
		assertEquals(expected, actual);
	}
	@Test
	public void testEntityMoves2StepRightThen1StepNoMove() throws Exception {
		
		moveActions.setRight(true);

		classUnderTest.update();
		classUnderTest.update();
		
		moveActions.setRight(false);
		
		classUnderTest.update();

		int expected = 15;
		int actual = classUnderTest.getX();

		assertEquals(expected, actual);
	}
	
	@Test
	public void testEntityMoves2StepLeftThen1StepNoMove() throws Exception {
		
		moveActions.setLeft(true);
		
		classUnderTest.update();
		classUnderTest.update();
		
		moveActions.setLeft(false);
		
		classUnderTest.update();
		
		int expected = -15;
		int actual = classUnderTest.getX();
		
		assertEquals(expected, actual);
	}
	@Test
	public void testEntityJumps1Step() throws Exception {
		
		moveActions.setUp(true);
		classUnderTest.setStandsOnSolidGround(true);
		
		classUnderTest.update();
		
		int expected = -10;
		int actual = classUnderTest.getY();
		
		assertEquals(expected, actual);
	}
	@Test
	public void testEntityJumps2Step() throws Exception {
		
		moveActions.setUp(true);
		classUnderTest.setStandsOnSolidGround(true);
		
		classUnderTest.update();
		classUnderTest.setStandsOnSolidGround(false);
		classUnderTest.update();
		
		int expected = -20;
		int actual = classUnderTest.getY();
		
		assertEquals(expected, actual);
	}
	@Test
	public void testEntityJumps1StepWhileRight() throws Exception {
		
		moveActions.setUp(true);
		classUnderTest.setStandsOnSolidGround(true);
		moveActions.setRight(true);
		
		classUnderTest.update();
		
		int expectedY = -10;
		int actualY = classUnderTest.getY();
		int expectedX = 5;
		int actualX = classUnderTest.getX();
		
		assertEquals(expectedY, actualY);
		assertEquals(expectedX, actualX);
	}
	@Test
	public void testEntityFalls1Step() throws Exception {
		
		moveActions.setUp(false);
		classUnderTest.setStandsOnSolidGround(false);
		classUnderTest.setY(20);
		
		classUnderTest.update();
		
		int expected = 25;
		int actual = classUnderTest.getY();
		
		assertEquals(expected, actual);
	}

}
