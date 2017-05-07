package data;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sun.misc.BASE64Decoder;


public class NewsDataParser {	
	
	public void setNewsDataList(List<NewsData> newsDataList,String path){
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
		 switch(k){
         	case 1:
         		temp.setTagIts(content);
         	case 3:
         		temp.setIsLoad(content);
         	case 5:
         		temp.setIsDeleted(content);
         	case 7:
         		temp.setMemo(content);
         	case 9:
         		temp.setTitle(content);
         	case 11:
         		temp.setDate(content);
         	case 13:
         		temp.setLocation(content);
         	case 15:
         		temp.setUrl(content);
         	case 17:
         		temp.setType(content);
         	case 19:
         		temp.setWordCount(content);
         	case 21:
         		temp.setID(content);
         	case 23:
         		temp.setTrueUrl(content);
         	case 25:
         		temp.setTags(content);
         	case 27:
         		temp.setEncodedContent(content);                      
         }   
	 }
}