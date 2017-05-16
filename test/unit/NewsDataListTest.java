package unit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.NewsDataList;


public class NewsDataListTest {

	  private NewsDataList testList;
	  @Before
	  public void setUp() throws Exception {
	    testList = new NewsDataList();
	    NewsDataList.initNewsDataList();
	  }

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testGetSize() {
		assertEquals("1156",testList.getSize());
	}

}
