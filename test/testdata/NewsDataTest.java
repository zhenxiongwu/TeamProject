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
		assertEquals(testNewsDataList.get(2).getTagIts(),"����������ذ�$ũ�񹤻����ض�ͯ$����|�����ͯ������|����Ҹ�������|�������ҵ�����|��������������|$����|Խ��Խ���С��˽��ѧУ������ȡ����ѧ�ʸ�|˽��ѧУѧ�Ѹ�|˽��ѧУ��ѧ����û����|�ޱ��ػ������빫��ѧУ|$$����$����|��ز��Ż��쵼��ο��|$��$�ظ�����д$");
	}

	@Test
	public void testGetTitle() {
		assertEquals(testNewsDataList.get(2).getTitle(),"ȫ����Эʮ���Ĵλ�����е�����ȫ���������ֳ�ϯ12λίԱΧ�ƿƽ����o�������ҵ...");
	}

	@Test
	public void testGetType() {
		assertEquals(testNewsDataList.get(2).getType(),"01,����");
	}

}

