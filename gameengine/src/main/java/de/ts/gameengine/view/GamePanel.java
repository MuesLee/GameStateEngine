package de.ts.gameengine.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import de.ts.gameengine.controller.GameController;
import de.ts.gameengine.gamestates.GameStateManager;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = -2414643643356712734L;
	private Image backBuffer;
	private Graphics bBG;

	private int width = GameController.WIDTH;
	private int height = GameController.HEIGHT;

	private GameStateManager gameStateManager;

	public GamePanel() {
		super();
		setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.RED);
		this.setVisible(true);
		setIgnoreRepaint(true);
	}

	@Override
	protected void paintComponent(Graphics g) {

		if (backBuffer == null) {
			backBuffer = createImage(getWidth(), getHeight());
			backBuffer.setAccelerationPriority(1f);
		}
		bBG = backBuffer.getGraphics();

		paintGameState(bBG);

		g.drawImage(backBuffer, 0, 0, this);
	}

	private void paintGameState(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		// clear screen
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		BufferedImage image = new BufferedImage(GameController.WIDTH,
				GameController.HEIGHT, BufferedImage.TYPE_INT_RGB);
		image.setAccelerationPriority(0.9f);

		Graphics2D paintedArea = (Graphics2D) image.getGraphics();
		gameStateManager.draw(paintedArea);
		paintedArea.dispose();
		g2d.drawImage(image, 0, 0, GameController.WIDTH, GameController.HEIGHT,
				this);
	}

	public GameStateManager getGameStateManager() {
		return gameStateManager;
	}

	public void setGameStateManager(GameStateManager gameStateManager) {
		this.gameStateManager = gameStateManager;
	}
}