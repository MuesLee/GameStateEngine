package de.ts.gameengine.gamestates.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import de.ts.gameengine.controller.GameController;
import de.ts.gameengine.controls.AnalogControlAction;
import de.ts.gameengine.gamestates.AbstractGameState;
import de.ts.gameengine.view.Background;

public class DefaultMenuState extends AbstractGameState {

	private Font titleFont;
	private Font font;

	private Color titleColor;
	private Color color;

	private AnalogControlAction playerOneMoveAction;
	
	private List<MenuItem> menuItems;

	private int selectedMenuItem = 0;

	public DefaultMenuState() {
		super();
		setMenuItems(new ArrayList<MenuItem>());
		getMenuItems().add(new MenuItem("Start"));
		getMenuItems().add(new MenuItem("Exit"));
		titleFont = new Font("Showcard Gothic", Font.BOLD, 64);
		font = new Font("Verdana", Font.PLAIN, 18);

		titleColor = Color.DARK_GRAY;
		color = Color.LIGHT_GRAY;
	}

	@Override
	public void update(double deltaU) {
		super.getBackground().update();
		
		if(playerOneMoveAction.isUp())
		{
			selectedMenuItem--;
			if (selectedMenuItem < 0) {
				selectedMenuItem = getMenuItems().size() - 1;
			}
		}
		else if (playerOneMoveAction.isDown()) {
			selectedMenuItem++;

			if (selectedMenuItem >= getMenuItems().size()) {
				selectedMenuItem = 0;
			}
		}
		else if(playerOneMoveAction.isStart())
		{
			handleMenuItemChoice(menuItems.get(selectedMenuItem));
		}
		playerOneMoveAction.resetMoveActions();
		playerOneMoveAction.resetSpecialActions();
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

		for (int i = 0; i < getMenuItems().size(); i++) {
			g.setColor(color);

			if (i == selectedMenuItem) {
				g.setColor(Color.red);
			}

			g.drawString(menuItems.get(i).getText(), 300, 140 + i * 20);
		}
	}

	@Override
	public void init() {
		final Background background = new Background("Tiles/Background/menuBackground.jpg", 1);
		background.setVector(5, 0);
		setBackground(background);
		playerOneMoveAction = players.get(0).getMoveActions();
	}

	protected void handleMenuItemChoice(MenuItem menuItem)
	{
		
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
}
