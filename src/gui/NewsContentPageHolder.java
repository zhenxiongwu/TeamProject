package gui;

import java.util.logging.Logger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import constant.Lab;

public class NewsContentPageHolder extends PageHolder {
	
	public static interface OnBackButtonListener{
		public void onBackButtonClick();
	}
	
	private OnBackButtonListener onBackButtonListener;
	
	private Composite parent;

	private Label label_newsTitle;
	private Label label_newsDate;
	private Label label_newsTag;
	private Label label_newsContent;
	private Label label_paperType;
	private Label label_newsType;
	private Label label_reportTheme;

	private Combo combo_paperType;
	private Combo combo_newsType;
	private Combo combo_reportTheme;

	private Text text_newsContent;

	private Button button_back;
	
	private final int GRIDLAYOUT_COLUMN = 6;
	
	@Override
	protected void createContent(Composite parent) {
		page = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(GRIDLAYOUT_COLUMN, true);
		page.setLayout(gridLayout);

		{
			label_newsTitle = new Label(page, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalSpan = 1;
			label_newsTitle.setLayoutData(gridData);
			label_newsTitle.setText("新闻标题：");
		}

		{
			label_newsDate = new Label(page, SWT.NONE);
			GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
			gridData.horizontalSpan = 5;
			label_newsDate.setLayoutData(gridData);
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
			label_paperType.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			label_paperType.setText("报纸类型：");
		}

		{
			combo_paperType = new Combo(page, SWT.NONE);
			combo_paperType.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
			combo_paperType.setItems(Lab.newspaperType);
		}

		{
			label_newsType = new Label(page, SWT.NONE);
			label_newsType.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			label_newsType.setText("新闻类型：");
		}

		{
			combo_newsType = new Combo(page, SWT.NONE);
			combo_newsType.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
			combo_newsType.setItems(Lab.newsType);
		}

		{
			label_reportTheme = new Label(page, SWT.NONE);
			label_reportTheme.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
			label_reportTheme.setText("报道主题：");
		}

		{
			combo_reportTheme = new Combo(page, SWT.NONE);
			combo_reportTheme.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
			combo_reportTheme.setItems(Lab.reportTheme);
		}

		{
			label_newsContent = new Label(page, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalSpan = GRIDLAYOUT_COLUMN;
			label_newsContent.setLayoutData(gridData);
			label_newsContent.setText("新闻内容：");
		}

		{
			text_newsContent = new Text(page, SWT.BORDER | SWT.V_SCROLL | SWT.READ_ONLY);
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.horizontalSpan = GRIDLAYOUT_COLUMN;
			text_newsContent.setLayoutData(gridData);
			text_newsContent.setText("新闻内容。");
		}

		{
			button_back = new Button(page, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalSpan = GRIDLAYOUT_COLUMN;
			gridData.horizontalAlignment = GridData.CENTER;
			button_back.setLayoutData(gridData);
			button_back.setText("　返　回　");
			button_back.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					if(onBackButtonListener != null){
						onBackButtonListener.onBackButtonClick();
					}
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
	

	public void display(Object object) {
		Logger logger = Logger.getLogger("zhenxiongwu");
		logger.info((String) object);
		label_newsTitle.setText("新闻标题： " + (String) object);
		
		//TODO: Add the logic of displaying the infomation of the object

		page.layout();
	}
	
	public void setOnBackButtonListener(OnBackButtonListener onBackButtonListener){
		this.onBackButtonListener = onBackButtonListener;
	}
}
