package main;


import controller.SearchController;
import data.NewsDataList;
import data.NewsDataPersistence;
import gui.window.HomeWindow;
import gui.window.Window;

public class Launcher {
	
	public static void main(String[] args){
		try {
			
			NewsDataList.initNewsDataList();
			SearchController.initSearchNewsList();
			Window window = new HomeWindow();
			window.open();
			NewsDataPersistence.createXml(NewsDataList.newsDataList,"my.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
