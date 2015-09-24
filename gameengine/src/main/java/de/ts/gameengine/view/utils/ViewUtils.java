package de.ts.gameengine.view.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ViewUtils {

	
	public static Dimension getScreenSize()
	{
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		
		return new Dimension(width,height);
	}
	
}
