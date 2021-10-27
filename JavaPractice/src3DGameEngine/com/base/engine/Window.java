package com.base.engine;

import java.awt.DisplayMode;
import org.lwjgl.LWJGLException;   //missing from library or error
import com.jogamp.newt.Display;

public class Window {
	public static void createWindow(int width, int height, String title) {
		Display.setTitle(title);
		try
		Display.setDisplayMode(new DisplayMode(width, height));
	} 
		catch (LWJGLException e) {
		
		e.printStackTrace();
	}
}
