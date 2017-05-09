package controller;

import data.NewsData;
import data.NewsDataList;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DeleteController {

	private static List<DataChangeListener> dataChangeListeners = new ArrayList<DataChangeListener>();
	public static List<NewsData> recycleNewsList = new ArrayList<NewsData>();

	public static interface DataChangeListener {
		public void recycleNewsChange();
	}

	public static void addDataChangeListener(DataChangeListener dataChangeListener) {
		dataChangeListeners.add(dataChangeListener);
	}

	private static void notifysAll() {
		for (DataChangeListener dataChangeListener : dataChangeListeners) {
			dataChangeListener.recycleNewsChange();
		}
	}

	public static void addRecycleNews(NewsData news) {
		news.setIsDeleted("true");
		Logger logger = Logger.getLogger("zhenxiongwu");
		logger.info(news.getTitle());
		recycleNewsList.add(news);

		dataChange();
	}

	public static void recoverRecycleNews(NewsData news) {
		news.setIsDeleted("false");
		recycleNewsList.remove(news);
		dataChange();
	}

	public static List<NewsData> getRecycleNewsList() {
		return recycleNewsList;
	}

	public static void initRecycleList(){
		for(NewsData newsData: NewsDataList.newsDataList){
			if(newsData.getIsDeleted()){
				recycleNewsList.add(newsData);
			}
		}
	}
	
	private static void dataChange(){
		notifysAll();
		SearchController.refreshSearchNewsList();
	}
}
