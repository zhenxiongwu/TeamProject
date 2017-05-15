package constant;

public class Lab {
	
	public static String NEWSPAPERTYPE = "1";
	public static String NEWSTYPE = "2";
	public static String REPORTTHEME = "3";
	public static String SHOWTYPE = "4";
	public static String SEX = "5";
	
	public static String[] newspaperType = new String[]{
			"",
			"中央一级党报",
			"省一级党报",
			"经营模式市场化的报纸"
	};
	
	public static int getIndexOfPaperType(String lab){
		if(lab == null)
			return 0;
		if(lab.equals("中央一级党报"))
			return 1;
		else if(lab.equals("省一级党报"))
			return 2;
		else if(lab.equals("经营模式市场化的报纸"))
			return 3;
		return 0;
	}

	public static String[] newsType = new String[]{
			"",
			"纯净新闻",
			"特稿与特写",
			"评论",
			"其他"
	};
	
	public static int getIndexOfNewsType(String lab){
		if(lab == null)
			return 0;
		if(lab.equals("纯净新闻"))
			return 1;
		else if(lab.equals("特稿与特写"))
			return 2;
		else if(lab.equals("评论"))
			return 3;
		else if(lab.equals("其他"))
			return 4;
		return 0;
	}
	
	
	public static String[] reportTheme = new String[]{
			"",
			"社会各界帮助关爱",
			"社会各界对留守儿童现象提出的建议和看法",
			"表彰帮助关爱留守儿童的单位和个人",
			"留守儿童遭受暴力",
			"留守儿童被性侵、猥亵、强奸或是怀孕、生子等",
			"留守儿童犯罪",
			"留守儿童意外死亡",
			"留守儿童努力上进",
			"打工父母在城市艰难的生活",
			"其他"
	};
	
	public static int getIndexOfReportTheme(String lab){
		if(lab == null)
			return 0;
		if(lab.equals("社会各界帮助关爱"))
			return 1;
		else if(lab.equals("社会各界对留守儿童现象提出的建议和看法"))
			return 2;
		else if(lab.equals("表彰帮助关爱留守儿童的单位和个人"))
			return 3;
		else if(lab.equals("留守儿童遭受暴力"))
			return 4;
		else if(lab.equals("留守儿童被性侵、猥亵、强奸或是怀孕、生子等"))
			return 5;
		else if(lab.equals("留守儿童犯罪"))
			return 6;
		else if(lab.equals("留守儿童意外死亡"))
			return 7;
		else if(lab.equals("留守儿童努力上进"))
			return 8;
		else if(lab.equals("打工父母在城市艰难的生活"))
			return 9;
		else if(lab.equals("其他"))
			return 10;
		return 0;
	}
	
	
/*	public static String[] newsSource = new String[]{
			"",
			"记者",
			"政府",
			"企业",
			"事业单位",
			"公益团体",
			"专家学者",
			"政府领导、政协或人大代表",
			"其他"
	};*/
	
	public static String[] showType = new String[]{
			"",
			"积极健康的形象",
			"可怜悲惨的形象",
			"沐恩幸福的形象",
			"问题儿童的形象",
			"其他"
	};
	
	public static int getIndexOfShowType(String lab){
		if(lab == null)
			return 0;
		if(lab.equals("积极健康的形象"))
			return 1;
		else if(lab.equals("可怜悲惨的形象"))
			return 2;
		else if(lab.equals("沐恩幸福的形象"))
			return 3;
		else if(lab.equals("问题儿童的形象"))
			return 4;
		else if(lab.equals("其他"))
			return 5;
		return 0;
	}
	
	public static String[] sex = new String[]{
			"",
			"男",
			"女"
	};
	
	public static int getIndexOfSex(String lab){
		if(lab == null)
			return 0;
		else if(lab.equals("男"))
			return 1;
		else if(lab.equals("女"))
			return 2;
		return 0;
	}
	
	public static String[] helpNewsType = new String[]{
			"",
			"单纯一次捐款捐物",
			"旅游活动安排的项目之一",
			"免费开放",
			"设立长期资助项目",
			"其他"
	};
	
	public static String[] helpNewsSubject = new String[]{
			"",
			"政府部门",
			"企业",
			"事业单位",
			"公益团体",
			"个人"
	};
	
	public static String[] raiseNewsSubject = new String[]{
			"",
			"政府部门",
			"企业",
			"事业单位",
			"公益团体",
			"个人"
	};
	
	public static String[] reason = new String[]{
			"",
			"无本地户籍难入公立学校",
			"私立学校学费高",
			"私立学校教学质量没有保障",
			"越来越多的小型私立学校被国家取消本学资格",
			"其他"
	};
	
	public static String[] reportTheme_statistics = new String[]{
			"留守儿童遭受暴力",
			"留守儿童犯罪",
			"留守儿童意外死亡"
	};
}
