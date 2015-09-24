package de.ts.gameengine.collision;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import de.ts.gameengine.entities.StaticGameEntity;

public class Quadtree {

	private static final int TOP_LEFT_QUADRANT = 1;
	private static final int TOP_RIGHT_QUADRANT = 0;
	private static final int BOTTOM_LEFT_QUADRANT = 2;
	private static final int BOTTOM_RIGHT_QUADRANT = 3;

	private int maxObjects = 5;
	private int maxLevels = 3;

	private int level;
	private List<StaticGameEntity> entities;
	private Rectangle bounds;
	private Quadtree[] nodes;

	public Quadtree(int startLevel, Rectangle bounds, int maxObjectsPerLevel,
			int maxLevels) {
		this.level = startLevel;

		this.maxObjects = maxObjectsPerLevel;
		this.maxLevels = maxLevels;
		entities = new ArrayList<StaticGameEntity>(maxObjects);
		this.bounds = bounds;
		nodes = new Quadtree[4];

		if (level < maxLevels) {
			split();
		}
	}

	public void clear() {
		entities.clear();

		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] != null) {
				nodes[i].clear();
				nodes[i] = null;
			}
		}
	}

	private void split() {
		int halvedWidth = (int) (bounds.getWidth() / 2);
		int halvedHeight = (int) (bounds.getHeight() / 2);
		int x = (int) bounds.getX();
		int y = (int) bounds.getY();

		nodes[TOP_RIGHT_QUADRANT] = new Quadtree(level + 1, new Rectangle(x
				+ halvedWidth, y, halvedWidth, halvedHeight), maxObjects,
				maxLevels);
		nodes[TOP_LEFT_QUADRANT] = new Quadtree(level + 1, new Rectangle(x, y,
				halvedWidth, halvedHeight), maxObjects, maxLevels);
		nodes[BOTTOM_LEFT_QUADRANT] = new Quadtree(level + 1, new Rectangle(x,
				y + halvedHeight, halvedWidth, halvedHeight), maxObjects,
				maxLevels);
		nodes[BOTTOM_RIGHT_QUADRANT] = new Quadtree(level + 1, new Rectangle(x
				+ halvedWidth, y + halvedHeight, halvedWidth, halvedHeight),
				maxObjects, maxLevels);

		// Iterator<StaticGameEntity> iterator = entities.iterator();
		// while (iterator.hasNext()) {
		// StaticGameEntity entity = iterator.next();
		// int index = getIndex(entity);
		// if (index != -1) {
		// nodes[index].insert(entity);
		// iterator.remove();
		// }
		// }
	}

	/**
	 * Gibt den Index des Quadranten zurück, in den das Rechteck passen würde.
	 * Returns -1 wenn es nirgends vollständig hereinpasst.
	 * 
	 * @param rectangle
	 *            zu überprüfendes Rechteck
	 * @return Index des Quadranten. -1 wenn es nicht vollständig in einen
	 *         Quadranten passt.
	 */
	private int getIndex(StaticGameEntity entitiy) {
		int index = -1;
		double midpointX = bounds.getX() + (bounds.getWidth() / 2);
		double midpointY = bounds.getY() + (bounds.getHeight() / 2);

		Rectangle rectangle = entitiy.getCollisionBox();

		// Rechteck passt komplett in die obere Hälfte des Quadtrees
		boolean topQuadrants = (rectangle.getY() < midpointY && rectangle.getY()
				+ rectangle.getHeight() <= midpointY);
		// Rechteck passt komplett in die untere Hälfte des Quadtrees
		boolean bottomQuadrants = (rectangle.getY() >= midpointY && rectangle
				.getY() + rectangle.getHeight() >= midpointY);

		// Rechteck passt komplett in die linke Hälfte des Quadtrees
		if (rectangle.getX() < midpointX
				&& rectangle.getX() + rectangle.getWidth() < midpointX) {
			if (topQuadrants) {
				index = TOP_LEFT_QUADRANT;
			} else if (bottomQuadrants) {
				index = BOTTOM_LEFT_QUADRANT;
			}
		}
		// Rechteck passt komplett in die rechte Hälfte des Quadtrees
		else if (rectangle.getX() >= midpointX) {
			if (topQuadrants) {
				index = TOP_RIGHT_QUADRANT;
			} else if (bottomQuadrants) {
				index = BOTTOM_RIGHT_QUADRANT;
			}
		}

		return index;
	}

	/**
	 * Fügt eine Entity in den Quadtree ein.
	 * 
	 * @param rectangle
	 */
	public void insert(StaticGameEntity entity) {

		int index = getIndex(entity);

		if (index != -1 && nodes[index] != null) {
			nodes[index].insert(entity);

		} else {
			entities.add(entity);
		}
	}

	/**
	 * Gibt alle Rechtecke zurück, die mit dem übergebenen Rechteck kollidieren
	 * könnten.
	 * 
	 * @param returnObjects
	 *            Ergebnisliste für rekursiven Aufruf. Darf initial null sein.
	 * @param entity
	 *            zu überprüfendes Rechteck
	 * @return Liste mit Rechtecken, die mit dem übergebenen kollidieren könnten
	 */
	public List<StaticGameEntity> retrieve(
			List<StaticGameEntity> returnObjects, StaticGameEntity entity) {

		if (returnObjects == null) {
			returnObjects = new ArrayList<>(maxObjects);
		}

		int index = getIndex(entity);

		if (index == -1) {

			returnObjects.addAll(entities);

			for (int i = 0; i < nodes.length; i++) {
				if (nodes[i] != null) {
					nodes[i].retrieve(returnObjects, entity);
				}
			}

		} else {
			if (nodes[index] != null) {
				returnObjects.addAll(entities);
				nodes[index].retrieve(returnObjects, entity);
			} else {
				returnObjects.addAll(entities);
			}
		}
		return returnObjects;
	}

	public String getPath(StaticGameEntity givenEntity) {

		String path = "";

		path = getPathRecursive(givenEntity, path);
		return path;
	}

	protected String getPathRecursive(StaticGameEntity givenEntity, String path) {

		if (entities.contains(givenEntity)) {
			return path += "X";

		} else {

			for (int i = 0; i < nodes.length; i++) {
				Quadtree quadtree = nodes[i];
				if (quadtree != null) {

					String result = quadtree
							.getPathRecursive(givenEntity, path);
					if (result.contains("X")) {
						path = i + path +result;
						break;
					}

				}
			}
		}

		return path;
	}

}
