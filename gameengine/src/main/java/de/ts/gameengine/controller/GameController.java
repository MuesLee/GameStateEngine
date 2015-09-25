package de.ts.gameengine.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import de.ts.gameengine.controls.KeyBindings;
import de.ts.gameengine.entities.Player;
import de.ts.gameengine.entities.movement.EightWayMovementHandler;
import de.ts.gameengine.gamestates.GameStateManager;
import de.ts.gameengine.gamestates.GameStateManagerFactory;
import de.ts.gameengine.view.GameFrame;
import de.ts.gameengine.view.GamePanel;
import de.ts.gameengine.view.utils.ViewUtils;

public class GameController implements Runnable {

	public static String GAME_TITLE = "EPIC NAME";

	public static int WIDTH = 660;
	public static int HEIGHT = 330;

	private static int UPS = 30;
	private static int FPS = 60;

	private static final boolean RENDER_TIME = true;

	private GameStateManagerFactory gameStateManagerFactory;
	private GameStateManager gameStateManager;
	
	

	private GameFrame frame;

	protected KeyBindings keyBindings;

	private ArrayList<Player> players;

	private AtomicBoolean running = new AtomicBoolean(false);
	private AtomicBoolean paused = new AtomicBoolean(false);

	protected GamePanel panel;
	
	private static long nextEntityID = 0l;

	public GameController(String gameName) {

		GameController.GAME_TITLE = gameName;
		
		Dimension screenSize = ViewUtils.getScreenSize();
		setWIDTH(screenSize.width/2);
		setHEIGHT(screenSize.height/2);
		setFrame(new GameFrame(GAME_TITLE));
		setPlayers(new ArrayList<Player>());
		panel = new GamePanel();
		getFrame().setLayout(new BorderLayout());
		getFrame().add(panel, BorderLayout.CENTER);
		getFrame().pack();

		configurePlayers();
	}
	
	protected void configurePlayers()
	{
		
		EightWayMovementHandler movementHandler = new EightWayMovementHandler();
		Player playerOne = new Player(this, movementHandler, 1);
		playerOne.setName("Player One");
		getPlayers().add(playerOne);
		keyBindings = new KeyBindings(panel);
		keyBindings.setPlayerOneInput(playerOne.getMoveActions());
		
	}

	private void startGameloop() {
		running.set(true);
		paused.set(false);
	}
	
	public void pauseGameloop ()
	{
		paused.set(true);
	}
	public void unpauseGameloop ()
	{
		paused.set(false);
	}

	public void stopGameloop() {
		running.set(false);
	}

	public void run() {

		startGameloop();
		
		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / UPS;
		final double timeF = 1000000000 / FPS;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();

		while (running.get()) {

			while (!paused.get()) {

				long currentTime = System.nanoTime();
				deltaU += (currentTime - initialTime) / timeU;
				deltaF += (currentTime - initialTime) / timeF;
				initialTime = currentTime;

				if (deltaU >= 1) {
					update();
					ticks++;
					deltaU--;
				}

				if (deltaF >= 1) {
					render();
					frames++;
					deltaF--;
				}

				if (System.currentTimeMillis() - timer > 1000) {
					if (RENDER_TIME) {
						System.out.println(String.format("UPS: %s, FPS: %s",
								ticks, frames));
					}
					frames = 0;
					ticks = 0;
					timer += 1000;
				}
			}
		}
	}

	private void update() {
		gameStateManager.update();
	}

	public static long getNextIDForEntity ()
	{
		return nextEntityID++;
	}
	
	private void render() {
		panel.repaint();
	}

	public void init() {
		gameStateManager = getGameStateManagerFactory().createGamestateManager();
		gameStateManager.setGameController(this);
		gameStateManager.loadState(0);
		panel.setGameStateManager(gameStateManager);
		getFrame().setVisible(true);
	}

	public KeyBindings getKeyBindings() {
		return keyBindings;
	}

	public void setKeyBindings(KeyBindings keyBindings) {
		this.keyBindings = keyBindings;
	}

	public GameFrame getFrame() {
		return frame;
	}

	public void setFrame(GameFrame frame) {
		this.frame = frame;
		this.frame.setLocationRelativeTo(null);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public GameStateManagerFactory getGameStateManagerFactory() {
		return gameStateManagerFactory;
	}

	public void setGameStateManagerFactory(GameStateManagerFactory gameStateManagerFactory) {
		this.gameStateManagerFactory = gameStateManagerFactory;
	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public static void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}

	public static int getUPS() {
		return UPS;
	}

	public static void setUPS(int uPS) {
		UPS = uPS;
	}

	public static int getFPS() {
		return FPS;
	}

	public static void setFPS(int fPS) {
		FPS = fPS;
	}
	
	
}
