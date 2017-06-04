package gui;

import java.util.logging.Logger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


import constant.Lab;
import controller.TagController;
import data.NewsData;

public class NewsContentPageHolder extends PageHolder {

	public static interface OnNewsContentButtonListener {
		public void onPrevNewsButtonClick(NewsData object);

		public void onBackButtonClick(NewsData object);

		public void onNextNewsButtonClick(NewsData object);
	}

	private OnNewsContentButtonListener onNewsContentButtonListener;

	private Composite parent;

	private Label label_newsTitle;
	private Label label_newsDate;
	private Label label_newsTag;
	private Label label_newsContent;

	private Label label_paperType;
	private Combo combo_paperType;

	private Label label_newsType;
	private Combo combo_newsType;

	private Label label_reportTheme;
	private Combo combo_reportTheme;

	private Label label_showType;
	private Combo combo_showType;

	private Label label_sex;
	private Combo combo_sex;

	private Text text_newsContent;

	private Button button_prev;

	private Button button_back;

	private Button button_next;

	private NewsData object;


	private final int GRIDLAYOUT_COLUMN = 6;

	@Override
	protected void createContent(Composite parent) {
		page = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(GRIDLAYOUT_COLUMN, false);
		page.setLayout(gridLayout);

		{
			label_newsTitle = new Label(page, SWT.WRAP);
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.horizontalSpan = GRIDLAYOUT_COLUMN;
			label_newsTitle.setLayoutData(gridData);
			label_newsTitle.setText("新闻标题：");
			label_newsTitle.setFont(new Font(Display.getDefault(), "宋体", 14, SWT.NONE));
		}

		{
			label_newsDate = new Label(page, SWT.NONE);
			// GridData gridData = new
			// GridData(GridData.HORIZONTAL_ALIGN_CENTER);
			// gridData.horizontalSpan = GRIDLAYOUT_COLUMN;
			// label_newsDate.setLayoutData(gridData);
			label_newsDate.setText("日期：");
		}

		{
			label_newsTag = new Label(page, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalSpan = GRIDLAYOUT_COLUMN;
			label_newsTag.setLayoutData(gridData);
			label_newsTag.setText("标签：");
		}

		{
			label_paperType = new Label(page, SWT.NONE);
//			label_paperType.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			label_paperType.setText("  报纸类型：");
		}

		{
			combo_paperType = new Combo(page, SWT.READ_ONLY);
			combo_paperType.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
			combo_paperType.setItems(Lab.newspaperType);
		}

		{
			label_newsType = new Label(page, SWT.NONE);
//			label_newsType.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			label_newsType.setText("\t新闻类型：");
		}

		{
			combo_newsType = new Combo(page, SWT.READ_ONLY);
			combo_newsType.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
			combo_newsType.setItems(Lab.newsType);
		}

		

		{
			label_showType = new Label(page, SWT.NONE);
			label_showType.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			label_showType.setText("\t呈现形式：");
		}

		{
			combo_showType = new Combo(page, SWT.READ_ONLY);
			combo_showType.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
			combo_showType.setItems(Lab.showType);
		}
		
		{
			label_reportTheme = new Label(page, SWT.NONE);
//			label_reportTheme.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			label_reportTheme.setText("  报道主题：");
		}

		{
			combo_reportTheme = new Combo(page, SWT.READ_ONLY);

			GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gridData.horizontalSpan = 3;
			combo_reportTheme.setLayoutData(gridData);
			combo_reportTheme.setItems(Lab.reportTheme);
		}

		{
			label_sex = new Label(page, SWT.NONE);
			GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
//			gridData.horizontalSpan = 3;
			label_sex.setLayoutData(gridData);
			label_sex.setText("\t性别：");
		}

		{
			combo_sex = new Combo(page, SWT.READ_ONLY);
			combo_sex.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
			combo_sex.setItems(Lab.sex);
		}
		{
			label_newsContent = new Label(page, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalSpan = GRIDLAYOUT_COLUMN;
			label_newsContent.setLayoutData(gridData);
			label_newsContent.setText("新闻内容：");
		}

		{
			text_newsContent = new Text(page, SWT.BORDER | SWT.V_SCROLL | SWT.READ_ONLY | SWT.WRAP);
			text_newsContent.setFont(new Font(Display.getDefault(), "宋体", 12, SWT.NONE));
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.horizontalSpan = GRIDLAYOUT_COLUMN;
			text_newsContent.setLayoutData(gridData);
		}

		{
			button_prev = new Button(page, SWT.NONE);
			GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
			gridData.horizontalSpan = 2;
//			gridData.horizontalAlignment = GridData.CENTER;
			button_prev.setLayoutData(gridData);
			button_prev.setText("　上一条　");
			button_prev.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					saveLab();
					onNewsContentButtonListener.onPrevNewsButtonClick(object);
				}

			});
		}

		{
			button_back = new Button(page, SWT.NONE);
			GridData gridData = new GridData();
			 gridData.horizontalSpan = 2;
			gridData.horizontalAlignment = GridData.CENTER;
			button_back.setLayoutData(gridData);
			button_back.setText("　返　回　");
			button_back.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					if (onNewsContentButtonListener != null) {
						saveLab();
						onNewsContentButtonListener.onBackButtonClick(object);

					}
				}

			});
		}

		{
			button_next = new Button(page, SWT.NONE);
			GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
			gridData.horizontalSpan = 2;
//			gridData.horizontalAlignment = GridData.CENTER;
			button_next.setLayoutData(gridData);
			button_next.setText("　下一条　");
			button_next.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					saveLab();
					onNewsContentButtonListener.onNextNewsButtonClick(object);
				}

			});
		}
	}

	@Override
	public void refresh() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void destory() {
		// TODO 自动生成的方法存根

	}

	public void display(NewsData object, boolean hasPrev, boolean hasNext) {

		this.object = object;

		label_newsTitle.setText("新闻标题： " + object.getTitle());
		label_newsDate.setText("日期：" + object.getDate());

		getLab();

		String newsContent = object.getEncodedContent();
		String text = newsContent.replaceAll("\n", "\n\n\t");
		text_newsContent.setText("\n\t" + text);
		text_newsContent.setTabs(4);

		button_prev.setEnabled(hasPrev);
		button_next.setEnabled(hasNext);
		page.layout();
	}

	private void saveLab() {
		String newsPaperType = Lab.newspaperType[combo_paperType.getSelectionIndex()];
		TagController.addTagIts(object, Lab.NEWSPAPERTYPE, newsPaperType);

		String newsType = Lab.newsType[combo_newsType.getSelectionIndex()];
		TagController.addTagIts(object, Lab.NEWSTYPE, newsType);

		String reportTheme = Lab.reportTheme[combo_reportTheme.getSelectionIndex()];
		TagController.addTagIts(object, Lab.REPORTTHEME, reportTheme);
		
		String showType = Lab.showType[combo_showType.getSelectionIndex()];
		TagController.addTagIts(object,Lab.SHOWTYPE,showType);
		
		String sex = Lab.sex[combo_sex.getSelectionIndex()];
		TagController.addTagIts(object,Lab.SEX,sex);
	}

	private void getLab() {
		String paperType = object.getTagItsMap().get(Lab.NEWSPAPERTYPE);
		combo_paperType.select(Lab.getIndexOfPaperType(paperType));

		String newsType = object.getTagItsMap().get(Lab.NEWSTYPE);
		combo_newsType.select(Lab.getIndexOfNewsType(newsType));

		String reportTheme = object.getTagItsMap().get(Lab.REPORTTHEME);
		combo_reportTheme.select(Lab.getIndexOfReportTheme(reportTheme));
		
		String showType = object.getTagItsMap().get(Lab.SHOWTYPE);
		combo_showType.select(Lab.getIndexOfShowType(showType));
		
		String sex = object.getTagItsMap().get(Lab.SEX);
		combo_sex.select(Lab.getIndexOfSex(sex));
	}

	public void setOnNewsContentButtonListener(OnNewsContentButtonListener onNewsContentButtonListener) {
		this.onNewsContentButtonListener = onNewsContentButtonListener;
	}
}
