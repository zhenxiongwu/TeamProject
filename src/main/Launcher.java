package main;

import data.NewsDataList;
import gui.HomeWindow;
import gui.Window;

public class Launcher {
	public static NewsDataList testList; 
	
	public static void main(String[] args){
		try {
			initialData();
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
