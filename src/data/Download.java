package data;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.sun.org.apache.xpath.internal.operations.Equals;

//下载外部新闻
public class Download {
	public static boolean trueContent(NewsData news){
		try{
			if(news.getTrueUrl().equals("")){
				return false;
			}
			Document doc = Jsoup.connect(news.getTrueUrl()).get();
			Element content =  doc.select("div#articleContent").first();
			news.setEncodedContent(content.text());
			return true;			
		}catch(IOException e){
			return false;
		}
	}
}
