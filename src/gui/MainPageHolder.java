package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import controller.SearchController;
import data.NewsData;
import gui.NewsContentPageHolder.OnBackButtonListener;
import gui.NewsSearchPageHolder.OnClickNewsListener;

public class MainPageHolder extends PageHolder
implements OnClickNewsListener,OnBackButtonListener {

	private StackLayout stacklayout = new StackLayout();

	private NewsSearchPageHolder newsSearchPageHolder;
	private Composite newsSearchPage;

	private NewsContentPageHolder newsContentPageHolder;
	private Composite newsContentPage;

	@Override
	protected void createContent(Composite parent) {
		this.parent = parent;
		page = new Composite(parent, SWT.BORDER);
		page.setLayout(stacklayout);

		newsSearchPageHolder = new NewsSearchPageHolder();
		newsSearchPageHolder.setOnClickNewsListener(this);
		SearchController.addDataChangeListener(newsSearchPageHolder);
		newsSearchPage = newsSearchPageHolder.getPage(page);

		newsContentPageHolder = new NewsContentPageHolder();
		newsContentPageHolder.setOnBackButtonListener(this);
		newsContentPage = newsContentPageHolder.getPage(page);

		stacklayout.topControl = newsSearchPage;

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
	public void onClickNews(Control control, NewsData object, int position) {
		stacklayout.topControl = newsContentPage;
		newsContentPageHolder.display(object);
		page.layout();
	}

	@Override
	public void onBackButtonClick() {
		stacklayout.topControl = newsSearchPage;
		page.layout();

	}

}
