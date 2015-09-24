package de.ts.gameengine.controls;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import de.ts.gameengine.entities.MoveActions;

public class KeyBindings {

	private MoveActions playerOneInput = new MoveActions();
	private MoveActions playerTwoInput = new MoveActions();

	@SuppressWarnings("serial")
	public KeyBindings(JComponent gp) {
		// PLAYER TWO

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "W pressed");
		gp.getActionMap().put("W pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setJumping(true);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "W released");
		gp.getActionMap().put("W released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setJumping(false);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "S pressed");
		gp.getActionMap().put("S pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setCrouching(true);
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "S released");
		gp.getActionMap().put("S released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setCrouching(false);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "A pressed");
		gp.getActionMap().put("A pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setMovingLeft(true);
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "A released");
		gp.getActionMap().put("A released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setMovingLeft(false);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "D pressed");
		gp.getActionMap().put("D pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setMovingRight(true);
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "D released");
		gp.getActionMap().put("D released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setMovingRight(false);
			}
		});

		// PLAYER ONE

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false),
				"down pressed");
		gp.getActionMap().put("down pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setCrouching(true);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true),
				"down released");
		gp.getActionMap().put("down released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setCrouching(false);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up pressed");
		gp.getActionMap().put("up pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setJumping(true);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "up released");
		gp.getActionMap().put("up released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setJumping(false);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false),
				"left pressed");
		gp.getActionMap().put("left pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setMovingLeft(true);
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true),
				"left released");
		gp.getActionMap().put("left released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setMovingLeft(false);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false),
				"right pressed");
		gp.getActionMap().put("right pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setMovingRight(true);
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true),
				"right released");
		gp.getActionMap().put("right released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setMovingRight(false);
			}
		});
	}

	public MoveActions getPlayerOneInput() {
		return playerOneInput;
	}

	public void setPlayerOneInput(MoveActions playerOneInput) {
		this.playerOneInput = playerOneInput;
	}

	public MoveActions getPlayerTwoInput() {
		return playerTwoInput;
	}

	public void setPlayerTwoInput(MoveActions playerTwoInput) {
		this.playerTwoInput = playerTwoInput;
	}
}