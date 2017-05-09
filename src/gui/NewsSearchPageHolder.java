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

import constant.Lab;
import controller.DeleteController;
import controller.SearchController;
import data.NewsData;
import gui.NewsListBuilder.NewsListListener;

public class NewsSearchPageHolder extends PageHolder
implements NewsListListener,SearchController.DataChangeListener{
	
	
	private final int COLUMN = 7;
	
	private Label paperStype_label;
	private Combo paperStype_combo;
	
	private Label newsStype_label;
	private Combo newsStype_combo;
	
	private Label reportStype_label;
	private Combo reportStype_combo;
	
	private Button search_button;
	
	private Composite newsListComposite;
	
	private NewsListBuilder newsListBuilder;
	
	@Override
	protected void createContent(Composite parent) {
		this.parent = parent;
		page = new Composite(parent,SWT.NONE);
		
		page.setLayout(new GridLayout(COLUMN, true));

		{
			paperStype_label = new Label(page, SWT.NONE);
			horizontal_align(paperStype_label, GridData.HORIZONTAL_ALIGN_CENTER);
			paperStype_label.setText("报纸类型:");

			paperStype_combo = new Combo(page, SWT.READ_ONLY);
			horizontal_align(paperStype_combo, GridData.HORIZONTAL_ALIGN_FILL);
			paperStype_combo.setItems(Lab.newspaperType);
			paperStype_combo.select(0);

			newsStype_label = new Label(page, SWT.NONE);
			// horizontal_align_fill(newsStype_label);
			horizontal_align(newsStype_label, GridData.HORIZONTAL_ALIGN_CENTER);
			newsStype_label.setText("新闻类型:");

			newsStype_combo = new Combo(page, SWT.READ_ONLY);
			horizontal_align(newsStype_combo, GridData.HORIZONTAL_ALIGN_FILL);
			newsStype_combo.setItems(Lab.newsType);

			reportStype_label = new Label(page, SWT.NONE);
			// horizontal_align_fill(newsStype_label);
			horizontal_align(reportStype_label, GridData.HORIZONTAL_ALIGN_CENTER);
			reportStype_label.setText("报道主题:");

			reportStype_combo = new Combo(page, SWT.READ_ONLY);
			horizontal_align(reportStype_combo, GridData.HORIZONTAL_ALIGN_FILL);
			reportStype_combo.setItems(Lab.reportTheme);

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

		{
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.horizontalSpan = COLUMN;

			newsListBuilder = new NewsListBuilder(page,SearchController.getSearchNewsList());
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
//		page.layout();
	}

	@Override
	public void destory() {
		// TODO 自动生成的方法存根

	}

	
	public static interface OnClickNewsListener{
		public void onClickNews(Control control, NewsData object,int position);
	}
	
	private OnClickNewsListener onClickNewsListener;
	
	public void setOnClickNewsListener(OnClickNewsListener onClickNewsListener){
		this.onClickNewsListener = onClickNewsListener;
	}

	
	@Override
	public void onItemClickDown(int mouse_button, Control control, NewsData object, int position) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void onItemClickUp(int mouse_button, Control control, NewsData object, int position) {

		Logger logger = Logger.getLogger("zhenxiongwu");
		
		if(mouse_button == 3){
			Menu menu = new Menu(control);
			MenuItem menuItemDownload = new MenuItem(menu,SWT.POP_UP);
			menuItemDownload.setText("下载(Download)　　");
			menuItemDownload.addListener(SWT.Selection,new Listener() {
				
				@Override
				public void handleEvent(Event arg0) {
					// TODO 自动生成的方法存根
					menu.dispose();
				}
			});
			MenuItem menuItemDelete = new MenuItem(menu,SWT.POP_UP);
			menuItemDelete.setText("删除(Delete)");
			menuItemDelete.addListener(SWT.Selection,new Listener() {
				
				@Override
				public void handleEvent(Event arg0) {
					
					DeleteController.addRecycleNews(object);
					logger.info(object.getTitle());
					menu.dispose();
				}
			});
			control.setMenu(menu);
		}
	}

	@Override
	public void onItemDoubleClick(int mouse_button, Control control, NewsData object, int position) {
		if(onClickNewsListener != null && mouse_button == 1){
			onClickNewsListener.onClickNews(control,object,position);
		}
		
	}

	@Override
	public void searchNewsChange() {
		refresh();
	}

}
