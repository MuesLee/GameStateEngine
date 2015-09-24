package de.ts.gameengine.entities;

import java.awt.image.BufferedImage;
import java.util.List;

import de.ts.gameengine.controller.GameController;

public class Player extends DynamicGameEntity {

	private List<BufferedImage[]> sprites;
	private static int[] NUMFRAMES = { 3, 3 };
	private static int[] FRAMEWIDTHS = { 64, 64, 64 };
	private static int[] FRAMEHEIGHTS = { 75, 75, 75 };
	private static int[] SPRITETIMINGS = { 3, 3 };

	private static final int ANIMATION_MOVE_RIGHT = 0;
//	private static final int ANIMATION_MOVE_LEFT = 1;

	private GameController gameController;
	private int playerID;

	public Player(GameController gameController, int playerID) {
		super();
		this.setPlayerID(playerID);
		setMoveSpeedIncreaseRate(5);
		setMoveSpeedMax(15);
		setJumpSpeedIncrease(2);
		setJumpSpeedMax(7);
		setMoveSpeedSlowDownRate(7.5);
		setFallSpeedIncreaseRate(1);
		setFallSpeedMax(5);
		this.setGameController(gameController);


		setAnimation(ANIMATION_MOVE_RIGHT);

	}

	protected void fillSprites ()
	{

	}
	
	@Override
	public void update() {
		super.update();
	}
	
	private void setAnimation(int i) {
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
