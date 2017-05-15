package data;

import java.util.HashMap;
import java.util.Map;

public class NewsData {
	private String TagIts;
	private String IsLoad;
	private boolean IsDeleted;
	private String Memo;
	private String Title;
	private String Date;
	private String Location;
	private String Url;
	private String Type;
	private String WordCount;
	private String ID;
	private String TrueUrl;
	private String Tags;
	private String EncodedContent;
	private String Gender="";
	private Map<String, String> TagItsMap = new HashMap<String, String>();

	public String getGender(){
		return Gender;
	}
	
	public void setGender(String gender){
		Gender = gender;
	}
	
	public void setTagItsMap(Map<String, String> tagItsMap) {
		TagItsMap = tagItsMap;
	}

	public Map<String, String> getTagItsMap() {
		return TagItsMap;
	}

	public String getTagIts() {
		return TagIts;
	}

	public void setTagIts(String tagIts) {
		TagIts = tagIts;
	}

	public String getIsLoad() {
		return IsLoad;
	}

	public void setIsLoad(String isLoad) {
		IsLoad = isLoad;
	}

	public boolean getIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		if (isDeleted != null && isDeleted.equals("true"))
			this.IsDeleted = true;
		else
			this.IsDeleted = false;
	}

	public String getMemo() {
		return Memo;
	}

	public void setMemo(String memo) {
		Memo = memo;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getWordCount() {
		return WordCount;
	}

	public void setWordCount(String wordCount) {
		WordCount = wordCount;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getTrueUrl() {
		return TrueUrl;
	}

	public void setTrueUrl(String trueUrl) {
		TrueUrl = trueUrl;
	}

	public String getTags() {
		return Tags;
	}

	public void setTags(String tags) {
		Tags = tags;
	}

	public String getEncodedContent() {
		return EncodedContent;
	}

	public void setEncodedContent(String encodedContent) {
		EncodedContent = encodedContent;
	}
}
