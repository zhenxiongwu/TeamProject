package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public abstract class PageHolder {
	
	protected Composite page;
	protected Composite parent;
	
	public Composite getPage(Composite parent){
		createContent(parent);
		return page;
	}
	
	protected void createContent(Composite parent){
		page = new Composite(parent,SWT.NONE);
	}
	
	public abstract void refresh();
	
	public abstract void destory();

	
}
