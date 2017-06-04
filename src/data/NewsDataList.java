package data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.sun.org.apache.bcel.internal.generic.RET;

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
		NewsDataPersistence.createXml(newsDataList,5);
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
	public static Map<Object, Object> getStatisticsData(){
		Map<Object, Object> resultMap = new HashMap<>();
		String var4Type4Sex0 ="var4Type4Sex0";
		resultMap.put(var4Type4Sex0, 0.0);
		
		String var4Type4Sex1 = "var4Type4Sex1";
		resultMap.put(var4Type4Sex1, 0.0);
		
		String var4Type5Sex0 = "var4Type5Sex0";
		resultMap.put(var4Type5Sex0, 0.0);
		
		String var4Type5Sex1 = "var4Type5Sex1";
		resultMap.put(var4Type5Sex1, 0.0);
		
		String var4Type7Sex0 = "var4Type7Sex0";
		resultMap.put(var4Type7Sex0, 0.0);
		
		String var4Type7Sex1 = "var4Type7Sex1";
		resultMap.put(var4Type7Sex1, 0.0);

		for(NewsData newsData: NewsDataList.newsDataList){
			String goal = judgeTag(newsData);
			
			if(goal == "")
				continue;
			double count = (double) resultMap.get(goal);
			count += 1.0;
			resultMap.put(goal, count);
		}		
		return resultMap;
	}
	
	private static String judgeTag(NewsData newsData){
		Map<String, String> temp = newsData.getTagItsMap();
		String result = "";
		Logger logger = Logger.getLogger("daipeng");
		logger.info(temp.get(Lab.REPORTTHEME));
		if(temp.get(Lab.REPORTTHEME)!=null){
			if(temp.get(Lab.REPORTTHEME).equals(Lab.reportTheme[4])){
				result += "var4Type4";
			}
			
			else if(temp.get(Lab.REPORTTHEME).equals(Lab.reportTheme[5])){
				result += "var4Type5";
			}
			else if(temp.get(Lab.REPORTTHEME).equals(Lab.reportTheme[7])){
				result += "var4Type7";
			}
		}
		
		String gender = temp.get(Lab.SEX);
		if(gender==null||gender.equals("")){
			result = "";
		}
		else if(gender == Lab.sex[1]){
			result += "Sex0";
		}			
		else{
			result += "Sex1";
		}
		return result;
	}

}
