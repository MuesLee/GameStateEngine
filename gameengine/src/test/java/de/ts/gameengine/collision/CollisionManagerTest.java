package de.ts.gameengine.collision;

import java.awt.Rectangle;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.ts.gameengine.entities.DynamicGameEntity;
import de.ts.gameengine.entities.movement.AnalogMoveActionHandler;
import de.ts.gameengine.entities.movement.EightWayMovementHandler;
import de.ts.gameengine.entities.movement.GameInputHandler;
import de.ts.gameengine.entities.movement.JumpActionHandler;
import de.ts.gameengine.entities.movement.SpecialMoveActionHandler;
import de.ts.gameengine.entities.movement.Velocity;
import de.ts.gameengine.gamestates.AbstractGameLevel;

public class CollisionManagerTest {

	private static final int HEIGHT = 100;
	private static final int WIDTH = 100;
	private CollisionManager classUnderTest;
	private DynamicGameEntity entity; 	
	
	@Mock
	private AbstractGameLevel gameState;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		AnalogMoveActionHandler analogMoveActionHandler = new EightWayMovementHandler();
		SpecialMoveActionHandler specialMoveActionHandler = new JumpActionHandler();
		entity = new DynamicGameEntity(new GameInputHandler(analogMoveActionHandler, specialMoveActionHandler)) {
		};
		entity.setWidth(WIDTH);
		entity.setHeight(HEIGHT);
		classUnderTest = new CollisionManager(gameState);
	}
	
	
	
	@Test
	public void testGetVisitedAreaForEntity() throws Exception {
	
	int velX = 10;
	int velY = 0;
		
	Velocity vel = new Velocity(velX, velY);	
	entity.setVelocity(vel);
	
	Rectangle expected = new Rectangle(0, 0, WIDTH+velX, HEIGHT+velY);
	Rectangle actual = classUnderTest.getVisitedAreaForEntity(entity);
	
	assertEquals(expected, actual);
	}
	
	@Test
	public void testGetSearchRectangleForEntity() throws Exception {
		
		int velX = 10;
		int velY = 0;
		
		int entX = 100;
		int entY = 100;
			
		Velocity vel = new Velocity(velX, velY);	
		entity.setVelocity(vel);
		entity.setX(entX);
		entity.setY(entY);
		
		Rectangle expected = new Rectangle(entX-CollisionManager.SEARCH_RANGE_X, entY-CollisionManager.SEARCH_RANGE_Y, WIDTH+2*CollisionManager.SEARCH_RANGE_X, HEIGHT+2*CollisionManager.SEARCH_RANGE_Y);
		Rectangle actual = classUnderTest.getSearchRectangleForEntity(entity);
		
		assertEquals(expected, actual);
		
	}
}

