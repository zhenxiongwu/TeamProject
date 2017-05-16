package unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.TagController;
import data.NewsData;
import data.NewsDataList;

public class TagControllerTest {

	private NewsDataList testList;
	private List<NewsData> testNewsDataList;
	private NewsData testNewsData;
	@Before
	public void setUp() throws Exception {
		testList = new NewsDataList();
	    testList.initNewsDataList();
	    testNewsDataList = testList.getNewsDataList();
	    testNewsData=testNewsDataList.get(1155);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddTagIts() {
		TagController.addTagIts(testNewsData,"1","中央一级党报");
		assertEquals("中央一级党报",testNewsData.getTagItsMap().get("1"));
	}

}
