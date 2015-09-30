package de.ts.gameengine.controls;

import java.util.concurrent.atomic.AtomicBoolean;

public class AnalogControlAction {

	private AtomicBoolean left;
	private AtomicBoolean right;
	private AtomicBoolean up;
	private AtomicBoolean down;
	
	
	private AtomicBoolean start;
	private AtomicBoolean jump;
	
	public AnalogControlAction() {
	this.left = new AtomicBoolean();
	this.right = new AtomicBoolean();
	this.up = new AtomicBoolean();
	this.down = new AtomicBoolean();
	this.jump = new AtomicBoolean();
	this.start = new AtomicBoolean();
	}
	
	public boolean isJump() {
		return jump.get();
	}
	public void setJump(boolean jump) {
		this.jump.set(jump);
	}

	public boolean isLeft() {
		return left.get();
	}
	public void setLeft(boolean left) {
		this.left.set(left);
	}
	public boolean isRight() {
		return right.get();
	}
	public void setRight(boolean right) {
		this.right.set(right);
	}
	public boolean isUp() {
		return up.get();
	}
	public void setUp(boolean up) {
		this.up.set( up);
	}
	public boolean isDown() {
		return down.get();
	}
	public void setDown(boolean down) {
		this.down.set(down);
	}
	public boolean isStart() {
		return start.get();
	}
	public void setStart(boolean start) {
		this.start.set(start);
	}
	
	public void resetMoveActions()
	{
		setUp(false);
		setDown(false);
		setLeft(false);
		setRight(false);
	}
	
	public void resetSpecialActions()
	{
		setStart(false);
	}
	
}
