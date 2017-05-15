package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import constant.Lab;
import gui.utils.BarChart;


public class StatisticsPageHolder extends PageHolder{

	private TabFolder tabFolder;

	private TabItem tabItem_cases;
	private Composite composite_cases;
	private Label label_reportTheme_cases;
	private Combo combo_reportTheme_cases;
	private Group group_cases;
	private Group group_percent;

	private TabItem tabItem_tendency;
	private Composite composite_tendency;
	private Label label_reportTheme_tendency;
	private Combo combo_reportTheme_tendency;
	private Group group_tendency;

	

	@Override
	protected void createContent(Composite parent) {

		this.parent = parent;
		page = new Composite(parent, SWT.NONE);
		page.setLayout(new FillLayout());
		BarChart.createChart(page);
		tabFolder = new TabFolder(page, SWT.NONE);

		{
			tabItem_cases = new TabItem(tabFolder, SWT.NONE);
			tabItem_cases.setText(" 案 例 统 计 ");

			composite_cases = new Composite(tabFolder, SWT.NONE);
			composite_cases.setLayout(new GridLayout(4, false));
			tabItem_cases.setControl(composite_cases);

			{
				label_reportTheme_cases = new Label(composite_cases, SWT.NONE);
				GridData gridData = new GridData();
				gridData.verticalSpan=3;
				gridData.horizontalSpan=1;
				gridData.verticalAlignment= GridData.CENTER;
				label_reportTheme_cases.setLayoutData(gridData);
				label_reportTheme_cases.setText("报道主题：");
			}
			
			{
				combo_reportTheme_cases = new Combo(composite_cases,SWT.READ_ONLY);
				GridData gridData = new GridData();
				gridData.verticalSpan=3;
				gridData.horizontalSpan=3;
				gridData.verticalAlignment= GridData.CENTER;
				combo_reportTheme_cases.setLayoutData(gridData);
				combo_reportTheme_cases.setItems(Lab.reportTheme_statistics);
			}
			
			{
				group_cases = new Group(composite_cases,SWT.NONE);
				GridData gridData = new GridData(GridData.FILL_BOTH);
				gridData.horizontalSpan=2;
				group_cases.setLayoutData(gridData);
				group_cases.setText("案例统计图");
			}
			
			{
				group_percent = new Group(composite_cases,SWT.NONE);
				GridData gridData = new GridData(GridData.FILL_BOTH);
				gridData.horizontalSpan=2;
				group_percent.setLayoutData(gridData);
				group_percent.setText("比例统计图");
			}

		}

		{
			tabItem_tendency = new TabItem(tabFolder, SWT.NONE);
			tabItem_tendency.setText(" 趋 向 统 计 ");

			composite_tendency = new Composite(tabFolder, SWT.NONE);
			composite_tendency.setLayout(new GridLayout(4, false));
			tabItem_tendency.setControl(composite_tendency);
			
			{
				label_reportTheme_tendency = new Label(composite_tendency, SWT.NONE);
				GridData gridData = new GridData();
				gridData.verticalSpan=3;
				gridData.verticalAlignment= GridData.CENTER;
				label_reportTheme_tendency.setLayoutData(gridData);
				label_reportTheme_tendency.setText("报道主题：");
			}
			
			{
				combo_reportTheme_tendency = new Combo(composite_tendency,SWT.READ_ONLY);
				GridData gridData = new GridData();
				gridData.verticalSpan=3;
				gridData.verticalAlignment= GridData.CENTER;
				combo_reportTheme_tendency.setLayoutData(gridData);
				combo_reportTheme_tendency.setItems(Lab.reportTheme_statistics);
			}
			
			{
				group_tendency = new Group(composite_tendency, SWT.NONE);
				GridData gridData = new GridData(GridData.FILL_BOTH);
				gridData.horizontalSpan=4;
				group_tendency.setLayoutData(gridData);
				group_tendency.setText("趋向统计图");
			}
		}
	}

	@Override
	public void refresh() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void destory() {
		// TODO 自动生成的方法存根
		
	}

}
