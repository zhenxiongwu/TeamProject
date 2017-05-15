package data;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

//下载外部新闻
public class Download {
	public static String trueContent(NewsData news){
		try{
			Document doc = Jsoup.connect(news.getTrueUrl()).get();
			Element content =  doc.select("div#articleContent").first();
			news.setEncodedContent(content.text());
			return content.text();
		}catch(IOException e){
			return "下载失败";
		}
	}
}
