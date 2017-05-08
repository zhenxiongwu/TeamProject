package testdata;

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
	@Before
	public void setUp() throws Exception {
		testList = new NewsDataList();
		testList.setNewsDataList();
		testNewsDataList = testList.getNewsDataList();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetTagIts() {
		assertEquals(testNewsDataList.get(2).getTagIts(),"社会各界帮助关爱$农民工或留守儿童$其他|问题儿童的形象|沐恩幸福的形象|可怜悲惨的形象|积极健康的形象|$其他|越来越多的小型私立学校被国家取消办学资格|私立学校学费高|私立学校教学质量没保障|无本地户籍难入公立学校|$$其他$其他|相关部门或领导人慰问|$男$特稿与特写$");
	}

	@Test
	public void testGetTitle() {
		assertEquals(testNewsDataList.get(2).getTitle(),"全国政协十届四次会议举行第三次全体会议贾庆林出席12位委员围绕科教文衞等社会事业...");
	}

	@Test
	public void testGetType() {
		assertEquals(testNewsDataList.get(2).getType(),"01,新闻");
	}

}

