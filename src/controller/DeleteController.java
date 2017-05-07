package controller;

import data.NewsData;
import data.deleteNewsList;
import main.Launcher;
public class DeleteController {

	private deleteNewsList deleteNewsList;
	//删除新闻
	public void addDeleteNews(int position){
		NewsData temp = Launcher.testList.getIndexNewsData(position);
		deleteNewsList.addDeletedNews(temp);
	}
	
	//回复新闻
	public void recoverDeleteNews(int position){
		NewsData temp = Launcher.testList.getIndexNewsData(position);
		deleteNewsList.recoverDeletedNews(temp);
	}
}
