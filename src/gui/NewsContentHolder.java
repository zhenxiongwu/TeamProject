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

public class NewsContentHolder {

	private static NewsContentHolder newsContentComposite;

	public static NewsContentHolder getInstance(Composite parent) {
		if (newsContentComposite == null) {
			newsContentComposite = new NewsContentHolder(parent);
			newsContentComposite.parent = parent;
		}
		return newsContentComposite;
	}

	private Composite parent;

	private Composite composite;

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

	private NewsContentHolder(Composite parent) {
		createComposite(parent);
	}

	private void createComposite(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(6, true);
		composite.setLayout(gridLayout);

		{
			label_newsTitle = new Label(composite, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalSpan = 3;
			label_newsTitle.setLayoutData(gridData);
			label_newsTitle.setText("新闻标题：");
		}

		{
			label_newsDate = new Label(composite, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalSpan = 3;
			label_newsDate.setLayoutData(gridData);
			label_newsDate.setText("日期：");
		}

		{
			label_newsTag = new Label(composite, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalSpan = 6;
			label_newsTag.setLayoutData(gridData);
			label_newsTag.setText("标签：");
		}

		{
			label_paperType = new Label(composite, SWT.NONE);
			label_paperType.setText("报纸类型：");
		}

		{
			combo_paperType = new Combo(composite, SWT.NONE);
		}

		{
			label_newsType = new Label(composite, SWT.NONE);
			label_newsType.setText("新闻类型：");
		}

		{
			combo_newsType = new Combo(composite, SWT.NONE);
		}

		{
			label_reportTheme = new Label(composite, SWT.NONE);
			label_reportTheme.setText("报道主题：");
		}

		{
			combo_reportTheme = new Combo(composite, SWT.NONE);
		}

		{
			label_newsContent = new Label(composite, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalSpan = 6;
			label_newsContent.setLayoutData(gridData);
			label_newsContent.setText("新闻内容：");
		}

		{
			text_newsContent = new Text(composite, SWT.BORDER | SWT.V_SCROLL | SWT.READ_ONLY);
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.horizontalSpan = 6;
			text_newsContent.setLayoutData(gridData);
			text_newsContent.setText("新闻内容。");
		}

		{
			button_back = new Button(composite, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalSpan = 6;
			gridData.horizontalAlignment = GridData.CENTER;
			button_back.setLayoutData(gridData);
			button_back.setText("　返　回　");
			button_back.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					Window.stackLayout.topControl = Window.mainPage;
					Window.rightComposite.layout();
				}

			});
		}

	}

	public void refresh(Object object) {
		Logger logger = Logger.getLogger("zhenxiongwu");
		logger.info((String) object);
		label_newsTitle.setText("新闻标题： " + (String) object);
		
		//TODO: Add the logic of refreshing the page

		composite.layout();
	}

	public Composite getComposite() {
		return composite;
	}

}
