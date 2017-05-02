package gui;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import java.util.ArrayList;

import org.eclipse.swt.SWT;

import utils.SWTUtils;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.ScrolledComposite;

public class Window {

	private Display display;

	private Shell shell;

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
	public static StackLayout stackLayout = new StackLayout();

	public static Composite rightComposite;

	public static Composite mainPage;

	private StatisticsPageHolder statisticsPageHolder;
	private Composite statisticsPage;

	private RecyclePageHolder recyclePageHolder;
	private Composite recyclePage;

	private NewsContentHolder newsContentHolder;
	private Composite newsContentPage;

	static final int COLUMN = 7;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Window window = new Window();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(display, SWT.CLOSE | SWT.MIN);

		// shell.setSize(450, 300);
		SWTUtils.fullScreen(shell);
		shell.setText("关爱留守儿童");
		shell.setLayout(new GridLayout(1, false));

		{

			SashForm sashForm = new SashForm(shell, SWT.BORDER);
			sashForm.setLayoutData(new GridData(GridData.FILL_BOTH));

			Composite title_image = new Composite(sashForm, SWT.NONE);
//			title_image.setBackgroundImage(new Image(display, "image/jpg3.jpg"));

			createSashForm_function(sashForm);

			sashForm.setOrientation(SWT.VERTICAL);

			sashForm.setWeights(new int[] { 2, 15 });

		}
	}

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

		createMainPage(rightComposite);

		{
			newsContentHolder = NewsContentHolder.getInstance(rightComposite);
			newsContentPage = newsContentHolder.getComposite();
		}

		{
			statisticsPageHolder = StatisticsPageHolder.getInstance(rightComposite);
			statisticsPage = statisticsPageHolder.getComposite();
		}

		{
			GridData gd_scrolledComposite = new GridData(GridData.FILL_BOTH);
			gd_scrolledComposite.horizontalSpan = COLUMN;
			java.util.List<Object> objects = new ArrayList();
			objects.add("关注留守儿童");
			recyclePageHolder = RecyclePageHolder.getInstance(rightComposite, gd_scrolledComposite, objects);
			recyclePage = recyclePageHolder.getComposite();
		}

		stackLayout.topControl = mainPage;
		// new Label(mainPage, SWT.NONE);

		sashForm_function.setWeights(new int[] { 1, 10 });
	}

	/**
	 * Create content in the composite "mainPage"
	 * 
	 * @param parentComp
	 * @return
	 */
	private void createMainPage(Composite parentComp) {
		mainPage = new Composite(parentComp, SWT.NONE);
		mainPage.setLayout(new GridLayout(COLUMN, true));

		{
			Label paperStype_label = new Label(mainPage, SWT.NONE);
			horizontal_align(paperStype_label, GridData.HORIZONTAL_ALIGN_CENTER);
			paperStype_label.setText("报纸类型:");

			Combo paperStype_combo = new Combo(mainPage, SWT.READ_ONLY);
			horizontal_align(paperStype_combo, GridData.HORIZONTAL_ALIGN_FILL);
			paperStype_combo.setItems("", "中央一级党报", "省一级党报");
			paperStype_combo.select(0);

			Label newsStype_label = new Label(mainPage, SWT.NONE);
			// horizontal_align_fill(newsStype_label);
			horizontal_align(newsStype_label, GridData.HORIZONTAL_ALIGN_CENTER);
			newsStype_label.setText("新闻类型:");

			Combo newsStype_combo = new Combo(mainPage, SWT.READ_ONLY);
			horizontal_align(newsStype_combo, GridData.HORIZONTAL_ALIGN_FILL);
			newsStype_combo.setItems();

			Label reportStype_label = new Label(mainPage, SWT.NONE);
			// horizontal_align_fill(newsStype_label);
			horizontal_align(reportStype_label, GridData.HORIZONTAL_ALIGN_CENTER);
			reportStype_label.setText("报道主题:");

			Combo reportStype_combo = new Combo(mainPage, SWT.READ_ONLY);
			horizontal_align(reportStype_combo, GridData.HORIZONTAL_ALIGN_FILL);
			newsStype_combo.setItems();

			Button search_button = new Button(mainPage, SWT.NONE);
			search_button.setText("　搜　索　");
			horizontal_align(search_button, GridData.HORIZONTAL_ALIGN_CENTER);

		}

		{
			GridData gd_scrolledComposite = new GridData(GridData.FILL_BOTH);
			gd_scrolledComposite.horizontalSpan = COLUMN;
			java.util.List<Object> objects = new ArrayList();
			objects.add("关注留守儿童");
			objects.add("光明日报");
			objects.add("sakldfjl");
			objects.add("asjdkfjlk");
			objects.add("关注留守儿童");
			objects.add("光明日报");
			objects.add("sakldfjl");
			objects.add("asjdkfjlk");

			NewsListComposite.createNewsComposite(mainPage, gd_scrolledComposite, objects);
		}

	}

	private void horizontal_align(Control control, int swtType) {
		control.setLayoutData(new GridData(swtType));
	}

}
