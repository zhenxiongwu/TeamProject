package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.swtchart.internal.Grid;

import data.NewsData;
import data.NewsDataParser;
import gui.NewsContentPageHolder.OnNewsContentButtonListener;

public class ConsistentTestPageHolder extends PageHolder implements OnNewsContentButtonListener{

	private StackLayout stackLayout;
	
	private Composite outsideComposite;
	
	private Group group_checkConsistent;
	private Label label_test_file;
	private Button button_open_test_file;
	private Button button_start;
	private Button button_export;
	private Button button_consistent_test;
	
	private Group group_mergeFile;
	private Label label_merge_file;
	private Button button_open_merge_file;
	private Button button_grant_merge;

	private NewsContentPageHolder newsContentPageHolder;
	private Composite newsContentPage;
	
	private String test_file;
	private String merge_file;

	private List<NewsData> newsDatas = new ArrayList();

	@Override
	protected void createContent(Composite parent) {
		super.createContent(parent);
		stackLayout = new StackLayout();
		page.setLayout(stackLayout);
		
		NewsDataParser.setNewsDataList(newsDatas, "test.xml");
		outsideComposite = new Composite(page,SWT.NONE);
		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		fillLayout.marginHeight=10;
		fillLayout.marginWidth=5;
		fillLayout.spacing=50;
		outsideComposite.setLayout(fillLayout);
		/*outsideComposite.setBackground(
				Display.getDefault().getSystemColor(SWT.COLOR_BLACK));*/
		
		{
			group_checkConsistent = new Group(outsideComposite,SWT.BORDER);
			group_checkConsistent.setText("一致性测试");
			GridLayout gridLayout = new GridLayout(4,false);
//			gridLayout.marginTop=30;
			gridLayout.marginBottom=10;
			group_checkConsistent.setLayout(gridLayout);
			
			{
				Label label_note = new Label(group_checkConsistent,SWT.NONE);
				label_note.setText("测试文件:");
			}
			
			{
				label_test_file = new Label(group_checkConsistent,SWT.BORDER);
				GridData gridData = new GridData(GridData.FILL_HORIZONTAL
						|GridData.GRAB_HORIZONTAL|GridData.GRAB_VERTICAL);
				gridData.horizontalSpan=2;
				label_test_file.setLayoutData(gridData);
			}
			
			{
				button_open_test_file = new Button(group_checkConsistent,SWT.NONE);
				button_open_test_file.setText("打开...      ");
				button_open_test_file.addSelectionListener(new SelectionAdapter() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						FileDialog fileDialog = new FileDialog(parent.getShell(),SWT.OPEN);
						test_file = fileDialog.open();
						if(test_file!=null)
							label_test_file.setText(test_file);
					}

				});
			}
			
			{
				button_start = new Button(group_checkConsistent,SWT.NONE);
				button_start.setText("贴标签测试");
				
				GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END
						|GridData.GRAB_HORIZONTAL);
				gridData.horizontalSpan=2;
				button_start.setLayoutData(gridData);
				button_start.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent arg0) {
						stackLayout.topControl=newsContentPage;
						page.layout();
					}
					
					
				});
			}

			{
				button_export = new Button(group_checkConsistent, SWT.NONE);
				button_export.setText("导出测试文件");
			}
			
			{
				button_consistent_test = new Button(group_checkConsistent,SWT.NONE);
				button_consistent_test.setText("一致性检验");
			}
		}
		
		{
			group_mergeFile = new Group(outsideComposite,SWT.BORDER);
			group_mergeFile.setText("合并标签");
			GridLayout gridLayout = new GridLayout(4,false);
			gridLayout.marginBottom=10;
			group_mergeFile.setLayout(gridLayout);
			
			{
				Label label_note = new Label(group_mergeFile,SWT.NONE);
				label_note.setText("合并文件:");
			}
			
			{
				label_merge_file = new Label(group_mergeFile,SWT.BORDER);
				GridData gridData = new GridData(GridData.GRAB_HORIZONTAL
						|GridData.FILL_HORIZONTAL|GridData.GRAB_VERTICAL);
				gridData.horizontalSpan=2;
				label_merge_file.setLayoutData(gridData);
			}
			{
				button_open_merge_file = new Button(group_mergeFile,SWT.NONE);
				button_open_merge_file.setText("打开...      ");
				
				button_open_merge_file.addSelectionListener(new SelectionAdapter() {		
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						FileDialog fileDialog = new FileDialog(parent.getShell(),SWT.OPEN);
						merge_file = fileDialog.open();
						if(merge_file!=null)
							label_merge_file.setText(merge_file);
					}

				});
			}
			{
				button_grant_merge = new Button(group_mergeFile,SWT.NONE);
				button_grant_merge.setText("确认合并");
				GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
				gridData.horizontalSpan=4;
				button_grant_merge.setLayoutData(gridData);
				
				button_grant_merge.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent arg0) {
						// TODO 自动生成的方法存根
						super.widgetSelected(arg0);
					}
					
				});
			}
		}
		

		{
			newsContentPageHolder = new NewsContentPageHolder();
			newsContentPage = newsContentPageHolder.getPage(page);
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.horizontalSpan = 3;
			
			newsContentPageHolder.display(newsDatas.get(0),false,true);
			newsContentPageHolder.setOnNewsContentButtonListener(this);
		}
		
		stackLayout.topControl = outsideComposite;

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
	public void onPrevNewsButtonClick(NewsData object) {
		int index = newsDatas.indexOf(object);
		boolean hasprev = true;
		boolean hasnext = true;
		if(index==1)
			hasprev = false;
		newsContentPageHolder.display(
				newsDatas.get(index-1),hasprev,hasnext);
		page.layout();
	}

	@Override
	public void onBackButtonClick(NewsData object) {
		stackLayout.topControl = outsideComposite;
		page.layout();
	}

	@Override
	public void onNextNewsButtonClick(NewsData object) {
		int index = newsDatas.indexOf(object);
		boolean hasprev = true;
		boolean hasnext = true;
		if(index==newsDatas.size()-2)
			hasnext = false;
		newsContentPageHolder.display(
				newsDatas.get(index+1),hasprev,hasnext);
		page.layout();
		
	}

}
