package gui.page;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;


import data.NewsData;

public class NewsListBuilder {

	public interface NewsListListener {
		public void onItemClickDown(int mouse_button, Control control, Menu menu, NewsData object, int position);

		public void onItemClickUp(int mouse_button, Control control,Menu menu, NewsData object, int position);

		public void onItemDoubleClick(int mouse_button, Control control,Menu menu, NewsData object, int position);
	}

	private NewsListListener newsListListener = null;

	private Composite externalComposite;
	private Composite newsListComposite;
	private ScrolledComposite scrolledComposite;
	private Composite innerScrolledComposite;

	private Button button_prev_page;
	private Button button_next_page;
	private Button button_goto;

	private Label label_1;
	private Label label_2;

	private Text text_currunt_page;

	private int currunt_page_num = 1;
	private int total_page_num = 1;
	private static int NEWS_PER_PAGE = 20;
	
	private List<NewsData> current_page_objects = new ArrayList<NewsData>();

	private Composite parent;
	private List<NewsData> objects;

	public NewsListBuilder(Composite parent, List<NewsData> objects) {
		this.parent = parent;
		this.objects = objects;
/*		total_page_num = objects.size() / NEWS_PER_PAGE;
		if (objects.size() % NEWS_PER_PAGE != 0)
			total_page_num++;
		if (objects.size() == 0){
			currunt_page_num = 1;
			total_page_num = 1;
		}*/
		createNewsList(parent);
	}

	public void setNewsListListener(NewsListListener newsListListener) {
		this.newsListListener = newsListListener;
	}

	public Composite getNewsListComposite() {
		return externalComposite;
	}

	private void createNewsList(Composite parent) {
		externalComposite = new Composite(parent, SWT.NONE);
		externalComposite.setLayout(new GridLayout(8, true));

		{
			createScrolledComposite();
		}

		{
			button_prev_page = new Button(externalComposite, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalSpan = 2;
			gridData.horizontalAlignment = GridData.END;
			button_prev_page.setLayoutData(gridData);
			button_prev_page.setText("　上一页　");
			button_prev_page.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					currunt_page_num--;
					refresh();
				}
			});
		}

		{
			label_1 = new Label(externalComposite, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalAlignment = GridData.END;
			label_1.setLayoutData(gridData);
			label_1.setText("第");
		}

		{
			text_currunt_page = new Text(externalComposite, SWT.BORDER | SWT.CENTER);
			text_currunt_page.setTextLimit(8);
			text_currunt_page.setText(String.format("%d", currunt_page_num));
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			text_currunt_page.setLayoutData(gridData);
		}

		{
			label_2 = new Label(externalComposite, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalAlignment = GridData.BEGINNING;
			label_2.setLayoutData(gridData);
			label_2.setText(String.format("页\t共%d页", total_page_num));
		}

		{
			button_goto = new Button(externalComposite, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalAlignment = GridData.BEGINNING;
			button_goto.setLayoutData(gridData);
			button_goto.setText("跳转");
			button_goto.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					String text_current_page = text_currunt_page.getText();
					int current_p=-1;
					try{current_p = Integer.parseInt(text_current_page);}
					catch(Exception e){
						
					}
					if(current_p!=-1 && current_p<=total_page_num){
						currunt_page_num=current_p;
						refresh();
					}
				}
				
			});
		}

		{
			button_next_page = new Button(externalComposite, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalSpan = 2;
			gridData.horizontalAlignment = GridData.BEGINNING;
			button_next_page.setLayoutData(gridData);
			button_next_page.setText("　下一页　");
			button_next_page.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					currunt_page_num++;
					refresh();
				}
				
			});;
		}

		refresh();
	}

	private void createScrolledComposite() {
		newsListComposite = new Composite(externalComposite, SWT.NONE);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 10;
		newsListComposite.setLayoutData(gridData);

		newsListComposite.setLayout(new FillLayout());

		{
			scrolledComposite = new ScrolledComposite(newsListComposite, SWT.BORDER | SWT.V_SCROLL);

			{
				innerScrolledComposite = new Composite(scrolledComposite, SWT.NONE);

				innerScrolledComposite.setLayout(new GridLayout());

				for (int position = 0;position<NEWS_PER_PAGE;position++) {
					
					addNewsItem(position);

				}
				innerScrolledComposite.layout();
				innerScrolledComposite
						.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
			}
			scrolledComposite.setContent(innerScrolledComposite);
			scrolledComposite.setMinSize(innerScrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setExpandVertical(true);
			scrolledComposite.setAlwaysShowScrollBars(true);

		}

	}
	
	private class NewsItemHolder{
		public Group group;
		public Label label;
		public GridData gridData;
		public int position;
		public NewsItemHolder(Group group, Label label,GridData gridData, int position) {
			this.group = group;
			this.label = label;
			this.gridData = gridData;
			this.position = position;
		}
	}
	
	private List<NewsItemHolder> newsItemHolders = new ArrayList<NewsItemHolder>();
	

	private void addNewsItem(int position) {
		Group group = new Group(innerScrolledComposite, SWT.NONE);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		group.setLayoutData(gridData);
		group.setLayout(new FillLayout());

		Label label_title = new Label(group, SWT.NONE);
		Menu menu = new Menu(label_title);
//		MenuItem menuItem = new MenuItem(menu,SWT.NONE);
		label_title.setMenu(menu);
//		label_title.setMenu(new Menu(label_title));

		// text_title.setEnabled(false);

		label_title.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent arg0) {
				if (newsListListener != null){
					NewsData newsData = getNewsData(position);

					newsListListener.onItemClickUp(arg0.button, label_title, menu, newsData, position);
					label_title.setMenu(menu);
//					newsListListener.onItemClickUp(arg0.button,label_title,newsData,position);
					group.layout();
				}
			} 

			@Override
			public void mouseDown(MouseEvent arg0) {

				innerScrolledComposite.setFocus();
				
				if (newsListListener != null){
					NewsData newsData = getNewsData(position);
					newsListListener.onItemClickDown(arg0.button, label_title, menu, newsData, position);
					group.layout();
				}
					
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				if (newsListListener != null)
					newsListListener.onItemDoubleClick(arg0.button, label_title, menu, getNewsData(position), position);
			}

		});

		label_title.addMouseTrackListener(new MouseTrackListener() {

			@Override
			public void mouseHover(MouseEvent arg0) {

			}

			@Override
			public void mouseExit(MouseEvent arg0) {
				// news.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_INFO_BACKGROUND));

				label_title.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
			}

			@Override
			public void mouseEnter(MouseEvent arg0) {
				// news.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_LIST_SELECTION));

				label_title.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_INFO_BACKGROUND));

			}
		});
		
		newsItemHolders.add(new NewsItemHolder(group,label_title,gridData,position));
		
	}
	
	private void refreshCurrentPageObjects(){
		current_page_objects.clear();
		if(currunt_page_num !=0){
			for(int i =(currunt_page_num-1)*NEWS_PER_PAGE, j=0;i<objects.size()&& j<NEWS_PER_PAGE;i++,j++){

					current_page_objects.add(objects.get(i));

			}
		}
	}
	
	private NewsData getNewsData(int item_position){
		return current_page_objects.get(item_position);
	}
	

	private void setData(){
		int position = 0;
		
		for(NewsData newsData : current_page_objects){
			NewsItemHolder newsItemHolder = newsItemHolders.get(position);
			newsItemHolder.gridData.exclude = false;
			newsItemHolder.group.setVisible(true);
			newsItemHolder.label.getMenu().setEnabled(true);
			newsItemHolder.group.setText(newsData.getDate());
			newsItemHolder.label.setText("\n\t"+newsData.getTitle()+"\n\n");
			
			position++;
		}
		
		for(;position<NEWS_PER_PAGE;position++){
			NewsItemHolder newsItemHolder = newsItemHolders.get(position);
			newsItemHolder.gridData.exclude = true;
			newsItemHolder.group.setVisible(false);
			newsItemHolder.label.getMenu().setEnabled(false);
		}
		innerScrolledComposite.layout();
		scrolledComposite.setMinSize(innerScrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.layout();
		externalComposite.layout();
	}

	public void refresh() {

		refreshCurrentPageObjects();
		setData();
		
		total_page_num = objects.size() / NEWS_PER_PAGE;
		if (objects.size() % NEWS_PER_PAGE != 0)
			total_page_num++;
		if (objects.size() == 0){
			currunt_page_num = 1;
			total_page_num = 1;
		}

		label_2.setText(String.format("页\t共%d页", total_page_num));

		if (currunt_page_num == 1) {
			button_prev_page.setEnabled(false);
		} else
			button_prev_page.setEnabled(true);

		if (currunt_page_num == total_page_num) {
			button_next_page.setEnabled(false);
		} else
			button_next_page.setEnabled(true);
		
		innerScrolledComposite.setFocus();

		text_currunt_page.setText(String.valueOf(currunt_page_num));
	}

}