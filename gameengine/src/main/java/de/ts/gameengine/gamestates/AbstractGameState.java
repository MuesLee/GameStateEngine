package de.ts.gameengine.gamestates;

import java.awt.Graphics2D;
import java.util.ArrayList;

import de.ts.gameengine.entities.Player;
import de.ts.gameengine.view.Background;

public abstract class AbstractGameState {

	protected Background background;
	
	protected ArrayList<Player> players;
	
	private GameStateManager manager;

	public AbstractGameState() {
	}

	public abstract void draw(Graphics2D g);

	public abstract void update(double deltaU);

	public abstract void init();

	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
	}


	public void drawBackground(Graphics2D g2d) {
		if(background!= null)
		{
			getBackground().draw(g2d);
		}
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public GameStateManager getManager() {
		return manager;
	}

	public void setManager(GameStateManager manager) {
		this.manager = manager;
	}

}
