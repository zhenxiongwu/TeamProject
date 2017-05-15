package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import controller.SearchController;
import data.NewsData;
import gui.NewsContentPageHolder.OnNewsContentButtonListener;
import gui.NewsSearchPageHolder.OnClickNewsListener;

public class MainPageHolder extends PageHolder
implements OnClickNewsListener,OnNewsContentButtonListener {

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
		newsContentPageHolder.setOnNewsContentButtonListener(this);
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
		boolean hasprev = true;
		boolean hasnext = true;
		if(SearchController.getSearchNewsList().indexOf(object)==0){
			hasprev = false;
		}
		if(SearchController.getSearchNewsList().indexOf(object)==
				SearchController.getSearchNewsList().size()-1)
			hasnext = false;
		newsContentPageHolder.display(object,hasprev,hasnext);
		page.layout();
	}

	@Override
	public void onBackButtonClick(NewsData object) {
		stacklayout.topControl = newsSearchPage;
		page.layout();

	}

	@Override
	public void onPrevNewsButtonClick(NewsData object) {
		int index = SearchController.getSearchNewsList().indexOf(object);
		boolean hasprev = true;
		boolean hasnext = true;
		if(index==1)
			hasprev = false;
		newsContentPageHolder.display(
				SearchController.getSearchNewsList().get(index-1),hasprev,hasnext);
		page.layout();
		
	}

	@Override
	public void onNextNewsButtonClick(NewsData object) {
		int index = SearchController.getSearchNewsList().indexOf(object);
		boolean hasprev = true;
		boolean hasnext = true;
		if(index==SearchController.getSearchNewsList().size()-2)
			hasnext = false;
		newsContentPageHolder.display(
				SearchController.getSearchNewsList().get(index+1),hasprev,hasnext);
		page.layout();
		
	}

}
