package de.ts.gameengine.collision;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.ts.gameengine.entities.StaticGameEntity;

public class QuadtreeTest {

	private StaticGameEntity entityTopLeft;
	private StaticGameEntity entityTopRight;
	private StaticGameEntity entityBotRight;
	private StaticGameEntity entityBotLeft;

	private StaticGameEntity givenEntity;

	private Rectangle rectangleTopLeft;
	private Rectangle rectangleTopRight;
	private Rectangle rectangleBotLeft;
	private Rectangle rectangleBotRight;

	private Rectangle givenRectangle;

	private Quadtree quadtree;

	@Test
	public void shouldRetrieveTopLeftPossibleCollisions() throws Exception {

		Rectangle givenRectangle = rectangleTopLeft;
		Mockito.when(givenEntity.getCollisionBox()).thenReturn(givenRectangle);
		Mockito.when(givenEntity.getId()).thenReturn(3L);

		List<StaticGameEntity> actual = quadtree.retrieve(null, givenEntity.getCollisionBox());
		List<StaticGameEntity> expected = new ArrayList<StaticGameEntity>();
		expected.add(entityTopLeft);

		assertEquals(expected, actual);
	}

	@Test
	public void shouldRetrieveTopRightPossibleCollisions() throws Exception {

		Rectangle givenRectangle = rectangleTopRight;
		Mockito.when(givenEntity.getCollisionBox()).thenReturn(givenRectangle);
		Mockito.when(givenEntity.getId()).thenReturn(0L);

		List<StaticGameEntity> actual = quadtree.retrieve(null, givenEntity.getCollisionBox());
		List<StaticGameEntity> expected = new ArrayList<StaticGameEntity>();
		expected.add(entityTopRight);
		assertEquals(expected, actual);
	}

	@Test
	public void shouldRetrieveBotLeftPossibleCollisions() throws Exception {

		Rectangle givenRectangle = rectangleBotLeft;
		Mockito.when(givenEntity.getCollisionBox()).thenReturn(givenRectangle);
		Mockito.when(givenEntity.getId()).thenReturn(2L);

		List<StaticGameEntity> actual = quadtree.retrieve(null, givenEntity.getCollisionBox());
		List<StaticGameEntity> expected = new ArrayList<StaticGameEntity>();
		expected.add(entityBotLeft);
		assertEquals(expected, actual);
	}

	@Test
	public void shouldRetrieveBotRightPossibleCollisions() throws Exception {

		Rectangle givenRectangle = rectangleBotRight;
		Mockito.when(givenEntity.getCollisionBox()).thenReturn(givenRectangle);
		Mockito.when(givenEntity.getId()).thenReturn(1L);

		List<StaticGameEntity> actual = quadtree.retrieve(null, givenEntity.getCollisionBox());
		List<StaticGameEntity> expected = new ArrayList<StaticGameEntity>();
		expected.add(entityBotRight);
		assertEquals(expected, actual);
	}

	@Test
	public void shouldRetrieveAllRectanglesAsPossibleCollisions()
			throws Exception {

		Rectangle givenRectangle = new Rectangle(0, 0, 100, 100);
		Mockito.when(givenEntity.getCollisionBox()).thenReturn(givenRectangle);
		List<StaticGameEntity> actual = quadtree.retrieve(null, givenEntity.getCollisionBox());
		List<StaticGameEntity> expected = new ArrayList<StaticGameEntity>();
		expected.add(entityBotLeft);
		expected.add(entityBotRight);
		expected.add(entityTopLeft);
		expected.add(entityTopRight);
		assertTrue(CollectionUtils.isEqualCollection(expected, actual));
	}
	
	@Test
	public void testInsertShouldInsertAt0() throws Exception {
		
		initEmptyQuadTree(new Rectangle (0,0,160,160),5,1);
		
		Rectangle givenRectangle = new Rectangle(0,0,160,160);
		Mockito.when(givenEntity.getCollisionBox()).thenReturn(givenRectangle);
		Mockito.when(givenEntity.getId()).thenReturn(3L);
		
		quadtree.insert(givenEntity);
		
		String expected = "X";
		String actual = quadtree.getPath(givenEntity);
		assertEquals(expected, actual);
	}
	@Test
	public void testInsertShouldInsertAt1() throws Exception {
		
		initEmptyQuadTree(new Rectangle (0,0,160,160),5,1);
		
		Rectangle givenRectangle = new Rectangle(0,0,39,39);
		Mockito.when(givenEntity.getCollisionBox()).thenReturn(givenRectangle);
		Mockito.when(givenEntity.getId()).thenReturn(3L);
		
		quadtree.insert(givenEntity);
		
		String expected = "1X";
		String actual = quadtree.getPath(givenEntity);
		assertEquals(expected, actual);
	}
	@Test
	public void testInsertShouldInsertAt11() throws Exception {
		
		initEmptyQuadTree(new Rectangle (0,0,160,160),5,2);
		
		Rectangle givenRectangle = new Rectangle(0,0,1,1);
		Mockito.when(givenEntity.getCollisionBox()).thenReturn(givenRectangle);
		Mockito.when(givenEntity.getId()).thenReturn(3L);
		
		quadtree.insert(givenEntity);
		
		String expected = "11X";
		String actual = quadtree.getPath(givenEntity);
		assertEquals(expected, actual);
	}
	@Test
	public void testInsertShouldInsertAt10() throws Exception {
		
		initEmptyQuadTree(new Rectangle (0,0,160,160),5,2);
		
		Rectangle givenRectangle = new Rectangle(41,39,1,1);
		Mockito.when(givenEntity.getCollisionBox()).thenReturn(givenRectangle);
		Mockito.when(givenEntity.getId()).thenReturn(3L);
		
		quadtree.insert(givenEntity);
		
		String expected = "10X";
		String actual = quadtree.getPath(givenEntity);
		assertEquals(expected, actual);
	}
	@Test
	public void testInsertShouldInsertAt13() throws Exception {
		
		initEmptyQuadTree(new Rectangle (0,0,160,160),5,2);
		
		Rectangle givenRectangle = new Rectangle(41,41,1,1);
		Mockito.when(givenEntity.getCollisionBox()).thenReturn(givenRectangle);
		Mockito.when(givenEntity.getId()).thenReturn(3L);
		
		quadtree.insert(givenEntity);
		
		String expected = "13X";
		String actual = quadtree.getPath(givenEntity);
		assertEquals(expected, actual);
	}
	@Test
	public void testInsertShouldInsertAt1_2() throws Exception {
		
		initEmptyQuadTree(new Rectangle (0,0,160,160),5,2);
		
		Rectangle givenRectangle = new Rectangle(39,41,1,1);
		Mockito.when(givenEntity.getCollisionBox()).thenReturn(givenRectangle);
		Mockito.when(givenEntity.getId()).thenReturn(3L);
		
		quadtree.insert(givenEntity);
		
		String expected = "1X";
		String actual = quadtree.getPath(givenEntity);
		assertEquals(expected, actual);
	}

	@Test
	public void shouldClearDefaultQuadtree() throws Exception {
		quadtree.clear();
		List<StaticGameEntity> actual = quadtree.retrieve(null, entityBotLeft.getCollisionBox());
		assertTrue(actual.isEmpty());
	}

	@Test
	public void shouldNotRetunAnyCollisions() throws Exception {

		initEmptyQuadTree(new Rectangle (0,0,160,160),5,1);

		quadtree.insert(entityTopRight);
		List<StaticGameEntity> actual = quadtree.retrieve(null, entityBotLeft.getCollisionBox());

		assertTrue(actual.isEmpty());
	}

	@Before
	public void initDefaultQuadtree() {
		Rectangle bounds = new Rectangle(0, 0, 100, 100);
		quadtree = new Quadtree(0, bounds,5,2);

		rectangleTopLeft = new Rectangle(0, 0, 10, 10);
		rectangleTopRight = new Rectangle(90, 0, 10, 10);
		rectangleBotLeft = new Rectangle(0, 90, 10, 10);
		rectangleBotRight = new Rectangle(90, 90, 10, 10);

		givenRectangle = new Rectangle(0, 0, 0, 0);

		entityBotLeft = Mockito.mock(StaticGameEntity.class);
		entityTopLeft = Mockito.mock(StaticGameEntity.class);
		entityTopRight = Mockito.mock(StaticGameEntity.class);
		entityBotRight = Mockito.mock(StaticGameEntity.class);

		givenEntity = Mockito.mock(StaticGameEntity.class);

		Mockito.when(entityTopRight.getCollisionBox()).thenReturn(
				rectangleTopRight);
		Mockito.when(entityTopRight.getId()).thenReturn(0L);
		

		Mockito.when(entityBotRight.getCollisionBox()).thenReturn(
				rectangleBotRight);
		Mockito.when(entityBotRight.getId()).thenReturn(1L);

		Mockito.when(entityBotLeft.getCollisionBox()).thenReturn(
				rectangleBotLeft);
		Mockito.when(entityBotLeft.getId()).thenReturn(2L);

		Mockito.when(entityTopLeft.getCollisionBox()).thenReturn(
				rectangleTopLeft);
		Mockito.when(entityTopLeft.getId()).thenReturn(3L);

		Mockito.when(givenEntity.getCollisionBox()).thenReturn(givenRectangle);

		quadtree.insert(entityTopLeft);
		quadtree.insert(entityTopRight);
		quadtree.insert(entityBotLeft);
		quadtree.insert(entityBotRight);
	}

	private void initEmptyQuadTree(Rectangle bounds, int maxObjects, int maxLevels) {
		this.quadtree = new Quadtree(0, bounds, maxObjects, maxLevels);
	}
}