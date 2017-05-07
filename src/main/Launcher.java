package main;

import gui.HomeWindow;
import gui.Window;

public class Launcher {
	
	public static void main(String[] args){
		try {
			Window window = new HomeWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
