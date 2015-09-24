package de.ts.gameengine.view;

import javax.swing.JFrame;

public class GameFrame extends JFrame
{

	private static final long serialVersionUID = 5161037030211039961L;

	public GameFrame(String title)
	{
		super(title);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}



}
