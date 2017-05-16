package unit;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Download;
import data.NewsData;
import data.NewsDataList;

public class DownloadTest {

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
	public void testTrueContent() {
		assertEquals("    12月30日，江西省德兴市海口小学14名留守儿童，用1万枚纽扣拼出了一幅边长1.2米的金猴凌驾“彩云”踏入2016的特别画图。       卓忠伟摄/光明图片   ",Download.trueContent(testNewsData));
	}

}
