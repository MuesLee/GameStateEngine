package de.ts.gameengine.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.ts.gameengine.controller.GameController;
import de.ts.gameengine.view.Background;

public class DefaultMenuState extends AbstractGameState implements KeyListener {

	private Font titleFont;
	private Font font;

	private Color titleColor;
	private Color color;

	private String[] menuItems = { "Start", "Exit" };

	private int selectedMenuItem = 0;

	public DefaultMenuState() {
		super();
		titleFont = new Font("Showcard Gothic", Font.BOLD, 64);
		font = new Font("Verdana", Font.PLAIN, 18);

		titleColor = Color.DARK_GRAY;
		color = Color.LIGHT_GRAY;
	}

	@Override
	public void update() {
		super.getBackground().update();
	}

	@Override
	public void draw(Graphics2D g) {
		drawBackground(g);
		drawTitle(g);
		drawMenu(g);

	}

	private void drawTitle(Graphics2D g) {
		g.setFont(titleFont);
		g.setColor(titleColor);
		g.drawString(GameController.GAME_TITLE, 155, 115);
	}

	private void drawMenu(Graphics2D g) {
		g.setFont(font);

		for (int i = 0; i < menuItems.length; i++) {
			g.setColor(color);

			if (i == selectedMenuItem) {
				g.setColor(Color.red);
			}

			g.drawString(menuItems[i], 300, 140 + i * 20);
		}
	}

	@Override
	public void init() {
		final Background background = new Background("Tiles/Background/menuBackground.jpg", 1);
		background.setVector(5, 0);
		setBackground(background);

	}

	@Override
	public void keyPressed(KeyEvent key) {

		if (key.getKeyCode() == KeyEvent.VK_UP) {
			selectedMenuItem--;
			if (selectedMenuItem < 0) {
				selectedMenuItem = menuItems.length - 1;
			}

		} else if (key.getKeyCode() == KeyEvent.VK_DOWN) {

			selectedMenuItem++;

			if (selectedMenuItem >= menuItems.length) {
				selectedMenuItem = 0;
			}
		} else if (key.getKeyCode() == KeyEvent.VK_ENTER) {
			switch (selectedMenuItem) {
			case 0:
				break;
			case 1:
				System.exit(0);
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {

	}

	@Override
	public void keyTyped(KeyEvent key) {

	}
}
