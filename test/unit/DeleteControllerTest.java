package unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.DeleteController;
import data.NewsData;
import data.NewsDataList;

public class DeleteControllerTest {
	
	private NewsDataList testList;
	private List<NewsData> testNewsDataList;
	private NewsData testNewsData;
	@Before
	public void setUp() throws Exception {
		testList = new NewsDataList();
	    testList.initNewsDataList();
	    testNewsDataList = testList.getNewsDataList();
	    testNewsData=testNewsDataList.get(2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddRecycleNews() {
		DeleteController.addRecycleNews(testNewsData);
		assertEquals(true,testNewsData.getIsDeleted());
	}

	@Test
	public void testRecoverRecycleNews() {
		DeleteController.recoverRecycleNews(testNewsData);
		assertEquals(false,testNewsData.getIsDeleted());
	}

}
