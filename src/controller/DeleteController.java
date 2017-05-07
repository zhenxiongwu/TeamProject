package controller;

import data.NewsData;

import java.util.ArrayList;
import java.util.List;

public class DeleteController {
	
	public static List<NewsData> recycleNewsList = new ArrayList<NewsData>();
	
	public static void addRecycleNews(NewsData news){
		recycleNewsList.add(news);
	}
	
	public static void recoverRecycleNews(NewsData news){
		recycleNewsList.remove(news);
	}

	public static List<NewsData> getRecycleNewsList(){
		return recycleNewsList;
	}

}
