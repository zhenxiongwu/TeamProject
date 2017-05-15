package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.swt.internal.win32.TCHITTESTINFO;

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

	public static void initSearchNewsList() {
		searchNewsList.clear();
		for (NewsData newsData : NewsDataList.newsDataList) {
			if (!newsData.getIsDeleted()) {
				searchNewsList.add(newsData);
			}
		}
	}
	
	private static int newspaperTypeIndex = 0;
	private static int newsTypeIndex = 0;
	private static int reportThemeIndex = 0;
	private static int showTypeIndex =0;
	
	public static void setSearchTag(int newspaperTypeIndex, int newsTypeIndex, 
			int reportThemeIndex, int showTypeIndex){
		SearchController.newspaperTypeIndex = newspaperTypeIndex;
		SearchController.newsTypeIndex = newsTypeIndex;
		SearchController.reportThemeIndex = reportThemeIndex;
		SearchController.showTypeIndex = showTypeIndex;
	}

	public static void searchByTag(int newspaperTypeIndex, int newsTypeIndex,
			int reportThemeIndex, int showTypeIndex) {
		searchNewsList.clear();
		String newspaperType = Lab.newspaperType[newspaperTypeIndex];
		String newsType = Lab.newsType[newsTypeIndex];
		String reportTheme = Lab.reportTheme[reportThemeIndex];
		String showType = Lab.showType[showTypeIndex];
		for (NewsData newsData : NewsDataList.newsDataList) {
			if (!newsData.getIsDeleted()) {

				Map<String, String> temp = newsData.getTagItsMap();
				Logger logger = Logger.getLogger("zhenxiongwu");
				logger.info(temp.get(Lab.NEWSPAPERTYPE));
				logger.info(newspaperType);
				
				if(temp.get(Lab.NEWSTYPE) == null){
					temp.put(Lab.NEWSTYPE,"");
				}
				if(temp.get(Lab.NEWSPAPERTYPE)==null){
					temp.put(Lab.NEWSPAPERTYPE,"");
				}
				if(temp.get(Lab.REPORTTHEME)==null){
					temp.put(Lab.REPORTTHEME,"");
				}
				if(temp.get(Lab.SHOWTYPE)==null){
					temp.put(Lab.SHOWTYPE,"");
				}
				if (temp.get(Lab.NEWSPAPERTYPE).equals(newspaperType) &&
						temp.get(Lab.NEWSTYPE).equals(newsType) && 
						temp.get(Lab.REPORTTHEME).equals(reportTheme) &&
						temp.get(Lab.SHOWTYPE).equals(showType)) {
					searchNewsList.add(newsData);
				}
			}
		}
	}

	public static void refreshSearchNewsList() {
		searchByTag(newspaperTypeIndex, newsTypeIndex, reportThemeIndex, showTypeIndex);
		notifysAll();
	}

}
