package de.ts.gameengine.entities;

import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage[] frames;
	private int indexOfcurrentFrame;
	private long framesBetweenNextAnimation;

	private int timesPlayed;
	private int framesSinceLastAnimation;
	private int maxIndexOfFrames;

	public Animation() {
		timesPlayed = 0;
		setFramesBetweenNextAnimation(3);
	}

	private void nextFrame() {
		if (indexOfcurrentFrame == maxIndexOfFrames) {
			indexOfcurrentFrame = 0;
		} else {
			indexOfcurrentFrame = Math.max(indexOfcurrentFrame + 1,
					maxIndexOfFrames);
		}
	}

	public void update() {

		if (getFramesBetweenNextAnimation() == -1)
			return;

		framesSinceLastAnimation++;

		if (framesSinceLastAnimation == getFramesBetweenNextAnimation()) {
			framesSinceLastAnimation = 0;
			nextFrame();
		}

	}

	public BufferedImage[] getFrames() {
		return frames;
	}

	public boolean hasPlayed(int i) {
		return timesPlayed == i;
	}

	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		indexOfcurrentFrame = 0;
		framesSinceLastAnimation = 0;
		timesPlayed = 0;
		setFramesBetweenNextAnimation(2);
		maxIndexOfFrames = frames.length - 1;
	}

	public BufferedImage getImage() {
		return frames[indexOfcurrentFrame];
	}

	public int getCurrentFrame() {
		return indexOfcurrentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.indexOfcurrentFrame = currentFrame;
	}

	public long getFramesBetweenNextAnimation() {
		return framesBetweenNextAnimation;
	}

	public void setFramesBetweenNextAnimation(long framesBetweenNextAnimation) {
		this.framesBetweenNextAnimation = framesBetweenNextAnimation;
	}

}
