package data;
import java.util.ArrayList;
import java.util.List;

public class NewsDataList {
	public static List<NewsData> newsDataList;
	
	public void setNewsDataList(){
		NewsDataParser newsDataParse = new NewsDataParser();
		newsDataList = new ArrayList<NewsData>();
		newsDataParse.setNewsDataList(newsDataList, "guangming.xml");
		//newsDataParse.setNewsDataList(newsDataList, "nanfangdaily.xml");
		//newsDataParse.setNewsDataList(newsDataList, "sichuan.xml");
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
/*	
	public static void main(String[] args){
		NewsDataList testList = new NewsDataList();
		testList.setNewsDataList();
		List<NewsData> testNewsDataList = testList.getNewsDataList();
		for(int i=0;i<testNewsDataList.size();i++){
			System.out.println(testNewsDataList.get(i).getTagIts());
		}
	}*/
}
