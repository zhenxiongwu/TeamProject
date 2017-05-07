package gui;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import data.NewsData;

public class NewsListBuilder {

	public interface NewsListListener {
		public void onItemClickDown(int mouse_button, Control control, NewsData object, int position);

		public void onItemClickUp(int mouse_button, Control control, NewsData object, int position);

		public void onItemDoubleClick(int mouse_button, Control control, NewsData object, int position);
	}

	private Composite newsListComposite;
	private NewsListListener newsListListener = null;
	
	private Composite parent;
	private List<NewsData> objects;

	public NewsListBuilder(Composite parent, List<NewsData> objects) {
		this.parent = parent;
		this.objects = objects;
		createNewsList(parent, objects);
	}

	public void setNewsListListener(NewsListListener newsListListener) {
		this.newsListListener = newsListListener;
	}

	public Composite getNewsListComposite() {
		return newsListComposite;
	}

	private void createNewsList(Composite parent, List<NewsData> objects) {
		newsListComposite = new Composite(parent, SWT.NONE);
		newsListComposite.setLayout(new FillLayout());
		ScrolledComposite scrolledComposite = new ScrolledComposite(newsListComposite, SWT.BORDER | SWT.V_SCROLL);

		Composite composite = new Composite(scrolledComposite, SWT.NONE);

		composite.setLayout(new GridLayout());

		int position = 0;
		for (NewsData object : objects) {
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			addNews(composite, gridData, object, position);
			position++;
		}

		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setAlwaysShowScrollBars(true);

	}

	private Group addNews(Composite parent, GridData gridData, NewsData object, int position) {
		Group news = new Group(parent, SWT.NONE);
		news.setLayoutData(gridData);
		news.setLayout(new FillLayout());
		news.setText(object.getDate());
		
		Label text_title = new Label(news,SWT.NONE);
		
		text_title.setText("\n\t"+object.getTitle()+"\n\n");
		
		text_title.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent arg0) {
				if (newsListListener != null)
					newsListListener.onItemClickUp(arg0.button, text_title, object, position);
			}

			@Override
			public void mouseDown(MouseEvent arg0) {
				if (newsListListener != null)
					newsListListener.onItemClickDown(arg0.button, text_title, object, position);

			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				if (newsListListener != null)
					newsListListener.onItemDoubleClick(arg0.button, text_title, object, position);
			}
			

		});
		
		text_title.addMouseTrackListener(new MouseTrackListener() {
			
			@Override
			public void mouseHover(MouseEvent arg0) {
				
				
			}
			
			@Override
			public void mouseExit(MouseEvent arg0) {
//				news.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_INFO_BACKGROUND));

				text_title.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
			}
			
			@Override
			public void mouseEnter(MouseEvent arg0) {
//				news.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_LIST_SELECTION));

				text_title.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
				
			}
		});
		return news;
	}
	
	public Composite refresh(){
		Logger logger = Logger.getLogger("zhenxiognwu");
		logger.info("objects size: "+objects.size());
		newsListComposite.dispose();
		createNewsList(parent,objects);
		newsListComposite.layout();
		return newsListComposite;
	}
}
