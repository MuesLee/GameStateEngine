package de.ts.gameengine.entities;

public class ControlAction {

	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private boolean start;
	private boolean jump;
	
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	public void reset() {
		left = false;
		right = false;
		up = false;
		down = false;
		start = false;
	}
	public boolean isJump() {
		return jump;
	}
	
	public void setJump(boolean jump) {
	this.jump = jump; 
	}

	
	
}
