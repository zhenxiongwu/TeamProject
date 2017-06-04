package data;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import constant.Lab;

public class NewsDataPersistence{
	
	
	//创建XML文件
	static public void createXml(List<NewsData> newsDataList,int key) {
		try{
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
			String fileName = df.format(new Date()) + ".xml";
			
			
			DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();  
			DocumentBuilder builder = factory.newDocumentBuilder();     
			Document document = builder.newDocument();   
			     
			//创建属性名、赋值  
			Element root = document.createElement("ArrayOfNewsData");  
			
			//创建第一个根节点、赋值
			for(int i = 0;i<newsDataList.size();i++){
				Element newsData = document.createElement("NewsData");  
				newsData.setAttribute("id", i + "");  
				NewsData news = newsDataList.get(i);
				
				Element isLoad = document.createElement("IsLoad"); 
				isLoad.setTextContent(news.getTitle());
				
				Element isDeleted = document.createElement("IsDeleted"); 
				isLoad.setTextContent(news.getIsDeleted() == true ? "true" : "false");
				
				Element title = document.createElement("Title");  
				title.setTextContent(news.getTitle());  
				
				Element date = document.createElement("Date"); 
				isLoad.setTextContent(news.getDate());
				
				Element location = document.createElement("Location"); 
				isLoad.setTextContent(news.getLocation());
				
				Element url = document.createElement("Url");  
				url.setTextContent(news.getUrl());  
				
				Element type = document.createElement("Type");  
				type.setTextContent(news.getType());  
				
				Element wordcount = document.createElement("WordCount");  
				wordcount.setTextContent(news.getWordCount());  
				
				Element id = document.createElement("ID");  
				id.setTextContent(news.getID());  
				
				Element trueurl = document.createElement("TrueUrl");  
				trueurl.setTextContent(news.getTrueUrl());  
				
				Element tags = document.createElement("Tags");  
				tags.setTextContent(news.getTags());  
				
				Element encodedcontent = document.createElement("EncodedContent");  
				encodedcontent.setTextContent(news.getEncodedContent());  
				
				Element newspapertype = document.createElement(Lab.NEWSPAPERTYPE); 
				String temp = news.getTagItsMap().get(Lab.NEWSPAPERTYPE);
				newspapertype.setTextContent(Encrypt(temp, key));  
				
				Element newstype = document.createElement(Lab.NEWSTYPE);  
				temp =  news.getTagItsMap().get(Lab.NEWSTYPE);
				newstype.setTextContent(Encrypt(temp, key));  
				
				Element reporttheme = document.createElement(Lab.REPORTTHEME); 
				temp = "报道主题";
				//temp = news.getTagItsMap().get(Lab.REPORTTHEME);
				reporttheme.setTextContent(Decrypt(Encrypt(temp, key),key));  
				
				Element showtype = document.createElement(Lab.SHOWTYPE);  
				temp = news.getTagItsMap().get(Lab.SHOWTYPE);
				showtype.setTextContent(Encrypt(temp, key));  
				
				Element sex = document.createElement(Lab.SEX);  
				temp = news.getTagItsMap().get(Lab.SEX);
				sex.setTextContent(Encrypt(temp,key));  

				newsData.appendChild(isLoad);  
				newsData.appendChild(isDeleted);  
				newsData.appendChild(title);  
				newsData.appendChild(date);  
				newsData.appendChild(location);  
				newsData.appendChild(url);  
				newsData.appendChild(type);  
				newsData.appendChild(wordcount);  
				newsData.appendChild(id);  
				newsData.appendChild(trueurl);  
				newsData.appendChild(tags);  
				newsData.appendChild(encodedcontent);  
				newsData.appendChild(newspapertype);  
				newsData.appendChild(newstype);  
				newsData.appendChild(reporttheme);  
				newsData.appendChild(showtype);  
				newsData.appendChild(sex);  
				
				//添加到属性中、  
				root.appendChild(newsData);  
			}      
			document.appendChild(root);  
			           
			//定义了用于处理转换指令，以及执行从源到结果的转换的  
			TransformerFactory transformerFactory = TransformerFactory.newInstance();  
			Transformer transformer = transformerFactory.newTransformer();  
			transformer.setOutputProperty("encoding", "UTF-8");  
			        
			
			transformer.transform(new DOMSource(document), new StreamResult(new File(fileName)));     
		} 
		catch (TransformerException | ParserConfigurationException e) {  
			e.printStackTrace();  
		}  
	}  
	
	public void parserXml(String fileName) {
		// TODO Auto-generated method stub
		
	}
	
	public static String Encrypt(String str,int k){
		if(str == null)
			return "";
		byte[] b = null;
		try {
			b = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < k; i += 2){
			byte m = b[i];
		    b[i] = b[i + 1];
		    b[i + 1] = m;
		}
		return b.toString();
	}
	
	public static String Decrypt(String str,int k){
		char arr[] = str.toCharArray();
		for(int i = 0; i < k; i += 2){
			StringBuilder strBuilder = new StringBuilder(str);
			char temp = arr[i];
			strBuilder.setCharAt(i,arr[i+1]);
			strBuilder.setCharAt(i+1,temp);
			str = strBuilder.toString();
		}
		String result = "";
		try {
			result = new String(str.getBytes(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
