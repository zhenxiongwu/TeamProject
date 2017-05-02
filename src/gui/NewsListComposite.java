package gui;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class NewsListComposite {

	private NewsListComposite() {
	}

	public static Composite createNewsComposite(Composite parent, Object layoutData, List<Object> objects) {
		Composite composite_parent = new Composite(parent, SWT.BORDER);
		composite_parent.setLayoutData(layoutData);
		composite_parent.setLayout(new FillLayout());
		ScrolledComposite scrolledComposite = new ScrolledComposite(composite_parent, SWT.BORDER | SWT.V_SCROLL);

		Composite composite = new Composite(scrolledComposite, SWT.NONE);

		composite.setLayout(new GridLayout());

		for (Object object : objects) {
			Logger logger = Logger.getLogger("zhenxiongwu");
			logger.info("befor create group");
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			addNews(composite, gridData, object);
			logger.info("after create group");
		}

		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setAlwaysShowScrollBars(true);
		return composite_parent;

	}

	private static Group addNews(Composite parent, GridData gridData, Object object) {
		Group news = new Group(parent, SWT.NONE);
		news.setLayoutData(gridData);
		news.setText((String) object);
		news.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				Logger logger = Logger.getLogger("zhenxiongwu");
				logger.info("double click");
				NewsContentHolder newsContentHolder = NewsContentHolder.getInstance(null);
				newsContentHolder.refresh(object);
				Window.stackLayout.topControl=newsContentHolder.getComposite();
				Window.rightComposite.layout();
				
			}
		});
		return news;
	}
}
