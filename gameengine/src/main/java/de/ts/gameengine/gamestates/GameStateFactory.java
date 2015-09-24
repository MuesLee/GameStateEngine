package de.ts.gameengine.gamestates;

public interface GameStateFactory {

	public AbstractGameState createGameState(GameState state);
}
