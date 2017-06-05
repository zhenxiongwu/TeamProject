package gui.page;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.sun.org.apache.bcel.internal.generic.NEW;

import controller.DeleteController;
import data.NewsData;
import gui.page.NewsListBuilder.NewsListListener;
import sun.rmi.runtime.Log;

public class RecyclePageHolder extends PageHolder implements NewsListListener, DeleteController.DataChangeListener {

	NewsListBuilder newsListBuilder;

	@Override
	protected void createContent(Composite parent) {
		this.parent = parent;

		page = new Composite(parent, SWT.NONE);
		page.setLayout(new FillLayout());

		newsListBuilder = new NewsListBuilder(page, DeleteController.getRecycleNewsList());
		newsListBuilder.setNewsListListener(this);
		newsListBuilder.getNewsListComposite();
	}

	@Override
	public void refresh() {

		Logger logger = Logger.getLogger("zhenxiongwu");
		logger.info("recyclePage refresh");
		newsListBuilder.refresh();
		page.layout();
	}

	@Override
	public void destory() {
		// TODO 自动生成的方法存根

	}
	
	class RecoverListener implements Listener{
		
		NewsData newsData;

		@Override
		public void handleEvent(Event arg0) {
			Logger logger = Logger.getLogger("zhenxiongwu");
			logger.info("recoverListener handleEvent");
			DeleteController.recoverRecycleNews(newsData);
			
		}
		
		public void resetNewsData(NewsData newsData){
			this.newsData = newsData;
		}
		
	}

	@Override
	public void onItemClickDown(int mouse_button, Control control, Menu menu, NewsData object, int position) {
		// TODO 自动生成的方法存根

	}
	

	RecoverListener recoverListener = new RecoverListener();
	

	@Override
	public void onItemClickUp(int mouse_button, Control control, Menu menu, NewsData object, int position) {
		
		
		
		if (mouse_button == 3) {
			// Menu menu = new Menu(control);
			if (menu.getItemCount() == 0) {
				MenuItem menuItem = new MenuItem(menu, SWT.PUSH);
				menuItem.setText("恢复(Recover)");
				menuItem.addListener(SWT.Selection,recoverListener);
				
			}
			recoverListener.resetNewsData(object);
		}

	}

	@Override
	public void onItemDoubleClick(int mouse_button, Control control, Menu menu, NewsData object, int position) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void recycleNewsChange() {
		Logger logger = Logger.getLogger("zhenxiongwu");
		logger.info("recycleNewschange");
		refresh();
	}

}
