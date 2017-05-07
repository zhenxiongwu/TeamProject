package gui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import gui.NewsListBuilder.NewsListListener;

public class RecyclePageHolder extends PageHolder implements NewsListListener{

	NewsListBuilder newsListBuilder;
	
	@Override
	protected void createContent(Composite parent) {
		this.parent = parent;
		java.util.List<Object> objects = new ArrayList();
		objects.add("关注留守儿童");
		
		newsListBuilder = new NewsListBuilder(parent,objects);
		newsListBuilder.setNewsListListener(this);
		page = newsListBuilder.getNewsListComposite();
	}

	@Override
	public void refresh() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void destory() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void onItemClickDown(int mouse_button, Control control, Object object, int position) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void onItemClickUp(int mouse_button, Control control, Object object, int position) {
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
	public void onItemDoubleClick(int mouse_button, Control control, Object object, int position) {
		// TODO 自动生成的方法存根
		
	}

}
