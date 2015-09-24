package de.ts.gameengine.gamestates;

import de.ts.gameengine.gamestates.menu.DefaultMenuState;

public enum GameState implements GameStateOrder{

	MENU_STATE(0, DefaultMenuState.class);

	private int stateNumber;
	private Class<?> gameStateClass;

	private GameState(int stateNumber, Class<?> gameStateClass)
	{
		this.setStateNumber(stateNumber);
		this.gameStateClass = gameStateClass;
	}

	public int getStateNumber()
	{
		return stateNumber;
	}

	public void setStateNumber(int stateNumber)
	{
		this.stateNumber = stateNumber;
	}

	@Override
	public Class<?> getGameStateClass() {
		return gameStateClass;
	}

}
