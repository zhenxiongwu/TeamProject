package data;

import java.util.List;

public class deleteNewsList {
	public static List<NewsData> deleteNewsList;

	public void addDeletedNews(NewsData news){
		deleteNewsList.add(news);
	}
	
	public void recoverDeletedNews(NewsData news){
		deleteNewsList.remove(news);
	}

	public List<NewsData> getDeleteNewsList(){
		return deleteNewsList;
	}
}
