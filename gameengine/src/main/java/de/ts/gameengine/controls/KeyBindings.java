package de.ts.gameengine.controls;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class KeyBindings {

	private AnalogControlAction playerOneInput = new AnalogControlAction();
	private AnalogControlAction playerTwoInput = new AnalogControlAction();

	@SuppressWarnings("serial")
	public KeyBindings(JComponent gp) {
		// PLAYER TWO

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "Enter pressed");
		gp.getActionMap().put("Enter pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setStart(true);
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "Enter released");
		gp.getActionMap().put("Enter released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setStart(false);
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "W pressed");
		gp.getActionMap().put("W pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setUp(true);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "W released");
		gp.getActionMap().put("W released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setUp(false);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "S pressed");
		gp.getActionMap().put("S pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setDown(true);
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "S released");
		gp.getActionMap().put("S released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setDown(false);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "A pressed");
		gp.getActionMap().put("A pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setLeft(true);;
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "A released");
		gp.getActionMap().put("A released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setLeft(false);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "D pressed");
		gp.getActionMap().put("D pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setRight(true);
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "D released");
		gp.getActionMap().put("D released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerTwoInput().setRight(false);
			}
		});

		// PLAYER ONE

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false),
				"down pressed");
		gp.getActionMap().put("down pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setDown(true);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true),
				"down released");
		gp.getActionMap().put("down released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setDown(false);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up pressed");
		gp.getActionMap().put("up pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setUp(true);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "up released");
		gp.getActionMap().put("up released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setUp(false);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false),
				"left pressed");
		gp.getActionMap().put("left pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setLeft(true);;
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true),
				"left released");
		gp.getActionMap().put("left released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setLeft(false);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false),
				"right pressed");
		gp.getActionMap().put("right pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setRight(true);
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true),
				"right released");
		gp.getActionMap().put("right released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setRight((false));
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true),
				"Spacebar released");
		gp.getActionMap().put("Spacebar released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setJump((false));
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false),
				"Spacebar pressed");
		gp.getActionMap().put("Spacebar pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getPlayerOneInput().setJump((true));
			}
		});
	}

	public AnalogControlAction getPlayerOneInput() {
		return playerOneInput;
	}

	public void setPlayerOneInput(AnalogControlAction playerOneInput) {
		this.playerOneInput = playerOneInput;
	}

	public AnalogControlAction getPlayerTwoInput() {
		return playerTwoInput;
	}

	public void setPlayerTwoInput(AnalogControlAction playerTwoInput) {
		this.playerTwoInput = playerTwoInput;
	}
}