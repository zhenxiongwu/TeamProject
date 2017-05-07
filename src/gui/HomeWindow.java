package gui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import gui.utils.SWTUtils;

public class HomeWindow extends Window {

	/**
	 * The function SashForm under the image of the title
	 */
	private SashForm sashForm_function;

	/**
	 * The List is the ListView in SWT, not the List in java.util
	 */
	private List selectList;

	/**
	 * The composites in the right of the page
	 */
	private StackLayout stackLayout = new StackLayout();

	private Composite rightComposite;

	private PageHolder mainPageHolder;
	private Composite mainPage;

	private StatisticsPageHolder statisticsPageHolder;
	private Composite statisticsPage;

	private RecyclePageHolder recyclePageHolder;
	private Composite recyclePage;


	static final int COLUMN = 7;

	@Override
	void createContents(Shell shell) {
		SWTUtils.fullScreen(shell);
		shell.setText("关爱留守儿童");
		shell.setLayout(new GridLayout(1, false));

		{

			SashForm sashForm = new SashForm(shell, SWT.BORDER);
			sashForm.setLayoutData(new GridData(GridData.FILL_BOTH));

			Composite title_image = new Composite(sashForm, SWT.NONE);
			title_image.setBackgroundImage(new Image(display, "image/jpg3.jpg"));

			createSashForm_function(sashForm);

			sashForm.setOrientation(SWT.VERTICAL);

			sashForm.setWeights(new int[] { 2, 15 });

		}
	}

	/**
	 * 主功能分割窗
	 * 
	 * @param parentComp
	 */
	private void createSashForm_function(Composite parentComp) {
		sashForm_function = new SashForm(parentComp, SWT.NONE);
		selectList = new List(sashForm_function, SWT.BORDER); // 分割窗左边的列表
		selectList.setItems(new String[] { "查看（筛选）", "统计", "回收站" });

		Font font = new Font(display, "宋体", 12, SWT.NONE);
		selectList.setFont(font);
		selectList.setSelection(0);
		selectList.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = selectList.getSelectionIndex();
				switch (index) {
				case 0:
					mainPageHolder.refresh();
					stackLayout.topControl = mainPage;
					break;
				case 1:
					stackLayout.topControl = statisticsPage;
					break;
				case 2:
					stackLayout.topControl = recyclePage;
					break;
				default:
				}
				rightComposite.layout();
			}

		});

		rightComposite = new Composite(sashForm_function, SWT.NONE); // 分割窗右边的堆栈式容器
		rightComposite.setLayout(stackLayout);

		{
			mainPageHolder = new MainPageHolder();
			mainPage = mainPageHolder.getPage(rightComposite); // 获得查看新闻页面
		}


		{
			statisticsPageHolder = new StatisticsPageHolder();
			statisticsPage = statisticsPageHolder.getPage(rightComposite);
		}

		{
			recyclePageHolder = new RecyclePageHolder();
			recyclePage = recyclePageHolder.getPage(rightComposite);
		}

		stackLayout.topControl = mainPage;
		// new Label(mainPage, SWT.NONE);

		sashForm_function.setWeights(new int[] { 1, 10 });

	}

}
