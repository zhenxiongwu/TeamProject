package gui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import controller.DeleteController;
import data.NewsData;
import gui.NewsListBuilder.NewsListListener;

public class RecyclePageHolder extends PageHolder implements NewsListListener{

	NewsListBuilder newsListBuilder;
	
	@Override
	protected void createContent(Composite parent) {
		this.parent = parent;
		
		page = new Composite(parent,SWT.NONE);
		page.setLayout(new FillLayout());
		
		newsListBuilder = new NewsListBuilder(page,DeleteController.getRecycleNewsList());
		newsListBuilder.setNewsListListener(this);
		newsListBuilder.getNewsListComposite();
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

	@Override
	public void onItemClickDown(int mouse_button, Control control, NewsData object, int position) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void onItemClickUp(int mouse_button, Control control, NewsData object, int position) {
		if(mouse_button == 3){
			Menu menu = new Menu(control);
			MenuItem menuItem = new MenuItem(menu, SWT.PUSH);
			menuItem.setText("恢复(Recover)");
			menuItem.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					// TODO 自动生成的方法存根
					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO 自动生成的方法存根
					
				}
			});
			control.setMenu(menu);
		}
		
	}

	@Override
	public void onItemDoubleClick(int mouse_button, Control control, NewsData object, int position) {
		// TODO 自动生成的方法存根
		
	}

}
