package controller;

import java.util.List;
import java.util.Map;

import org.xml.sax.SAXException;

import constant.Lab;
import data.NewsData;
import data.NewsDataList;
import data.NewsDataParser;

public class MergeController {
	// 先解析一个XML，得到一个NewsData的List，再与现有的比较
	public static void Merge(String path) throws SAXException {
		List<NewsData> other = NewsDataParser.getFromFile(path);

		merge(NewsDataList.newsDataList, other);
	}

	// 对两者都贴了标签的，以自己的为准
	// 对自己没有贴标签的，加上标签
	private static String[] tags = { Lab.NEWSPAPERTYPE, Lab.NEWSTYPE, Lab.SHOWTYPE, Lab.REPORTTHEME,Lab.SEX };

	private static void merge(List<NewsData> l, List<NewsData> r) {
		int size = l.size();
		for (int i = 0; i < r.size(); i++) {
			boolean isContain = false;
			for (int j = 0; j < size; j++) {
				if (l.get(j).getTitle().equals(r.get(i).getTitle())) {
					isContain = true;
					for (String tag:tags) {
						if ((l.get(j).getTagItsMap().get(tag) == null ||
								l.get(j).getTagItsMap().get(tag).equals(""))
								&& r.get(i).getTagItsMap().get(tag) != null) {
							l.get(j).getTagItsMap().put(tag,
									r.get(i).getTagItsMap().get(tag));
						}
					}
					break;
				}
			}
			// r内的新闻没有在l中
			if (!isContain) {
				l.add(r.get(i));
			}
		}
	}

	// 做一致性测试的时候，假定贴的是相同的新闻
	// 判断是每一个标签都要有百分之九十的相似度
	public static boolean Consistency(List<NewsData> l, List<NewsData> r, Map<String,Float> consistentResult) {
		boolean result = true;
		int diff_count_newspapertype = 0;
		int diff_count_newstype = 0;
		int diff_count_reporttheme = 0;
		int diff_count_showtype = 0;

		for (int i = 0; i < l.size(); i++) {
			if (!l.get(i).getTagItsMap().get(Lab.NEWSPAPERTYPE)
					.equals(r.get(i).getTagItsMap().get(Lab.NEWSPAPERTYPE))) {
				diff_count_newspapertype += 1;
			}
			if (!l.get(i).getTagItsMap().get(Lab.NEWSTYPE).equals(r.get(i).getTagItsMap().get(Lab.NEWSTYPE))) {
				diff_count_newstype += 1;
			}
			if (!l.get(i).getTagItsMap().get(Lab.REPORTTHEME).equals(r.get(i).getTagItsMap().get(Lab.REPORTTHEME))) {
				diff_count_reporttheme += 1;
			}
			if (!l.get(i).getTagItsMap().get(Lab.SHOWTYPE).equals(r.get(i).getTagItsMap().get(Lab.SHOWTYPE))) {
				diff_count_showtype += 1;
			}
		}
		consistentResult.put(Lab.NEWSPAPERTYPE,1.0f-(float) diff_count_newspapertype / l.size());
		consistentResult.put(Lab.NEWSTYPE,1.0f-(float) diff_count_newstype / l.size());
		consistentResult.put(Lab.REPORTTHEME,1.0f-(float) diff_count_reporttheme / l.size());
		consistentResult.put(Lab.SHOWTYPE,1.0f-(float) diff_count_showtype / l.size());
		
		if ((float) diff_count_newspapertype / l.size() > 0.1 || (float) diff_count_newstype / l.size() > 0.1
				|| (float) diff_count_reporttheme / l.size() > 0.1 || (float) diff_count_showtype / l.size() > 0.1) {
			result = false;
		}
		return result;
	}

}
