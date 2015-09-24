package de.ts.gameengine.view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import de.ts.gameengine.controller.GameController;

public class Background {
	private Image image;

	private int x = 0;
	private int y = 0;

	private int dx = 0;
	private int dy = 0;

	private double moveScale = 1;

	public Background(String path, double moveScale) {
		try {
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream(path));
		} catch (Exception e) {
			image = new BufferedImage(GameController.WIDTH,GameController.HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		}

		this.moveScale = moveScale;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setVector(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public void update() {

		x = (int) (x + dx * moveScale);
		y = (int) (y + dy * moveScale);

		if (x == GameController.WIDTH) {
			x = 0;
		} else if (y == GameController.HEIGHT) {
			y = 0;
		}
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, x, y, GameController.WIDTH, GameController.HEIGHT,
				null);

		if (x < 0) {
			g2d.drawImage(image, GameController.WIDTH + x, y,
					GameController.WIDTH, GameController.HEIGHT, null);
		} else if (x > 0) {
			g2d.drawImage(image, x - GameController.WIDTH, y,
					GameController.WIDTH, GameController.HEIGHT, null);
		}
	}
}
