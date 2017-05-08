package controller;

import java.util.ArrayList;
import java.util.List;

import constant.Lab;
import data.NewsData;
import data.NewsDataList;

public class SearchController {
	
	public static List<NewsData> searchNewsList = new ArrayList<NewsData>();
	
	//这个只写了根据报纸类型筛选
	public static List<NewsData> searchByKey(String newspaperTye){
		searchNewsList.clear();
		for(NewsData news:NewsDataList.newsDataList){
			if(news.getTagItsMap().get(Lab.NEWSPAPERTYPE) == newspaperTye)
				searchNewsList.add(news);
			//如果有其他筛选条件，在这里完成
			
		}
		return searchNewsList;
	}
	
	//page是第几页
	//pageNum是一页放几个
	public static List<NewsData> searchByPage(int page,int pageNum){
		searchNewsList.clear();
		int start = (page - 1) * pageNum;
		int end = page * pageNum;
		int index = start;
		while(index < end){
			searchNewsList.add(NewsDataList.newsDataList.get(index));
			index++;
		}
		return searchNewsList;
	}
	
	//该函数计算共有多少页
	//pageNum为一页放几个
	public String pageNumber(int pageNum){
		int newsSize = NewsDataList.newsDataList.size();
		return String.valueOf(newsSize/pageNum + 1);
	}
	
}
