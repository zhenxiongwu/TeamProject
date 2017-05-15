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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.sun.glass.ui.delegate.MenuDelegate;
import com.sun.glass.ui.delegate.MenuItemDelegate;
import com.sun.swing.internal.plaf.metal.resources.metal;

import constant.Lab;
import controller.DeleteController;
import controller.SearchController;
import data.NewsData;
import gui.NewsListBuilder.NewsListListener;

public class NewsSearchPageHolder extends PageHolder implements NewsListListener, SearchController.DataChangeListener {

	private final int COLUMN = 9;

	private Label paperStype_label;
	private Combo paperStype_combo;

	private Label newsStype_label;
	private Combo newsStype_combo;

	private Label reportStype_label;
	private Combo reportStype_combo;
	

	private Label showType_label;
	private Combo showType_combo;
	

	private Label sex_label;
	private Combo sex_combo;

	private Button search_button;

	private Composite newsListComposite;

	private NewsListBuilder newsListBuilder;

	@Override
	protected void createContent(Composite parent) {
		this.parent = parent;
		page = new Composite(parent, SWT.NONE);

		page.setLayout(new GridLayout(COLUMN, false));

		{
			{
				paperStype_label = new Label(page, SWT.NONE);
				horizontal_align(paperStype_label, GridData.HORIZONTAL_ALIGN_CENTER);
				paperStype_label.setText("\t报纸类型:");
			}

			{
				paperStype_combo = new Combo(page, SWT.READ_ONLY);
				horizontal_align(paperStype_combo, GridData.HORIZONTAL_ALIGN_FILL);
				paperStype_combo.setItems(Lab.newspaperType);
				paperStype_combo.select(0);
			}

			{
				newsStype_label = new Label(page, SWT.NONE);
				// horizontal_align_fill(newsStype_label);
				horizontal_align(newsStype_label, GridData.HORIZONTAL_ALIGN_CENTER);
				newsStype_label.setText("\t新闻类型:");
			}

			{
				newsStype_combo = new Combo(page, SWT.READ_ONLY);
				horizontal_align(newsStype_combo, GridData.HORIZONTAL_ALIGN_FILL);
				newsStype_combo.setItems(Lab.newsType);
			}

			{
				reportStype_label = new Label(page, SWT.NONE);
				// horizontal_align_fill(newsStype_label);
				horizontal_align(reportStype_label, GridData.HORIZONTAL_ALIGN_CENTER);
				reportStype_label.setText("\t报道主题:");
			}
			

			{
//				gridData.horizontalSpan = COLUMN - 1;
				reportStype_combo = new Combo(page, SWT.READ_ONLY);
				reportStype_combo.setLayoutData(new GridData());
//				horizontal_align(reportStype_combo, gridData);
				reportStype_combo.setItems(Lab.reportTheme);
			}

			{
				showType_label = new Label(page,SWT.NONE);
				horizontal_align(showType_label,GridData.HORIZONTAL_ALIGN_CENTER);
				showType_label.setText("\t形象呈现:");
			}
			
			{
				showType_combo = new Combo(page,SWT.READ_ONLY);
				showType_combo.setLayoutData(new GridData());
				showType_combo.setItems(Lab.showType);
			}

			{
				search_button = new Button(page, SWT.NONE);
				search_button.setText("　搜　索　");
				horizontal_align(search_button, GridData.HORIZONTAL_ALIGN_CENTER);
				search_button.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent arg0) {
						// TODO 自动生成的方法存根
						super.widgetSelected(arg0);
					}

				});
			}

		}

		{
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.horizontalSpan = COLUMN;

			newsListBuilder = new NewsListBuilder(page, SearchController.getSearchNewsList());
			newsListBuilder.setNewsListListener(this);
			newsListComposite = newsListBuilder.getNewsListComposite();
			newsListComposite.setLayoutData(gridData);
		}
	}

	private void horizontal_align(Control control, int swtType) {
		control.setLayoutData(new GridData(swtType));
	}

	@Override
	public void refresh() {
		newsListBuilder.refresh();
		page.layout();
	}

	@Override
	public void destory() {
		// TODO 自动生成的方法存根

	}

	public static interface OnClickNewsListener {
		public void onClickNews(Control control, NewsData object, int position);
	}

	private OnClickNewsListener onClickNewsListener;

	public void setOnClickNewsListener(OnClickNewsListener onClickNewsListener) {
		this.onClickNewsListener = onClickNewsListener;
	}

	private MenuItem menuItemDelete;
	private MenuItem menuItemDownload;

	class MyDownloadListener implements Listener {

		NewsData object;

		public void resetNewsData(NewsData newsData) {
			object = newsData;
		}

		@Override
		public void handleEvent(Event arg0) {
			// TODO download

		}
	};

	class MyDeleteListener implements Listener {

		NewsData object;

		public void resetNewsData(NewsData newsData) {
			object = newsData;
		}

		@Override
		public void handleEvent(Event arg0) {
			DeleteController.addRecycleNews(object);

		}

	}

	MyDownloadListener downloadListener = new MyDownloadListener();
	MyDeleteListener deleteListener = new MyDeleteListener();

	@Override
	public void onItemClickDown(int mouse_button, Control control, Menu menu, NewsData object, int position) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void onItemClickUp(int mouse_button, Control control, Menu menu, NewsData object, int position) {

		Logger logger = Logger.getLogger("zhenxiongwu");

		if (menu.getItemCount() == 0) {
			menuItemDownload = new MenuItem(menu, SWT.POP_UP);
			menuItemDownload.setText("下载(Download)　　");
			menuItemDownload.addListener(SWT.Selection, downloadListener);

			menuItemDelete = new MenuItem(menu, SWT.POP_UP);
			menuItemDelete.setText("删除(Delete)");
			menuItemDelete.addListener(SWT.Selection, deleteListener);
		}

		if (mouse_button == 3) {

			downloadListener.resetNewsData(object);
			deleteListener.resetNewsData(object);
		}
	}

	@Override
	public void onItemDoubleClick(int mouse_button, Control control, Menu menu, NewsData object, int position) {
		if (onClickNewsListener != null && mouse_button == 1) {
			onClickNewsListener.onClickNews(control, object, position);
		}

	}

	@Override
	public void searchNewsChange() {
		refresh();
	}

}
