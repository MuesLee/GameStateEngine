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
		
		System.out.println(currentGameState);
		System.out.println("########## MANAGER INIT GAME STATE #############");
	}

	public void draw(Graphics2D g) {

		AbstractGameState tempCurrentState = getCurrentState();

		tempCurrentState.draw(g);

		g.translate(0, 0);
	}

	public void update() {
		getCurrentState().update();
	}

	private AbstractGameState getCurrentState() {
		return currentState;
	}

	public void loadState(int number) {
		gameController.pauseGameloop();
		
		GameState stateByNumber = GameState.getStateByNumber(number);
		final AbstractGameState nextState = gameStateFactory.createGameState(
				stateByNumber);
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
