package unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.NewsData;
import data.NewsDataList;

public class NewsDataTest {
	
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
	public void testGetTagIts() {
		assertEquals("社会各界帮助关爱$农民工或留守儿童$其他|问题儿童的形象|沐恩幸福的形象|可怜悲惨的形象|积极健康的形象|$其他|越来越多的小型私立学校被国家取消办学资格|私立学校学费高|私立学校教学质量没保障|无本地户籍难入公立学校|$$其他$其他|相关部门或领导人慰问|$男$特稿与特写$",testNewsData.getTagIts());
	}

	@Test
	public void testGetTitle() {
		assertEquals("全国政协十届四次会议举行第三次全体会议贾庆林出席12位委员围绕科教文衞等社会事业...",testNewsData.getTitle());
	}

	@Test
	public void testGetDate() {
		assertEquals(testNewsData.getDate(),"2006-03-10");
	}

	@Test
	public void testGetUrl() {
		assertEquals(testNewsData.getUrl(),"http://wisesearch.wisers.net.libezproxy.must.edu.mo/wortal/tool.do;jsessionid=4C5C7CA9D1D15177612A4E5E34FAD8EE.wise24?wp_dispatch=confirm-view&federated=true&doc-ids=news:2551^200603101410028(S:193916305)&menu-id=&on-what=selected&from-list&display-style=all&tooldisplay=true&currentsubdb=wiseselect");
	}

	@Test
	public void testGetType() {
		assertEquals("01,新闻",testNewsData.getType());
	}

	@Test
	public void testGetWordCount() {
		assertEquals("1484",testNewsData.getWordCount());
	}

	@Test
	public void testGetID() {
		assertEquals(testNewsData.getID(),"news:2551^200603101410028(S:193916305)");
	}

}
