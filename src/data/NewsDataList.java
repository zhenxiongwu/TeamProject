package data;
import java.util.ArrayList;
import java.util.List;

import controller.DeleteController;

public class NewsDataList {
	
	public static List<NewsData> newsDataList = new ArrayList<NewsData>();
	
	public static void initNewsDataList(){
		NewsDataParser newsDataParse = new NewsDataParser();
		newsDataParse.setNewsDataList(newsDataList, "guangming.xml");
		//newsDataParse.setNewsDataList(newsDataList, "nanfangdaily.xml");
		//newsDataParse.setNewsDataList(newsDataList, "sichuan.xml");
		DeleteController.initRecycleList();
		
	}
	
	public static List<NewsData> getNewsDataList(){
		return newsDataList;
	}
	
	public NewsData getIndexNewsData(int index){
		return newsDataList.get(index);
	}
	
	public int getSize(){
		return newsDataList.size();
	}
}
