package data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import constant.Lab;
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

	
	//一、对变量4中第四类新闻加以分析，新闻中犯罪的留守儿童性别
	//二、对变量4中第五类新闻加以分析，新闻中被家暴、被性侵等行为的留守儿童性别
	//三、对变量4中第七类新闻加以分析，新闻中意外死亡的留守儿童性别
	public Map getStatisticsData(){
		Map resultMap = new HashMap<>();
		String var4Type4Sex0 ="var4Type4Sex0";
		resultMap.put(var4Type4Sex0, 0);
		
		String var4Type4Sex1 = "var4Type4Sex1";
		resultMap.put(var4Type4Sex1, 0);
		
		String var4Type5Sex0 = "var4Type5Sex0";
		resultMap.put(var4Type5Sex0, 0);
		
		String var4Type7Sex1 = "var4Type7Sex1";
		resultMap.put(var4Type7Sex1, 0);

		for(NewsData newsData: NewsDataList.newsDataList){
			Map<String, String> temp = newsData.getTagItsMap();
			
		}
		
		return resultMap;
		
	}
	private String judgeTag(NewsData newsData){
		Map<String, String> temp = newsData.getTagItsMap();
		String gender = newsData.getGender();
		String result;
		if(temp.get(Lab.)){
			result += "var4Type4";
		}
		else if(){
			
		}
	}



}
