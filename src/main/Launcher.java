package main;

import java.util.logging.Logger;

import data.NewsDataList;
import gui.HomeWindow;
import gui.Window;

public class Launcher {
	public static NewsDataList testList; 
	
	public static void main(String[] args){
		try {
			initialData();
			Logger logger = Logger.getLogger("zhenxiongwu");
			logger.info(""+testList.getSize());
			Window window = new HomeWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void initialData(){
		testList = new NewsDataList();
		testList.setNewsDataList();
	}
	
}
