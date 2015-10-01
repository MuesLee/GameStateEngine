package de.ts.gameengine.gamestates;

import java.awt.Graphics2D;

import de.ts.gameengine.controller.GameController;

public class GameStateManager {
	
	public static int REAL_FPS;
	protected AbstractGameState currentState;	
	private GameStateFactory gameStateFactory;
	
	private GameController gameController;

	public GameStateManager() {
	}

	public void init() {
		AbstractGameState currentGameState = getCurrentState();
		currentGameState.setPlayers(gameController.getPlayers());
		currentGameState.init();
		
		System.out.println("########## MANAGER INIT GAME STATE #############");
		System.out.println(currentGameState);
	}

	public void draw(Graphics2D g) {

		AbstractGameState tempCurrentState = getCurrentState();

		tempCurrentState.draw(g);

		g.translate(0, 0);
	}

	public void update(double deltaU) {
		getCurrentState().update(deltaU);
	}

	private AbstractGameState getCurrentState() {
		return currentState;
	}

	public void loadState(int number) {
		gameController.pauseGameloop();
		
		final AbstractGameState nextState = gameStateFactory.createGameState(number);
				currentState = nextState;
				currentState.setManager(this);
				init();
		gameController.unpauseGameloop();
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public GameStateFactory getGameStateFactory() {
		return gameStateFactory;
	}

	public void setGameStateFactory(GameStateFactory gameStateFactory) {
		this.gameStateFactory = gameStateFactory;
	}

}
