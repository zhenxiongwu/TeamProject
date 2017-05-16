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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.sun.glass.ui.delegate.MenuDelegate;
import com.sun.glass.ui.delegate.MenuItemDelegate;
import com.sun.org.apache.bcel.internal.generic.I2F;
import com.sun.swing.internal.plaf.metal.resources.metal;

import constant.Lab;
import controller.DeleteController;
import controller.SearchController;
import data.Download;
import data.NewsData;
import gui.NewsListBuilder.NewsListListener;

public class NewsSearchPageHolder extends PageHolder implements NewsListListener, SearchController.DataChangeListener {

	private final int COLUMN = 9;

	private Label paperType_label;
	private Combo paperType_combo;

	private Label newsType_label;
	private Combo newsType_combo;

	private Label reportType_label;
	private Combo reportType_combo;
	

	private Label showType_label;
	private Combo showType_combo;
	

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
				paperType_label = new Label(page, SWT.NONE);
				horizontal_align(paperType_label, GridData.HORIZONTAL_ALIGN_CENTER);
				paperType_label.setText("报纸类型:");
			}

			{
				paperType_combo = new Combo(page, SWT.READ_ONLY);
				horizontal_align(paperType_combo, GridData.HORIZONTAL_ALIGN_FILL);
				paperType_combo.setItems(Lab.newspaperType);
				paperType_combo.select(0);
			}

			{
				newsType_label = new Label(page, SWT.NONE);
				// horizontal_align_fill(newsStype_label);
				horizontal_align(newsType_label, GridData.HORIZONTAL_ALIGN_CENTER);
				newsType_label.setText("\t新闻类型:");
			}

			{
				newsType_combo = new Combo(page, SWT.READ_ONLY);
				horizontal_align(newsType_combo, GridData.HORIZONTAL_ALIGN_FILL);
				newsType_combo.setItems(Lab.newsType);
			}

			{
				reportType_label = new Label(page, SWT.NONE);
				// horizontal_align_fill(newsStype_label);
				horizontal_align(reportType_label, GridData.HORIZONTAL_ALIGN_CENTER);
				reportType_label.setText("\t报道主题:");
			}
			

			{
//				gridData.horizontalSpan = COLUMN - 1;
				reportType_combo = new Combo(page, SWT.READ_ONLY);
				reportType_combo.setLayoutData(new GridData());
//				horizontal_align(reportStype_combo, gridData);
				reportType_combo.setItems(Lab.reportTheme);
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
						int paperIndex = paperType_combo.getSelectionIndex();
						int newsTypeIndex = newsType_combo.getSelectionIndex();
						int reportThemeIndex = reportType_combo.getSelectionIndex();
						int showTypeIndex = showType_combo.getSelectionIndex();
						paperIndex = paperIndex<0?0:paperIndex;
						newsTypeIndex = newsTypeIndex<0?0:newsTypeIndex;
						reportThemeIndex = reportThemeIndex<0?0:reportThemeIndex;
						showTypeIndex = showTypeIndex<0?0:showTypeIndex;
						SearchController.setSearchTag(
								paperIndex,newsTypeIndex,reportThemeIndex,showTypeIndex);
						SearchController.refreshSearchNewsList();
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
			MessageBox messageBox = new MessageBox(newsListComposite.getShell());
			if(Download.trueContent(object)){
				messageBox.setMessage("下载成功!");
			}
			else
				messageBox.setMessage("下载失败！！！ 新闻已下载");
			messageBox.open();

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
