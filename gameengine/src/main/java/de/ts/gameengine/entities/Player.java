package de.ts.gameengine.entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import de.ts.gameengine.controller.GameController;

public class Player extends DynamicGameEntity {

	private List<BufferedImage[]> sprites;
	private static final int[] NUMFRAMES = { 3, 3 };
	private static final int[] FRAMEWIDTHS = { 64, 64, 64 };
	private static final int[] FRAMEHEIGHTS = { 75, 75, 75 };
	private static final int[] SPRITETIMINGS = { 3, 3 };

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

		BufferedImage spritesheet;
		try {
			spritesheet = ImageIO.read(getClass().getResourceAsStream(
					"/Sprites/Player/nerdyguy_192_150.png"));

			int count = 0;
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < NUMFRAMES.length; i++) {
				BufferedImage[] bi = new BufferedImage[NUMFRAMES[i]];
				for (int j = 0; j < NUMFRAMES[i]; j++) {
					bi[j] = spritesheet.getSubimage(j * FRAMEWIDTHS[i], count,
							FRAMEWIDTHS[i], FRAMEHEIGHTS[i]);
				}
				sprites.add(bi);
				count += FRAMEHEIGHTS[i];
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		setAnimation(ANIMATION_MOVE_RIGHT);

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
}
