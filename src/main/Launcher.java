package main;


import controller.SearchController;
import data.NewsDataList;
import gui.HomeWindow;
import gui.Window;

public class Launcher {
	
	public static void main(String[] args){
		try {
			
			NewsDataList.initNewsDataList();
			SearchController.initSearchNewsList();
			Window window = new HomeWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
