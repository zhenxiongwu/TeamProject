package data;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import constant.Lab;
import sun.misc.BASE64Decoder;


public class NewsDataParser {	
	
	public static void setNewsDataList(List<NewsData> newsDataList,String path){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(path);
            
            NodeList newsList = document.getElementsByTagName("NewsData");
            for (int i = 0; i < newsList.getLength(); i++) {
            	Node news = newsList.item(i);
            	NewsData node = new NewsData();
                NodeList childNodes = news.getChildNodes();
                for (int k = 0; k < childNodes.getLength(); k++) {
                    if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                        String content;
                        if(childNodes.item(k).getNodeName() == "EncodedContent")
                        	content = decodeContent(childNodes.item(k).getTextContent());
                        else
                        	content = childNodes.item(k).getTextContent();
                        addAttr(node,k,content);
                        
                    }
                }
                newsDataList.add(node);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }        
	}

	static private String decodeContent(String encodedContent) {
		    byte[] b = null;
		    BASE64Decoder decoder = new BASE64Decoder();

		    try {
		      b = decoder.decodeBuffer(encodedContent);
		    } catch (IOException e) {
		      e.printStackTrace();
		    }

		    for (int i = 0; i < b.length; i += 2) {
		      byte m = b[i];
		      b[i] = b[i + 1];
		      b[i + 1] = m;
		    }

		    String str = null;
		    try {
		      str = new String(b, "utf-16");
		    } catch (UnsupportedEncodingException e) {
		      e.printStackTrace();
		    }
		    str = str.replace("<body>", "").replace("</body>", "").replace("<P>", "").replace("</P>", "");
		    str = str.replace("<html>", "").replace("<ml>", "").replace("</html>","");
		    return str;
	 }

	static private void addAttr(NewsData temp,int k,String content){
		/*Logger logger = Logger.getLogger("zhenxiongwu");
		logger.info(content);*/
		
		//TODO 计数k可能有错
		 switch(k){
         	case 2:
         		temp.setTagIts(content);
         		break;
         	case 3:
         		temp.setIsLoad(content);
         		break;
         	case 4:
         		temp.setIsDeleted(content);
         		break;
         	case 7:
         		temp.setMemo(content);
         		break;
         	case 9:
         		temp.setTitle(content);
         		break;
         	case 11:
         		temp.setDate(content);
         		break;
         	case 13:
         		temp.setLocation(content);
         		break;
         	case 15:
         		temp.setUrl(content);
         		break;
         	case 17:
         		temp.setType(content);
         		break;
         	case 19:
         		temp.setWordCount(content);
         		break;
         	case 21:
         		temp.setID(content);
         		break;
         	case 23:
         		temp.setTrueUrl(content);
         		break;
         	case 25:
         		temp.setTags(content);
         		break;
         	case 27:
         		temp.setEncodedContent(content);  
         		break;                    
         }   
	 }

	static private void addAttr(NewsData temp,String tagName,String content){
		if(tagName.equals(Lab.ISLOAD)){
			temp.setIsLoad(content);
		}
		if(tagName.equals(Lab.ISDELETED)){
			Logger logger = Logger.getLogger("zhenxiongwu");
			logger.info("is Deleted");
			temp.setIsDeleted(content);
		}
		if(tagName.equals(Lab.TITLE)){
			temp.setTitle(content);
		}
		if(tagName.equals(Lab.DATE)){
			temp.setDate(content);
		}
		if(tagName.equals(Lab.LOCATION)){
			temp.setLocation(content);
		}
		if(tagName.equals(Lab.URL)){
			temp.setUrl(content);
		}
		if(tagName.equals(Lab.TYPE)){
			temp.setType(content);
		}
		if(tagName.equals(Lab.WORDCOUNT)){
			temp.setWordCount(content);
		}
		if(tagName.equals(Lab.ID)){
			temp.setID(content);
		}
		if(tagName.equals(Lab.TRUEURL)){
			temp.setTrueUrl(content);
		}
		if(tagName.equals(Lab.TAGS)){
			temp.setTags(content);
		}
		if(tagName.equals(Lab.ENCODEDCONTENT)){
			temp.setEncodedContent(content);
		}
		if(tagName.equals(Lab.NEWSPAPERTYPE)){
			temp.getTagItsMap().put(Lab.NEWSPAPERTYPE, content);
		}
		if(tagName.equals(Lab.NEWSTYPE)){
			temp.getTagItsMap().put(Lab.NEWSTYPE, content);
		}
		if(tagName.equals(Lab.REPORTTHEME)){
			temp.getTagItsMap().put(Lab.REPORTTHEME, content);
		}
		
		if(tagName.equals(Lab.SHOWTYPE)){
			temp.getTagItsMap().put(Lab.SHOWTYPE, content);
		}
		if(tagName.equals(Lab.SEX)){
			temp.getTagItsMap().put(Lab.SEX, content);
		}
	}
	
	//解析新生成的XML文件
	@SuppressWarnings("null")
	static public List<NewsData> getFromFile(String path){
		List<NewsData> result = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(path);
            
            NodeList newsList = document.getElementsByTagName("NewsData");
            for (int i = 0; i < newsList.getLength(); i++) {
            	Node news = newsList.item(i);
            	NewsData node = new NewsData();
                NodeList childNodes = news.getChildNodes();
                for (int k = 0; k < childNodes.getLength(); k++) {
                    if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                        String tagName = childNodes.item(k).getNodeName();
                    	String content = childNodes.item(k).getTextContent();
                        addAttr(node,tagName,content);
                    }
                }
                result.add(node);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return result;        
	}

}