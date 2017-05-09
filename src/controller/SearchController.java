package controller;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;


import constant.Lab;
import data.NewsData;
import data.NewsDataList;

public class SearchController {
	private static List<DataChangeListener> dataChangeListeners = new ArrayList<DataChangeListener>();
	public static List<NewsData> searchNewsList = new ArrayList<NewsData>();

	public static interface DataChangeListener {
		public void searchNewsChange();
	}

	public static void addDataChangeListener(DataChangeListener dataChangeListener) {
		dataChangeListeners.add(dataChangeListener);
	}

	public static void notifysAll() {
		for (DataChangeListener dataChangeListener : dataChangeListeners) {
			dataChangeListener.searchNewsChange();
		}
	}


	public static List<NewsData> getSearchNewsList() {
		return searchNewsList;
	}

	public static void initSearchNewsList(){
		searchNewsList.clear();
		for(NewsData newsData: NewsDataList.newsDataList){
			if(!newsData.getIsDeleted()){
				searchNewsList.add(newsData);
			}
		}
	}
	
	public static void refreshSearchNewsList(){
		initSearchNewsList();
		notifysAll();
	}

}
