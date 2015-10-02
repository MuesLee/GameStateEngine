package de.ts.gameengine.entities;

import java.awt.image.BufferedImage;

public class Enviroment extends StaticGameEntity {
	
	private Tile tile;

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	@Override
	protected BufferedImage getdrawImage() {
		return tile.getImage();
	}
	
}
