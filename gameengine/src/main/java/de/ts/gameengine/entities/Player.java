package de.ts.gameengine.entities;

import java.awt.image.BufferedImage;
import java.util.List;

import de.ts.gameengine.controller.GameController;
import de.ts.gameengine.entities.movement.GameInputHandler;

public class Player extends DynamicGameEntity {

	protected List<BufferedImage[]> sprites;
	protected static int[] NUMFRAMES = { 3, 3 };
	protected static int[] FRAMEWIDTHS = { 64, 64, 64 };
	protected static int[] FRAMEHEIGHTS = { 75, 75, 75 };
	protected static int[] SPRITETIMINGS = { 3, 3 };

	protected static final int ANIMATION_MOVE_RIGHT = 0;
//	private static final int ANIMATION_MOVE_LEFT = 1;

	protected GameController gameController;
	protected int playerID;

	public Player(GameController gameController, GameInputHandler gameInputHandler,int playerID) {
		super(gameInputHandler);
		this.setPlayerID(playerID);
		setMoveSpeedIncreaseRate(5);
		setMoveSpeedMax(15);
		setMoveSpeedSlowDownRate(7.5);
		setJumpSpeedIncreaseRate(5);
		setJumpSpeedMax(15);
		this.setGameController(gameController);
		
		fillSprites();


	}

	protected void fillSprites ()
	{

	}
	
	@Override
	public void update() {
		super.update();
	}
	
	protected void setAnimation(int i) {
		setCurrentAction(i);
		animation.setFrames(sprites.get(getCurrentAction()));
		animation.setFramesBetweenNextAnimation(SPRITETIMINGS[getCurrentAction()]);
		width = FRAMEWIDTHS[getCurrentAction()];
		height = FRAMEHEIGHTS[getCurrentAction()];
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public static int[] getNUMFRAMES() {
		return NUMFRAMES;
	}

	public static void setNUMFRAMES(int[] nUMFRAMES) {
		NUMFRAMES = nUMFRAMES;
	}

	public static int[] getFRAMEWIDTHS() {
		return FRAMEWIDTHS;
	}

	public static void setFRAMEWIDTHS(int[] fRAMEWIDTHS) {
		FRAMEWIDTHS = fRAMEWIDTHS;
	}

	public static int[] getFRAMEHEIGHTS() {
		return FRAMEHEIGHTS;
	}

	public static void setFRAMEHEIGHTS(int[] fRAMEHEIGHTS) {
		FRAMEHEIGHTS = fRAMEHEIGHTS;
	}

	public static int[] getSPRITETIMINGS() {
		return SPRITETIMINGS;
	}

	public static void setSPRITETIMINGS(int[] sPRITETIMINGS) {
		SPRITETIMINGS = sPRITETIMINGS;
	}
	
	
}
