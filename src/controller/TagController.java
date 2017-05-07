package controller;
import java.util.HashMap;
import java.util.Map;

import data.NewsData;
import data.NewsDataList;
import main.Launcher;
public class TagController {
	
	//报纸类型n
	private String newspaperType;
	
	private String newsType;
	
	private String newsCount;
	
	private String newsTopic;
	
	private String newsSource;

	private String showType;

	private String helpNewsType;
	
	private String helpNewsSubject;

	private String raiseNewsSubject;
	
	private String reason;
	
	public String getNewspaperType() {
		return newspaperType;
	}

	public void setNewspaperType(String newspaperType) {
		this.newspaperType = newspaperType;
	}

	public String getNewsType() {
		return newsType;
	}


	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}


	public String getNewsCount() {
		return newsCount;
	}


	public void setNewsCount(String newsCount) {
		this.newsCount = newsCount;
	}


	public String getNewsTopic() {
		return newsTopic;
	}


	public void setNewsTopic(String newsTopic) {
		this.newsTopic = newsTopic;
	}


	public String getNewsSource() {
		return newsSource;
	}


	public void setNewsSource(String newsSource) {
		this.newsSource = newsSource;
	}


	public String getShowType() {
		return showType;
	}


	public void setShowType(String showType) {
		this.showType = showType;
	}


	public String getHelpNewsType() {
		return helpNewsType;
	}


	public void setHelpNewsType(String helpNewsType) {
		this.helpNewsType = helpNewsType;
	}


	public String getHelpNewsSubject() {
		return helpNewsSubject;
	}


	public void setHelpNewsSubject(String helpNewsSubject) {
		this.helpNewsSubject = helpNewsSubject;
	}


	public String getRaiseNewsSubject() {
		return raiseNewsSubject;
	}


	public void setRaiseNewsSubject(String raiseNewsSubject) {
		this.raiseNewsSubject = raiseNewsSubject;
	}

	public String getReason(){
		return reason;
	}
	
	public void setReason(String reason){
		this.reason = reason;
	}
	
	//获取哪一条newsData被点击
	//这里暂时定为传position
	public NewsData getNewsData(int position){ 
		return Launcher.testList.getIndexNewsData(position);
	}

	public void addTagIts(NewsData newsData){
		Map<String, String> temp = new HashMap<String, String>();
		temp.put("报纸类别",newspaperType);
		temp.put("新闻类型",newsType);
		temp.put("报道数量",newsCount);
		temp.put("报道主题",newsTopic);
		temp.put("新闻报道消息来源",newsSource);
		temp.put("媒介形象呈现", showType);
		temp.put("帮助新闻的具体种类",helpNewsType);
		temp.put("帮助类新闻的主体", helpNewsSubject);
		temp.put("表彰奖励的新闻主体",raiseNewsSubject);
		temp.put("有关民工子女不能留在城市读书的原因", reason);
		newsData.setTagItsMap(temp);
	}
}
