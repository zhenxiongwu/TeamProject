package gui.page;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.xml.sax.SAXException;

import controller.DeleteController;
import controller.MergeController;
import controller.SearchController;
import data.NewsData;
import data.NewsDataList;
import data.NewsDataParser;
import data.NewsDataPersistence;
import gui.page.NewsContentPageHolder.OnNewsContentButtonListener;
import gui.window.SaveFileDialog;
import proguard.Proguard;

public class ConsistentTestPageHolder extends PageHolder implements OnNewsContentButtonListener {

	private StackLayout stackLayout;

	private Composite outsideComposite;

	private ScrolledComposite scrolledComposite;
	private Composite innerScrolledComposite;

	private Group group_lab_test;
	private Button button_start;
	private Button button_export_test_file;

	private Group group_checkConsistent;
	private Label label_test_file;
	private Button button_open_test_file;
	private Label label_local_test_file;
	private Button button_open_local_test_file;
	private Button button_consistent_test;

	private Group group_import_merge_file;
	private Label label_merge_file;
	private Button button_open_merge_file;
	private Text text_import_merge_password;
	private Button button_grant_merge;

	private Group group_export_merge_file;
	private Text text_export_merge_password;
	private Text text_grant_export_password;
	private Button button_grant_export;

	private NewsContentPageHolder newsContentPageHolder;
	private Composite newsContentPage;

	private String remote_test_file;
	private String local_test_file;
	private String import_merge_file;
	private String export_merge_file;

	private List<NewsData> newsDatas = new ArrayList();

	@Override
	protected void createContent(Composite parent) {
		super.createContent(parent);
		stackLayout = new StackLayout();
		page.setLayout(stackLayout);

		NewsDataParser.setNewsDataList(newsDatas, "test.xml");
		outsideComposite = new Composite(page, SWT.NONE);
		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		fillLayout.marginHeight = 10;
		fillLayout.marginWidth = 5;
		fillLayout.spacing = 50;
		outsideComposite.setLayout(fillLayout);
		/*
		 * outsideComposite.setBackground(
		 * Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		 */

		{
			scrolledComposite = new ScrolledComposite(outsideComposite, SWT.BORDER | SWT.V_SCROLL);

			{
				innerScrolledComposite = new Composite(scrolledComposite, SWT.NONE);

				FillLayout fillLayout1 = new FillLayout(SWT.VERTICAL);
				fillLayout1.marginHeight = 10;
				fillLayout1.marginWidth = 5;
				fillLayout1.spacing = 50;
				innerScrolledComposite.setLayout(fillLayout1);

				{
					group_lab_test = new Group(innerScrolledComposite, SWT.BORDER);
					group_lab_test.setText("标签测试");
					group_lab_test.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));

					group_lab_test.setLayout(new GridLayout(2, false));

					{
						button_start = new Button(group_lab_test, SWT.NONE);
						button_start.setText("贴标签测试");

						GridData gridData = new GridData(
								GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
						button_start.setLayoutData(gridData);
						button_start.addSelectionListener(new SelectionAdapter() {

							@Override
							public void widgetSelected(SelectionEvent arg0) {
								stackLayout.topControl = newsContentPage;
								page.layout();
							}

						});
					}

					{
						button_export_test_file = new Button(group_lab_test, SWT.NONE);
						button_export_test_file.setText("导出测试文件");
						button_export_test_file.addSelectionListener(new SelectionAdapter() {

							SaveFileDialog saveFileDialog = new SaveFileDialog(button_export_test_file.getShell(),
									new String[] { "Test label files(*.tf)" }, new String[] { "*.tf" });

							@Override
							public void widgetSelected(SelectionEvent arg0) {
								remote_test_file = saveFileDialog.open();
								if (remote_test_file != null)
									NewsDataPersistence.createXml(newsDatas, remote_test_file);
							}

						});
					}
				}

				{
					group_checkConsistent = new Group(innerScrolledComposite, SWT.BORDER);
					group_checkConsistent.setText("一致性检验");
					group_checkConsistent
							.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
					GridLayout gridLayout = new GridLayout(4, false);
					// gridLayout.marginTop=30;
					gridLayout.marginBottom = 10;
					group_checkConsistent.setLayout(gridLayout);

					{
						Label label_note = new Label(group_checkConsistent, SWT.NONE);
						label_note.setText("远程测试文件:");
					}

					{
						label_test_file = new Label(group_checkConsistent, SWT.BORDER);
						GridData gridData = new GridData(
								GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
						gridData.horizontalSpan = 2;
						label_test_file.setLayoutData(gridData);
					}

					{
						button_open_test_file = new Button(group_checkConsistent, SWT.NONE);
						button_open_test_file.setText("打开...      ");
						button_open_test_file.addSelectionListener(new SelectionAdapter() {

							@Override
							public void widgetSelected(SelectionEvent arg0) {
								FileDialog fileDialog = new FileDialog(parent.getShell(), SWT.OPEN);
								fileDialog.setFilterNames(new String[]{"Test label files(*.tf)"});
								fileDialog.setFilterExtensions(new String[]{"*.tf"});
								String remote_test_file_tem = fileDialog.open();
								if (remote_test_file_tem != null){
									remote_test_file = remote_test_file_tem;
									label_test_file.setText(remote_test_file);
								}
							}

						});
					}

					{
						Label label_note = new Label(group_checkConsistent, SWT.NONE);
						label_note.setText("本地测试文件:");
					}
					{
						label_local_test_file = new Label(group_checkConsistent, SWT.BORDER);
						GridData gridData = new GridData(
								GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
						gridData.horizontalSpan = 2;
						label_local_test_file.setLayoutData(gridData);
					}

					{
						button_open_local_test_file = new Button(group_checkConsistent, SWT.NONE);
						button_open_local_test_file.setText("打开...      ");
						button_open_local_test_file.addSelectionListener(new SelectionAdapter() {

							@Override
							public void widgetSelected(SelectionEvent arg0) {
								FileDialog fileDialog = new FileDialog(parent.getShell(), SWT.OPEN);
								fileDialog.setFilterNames(new String[]{"Test label files(*.tf)"});
								fileDialog.setFilterExtensions(new String[]{"*.tf"});
								String local_test_file_tem = fileDialog.open();
								if (local_test_file_tem != null){
									local_test_file = local_test_file_tem;
									label_local_test_file.setText(local_test_file);
								}
							}

						});
					}

					{
						button_consistent_test = new Button(group_checkConsistent, SWT.NONE);
						button_consistent_test.setText("一致性检验");
						GridData gridData = new GridData(
								GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_VERTICAL);
						gridData.horizontalSpan = 4;
						button_consistent_test.setLayoutData(gridData);
						button_consistent_test.addSelectionListener(new SelectionAdapter() {

							@Override
							public void widgetSelected(SelectionEvent arg0) {
								MessageBox messageBox = new MessageBox(button_consistent_test.getShell());
								if (remote_test_file == null) {
									messageBox.setMessage("远程测试文件不能为空!!!");
								} else if (local_test_file == null) {
									messageBox.setMessage("本地测试文件不能为空!!!");
								} else {
									try {
										List<NewsData> remote = NewsDataParser.getFromFile(remote_test_file);
										List<NewsData> local = NewsDataParser.getFromFile(local_test_file);
										if (MergeController.Consistency(remote, local)) {
											messageBox.setMessage("一致性通过!");
										} else {
											messageBox.setMessage("一致性不通过!!!");
										}
									} catch (Exception e) {
									}
								}

								messageBox.open();
							}

						});
					}
				}

				{
					group_import_merge_file = new Group(innerScrolledComposite, SWT.BORDER);
					group_import_merge_file.setText("导入可合并文件");
					group_import_merge_file
							.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
					GridLayout gridLayout = new GridLayout(4, false);
					gridLayout.marginBottom = 10;
					group_import_merge_file.setLayout(gridLayout);

					{
						Label label_note = new Label(group_import_merge_file, SWT.NONE);
						label_note.setText("合并文件:");
					}

					{
						label_merge_file = new Label(group_import_merge_file, SWT.BORDER);
						GridData gridData = new GridData(
								GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL | GridData.GRAB_VERTICAL);
						gridData.horizontalSpan = 2;
						label_merge_file.setLayoutData(gridData);
					}
					{
						button_open_merge_file = new Button(group_import_merge_file, SWT.NONE);
						button_open_merge_file.setText("打开...      ");

						button_open_merge_file.addSelectionListener(new SelectionAdapter() {

							@Override
							public void widgetSelected(SelectionEvent arg0) {
								FileDialog fileDialog = new FileDialog(parent.getShell(), SWT.OPEN);
								fileDialog.setFilterNames(new String[]{"Mergable files(*.mf)"});
								fileDialog.setFilterExtensions(new String[]{"*.mf"});
								String import_merge_file_tem = fileDialog.open();
								if (import_merge_file_tem != null){
									import_merge_file = import_merge_file_tem;
									label_merge_file.setText(import_merge_file);
								}
							}

						});
					}
					{
						Label label_note = new Label(group_import_merge_file, SWT.NONE);
						label_note.setText("文件签名:");
					}
					{
						text_import_merge_password = new Text(group_import_merge_file,
								SWT.SINGLE | SWT.PASSWORD | SWT.BORDER);
						GridData gridData = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
						gridData.horizontalSpan = 2;
						text_import_merge_password.setLayoutData(gridData);
					}
					{
						button_grant_merge = new Button(group_import_merge_file, SWT.NONE);
						button_grant_merge.setText("合并...      ");
						GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
						// gridData.horizontalSpan=4;
						button_grant_merge.setLayoutData(gridData);

						button_grant_merge.addSelectionListener(new SelectionAdapter() {

							@Override
							public void widgetSelected(SelectionEvent arg0) {
								MessageBox messageBox = new MessageBox(button_grant_merge.getShell());
								String password = text_import_merge_password.getText();
								if (import_merge_file == null) {
									messageBox.setMessage("合并文件不能为空!!!");
								} else if (password == null || password.equals("")) {
									messageBox.setMessage("文件签名不能为空!!!");
								} else {
									String temporalFile = "tem.xml";
									Proguard.EncryptOrDecrypt(import_merge_file, temporalFile, password);

									try {
										MergeController.Merge(temporalFile);
										SearchController.notifysAll();
										DeleteController.notifysAll();
										messageBox.setMessage("合并成功!");
									} catch (SAXException e) {
										messageBox.setMessage("签名密码错误!!!");
									}
									File file = new File(temporalFile);
									if (file.exists())
										file.delete();
								}
								messageBox.open();
							}

						});
					}
				}

				{
					group_export_merge_file = new Group(innerScrolledComposite, SWT.BORDER);
					group_export_merge_file.setText("导出可合并文件");
					group_export_merge_file
							.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
					group_export_merge_file.setLayout(new GridLayout(2, false));

					{
						Label label_note = new Label(group_export_merge_file, SWT.NONE);
						label_note.setText("签名密码:");
					}
					{
						text_export_merge_password = new Text(group_export_merge_file,
								SWT.SINGLE | SWT.PASSWORD | SWT.BORDER);
						GridData gridData = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
						text_export_merge_password.setLayoutData(gridData);
					}
					{
						Label label_note = new Label(group_export_merge_file, SWT.NONE);
						label_note.setText("确认密码:");
					}
					{
						text_grant_export_password = new Text(group_export_merge_file,
								SWT.SINGLE | SWT.PASSWORD | SWT.BORDER);
						GridData gridData = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
						text_grant_export_password.setLayoutData(gridData);
					}

					{
						button_grant_export = new Button(group_export_merge_file, SWT.NONE);
						button_grant_export.setText("导出...      ");
						GridData gridData = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
						gridData.horizontalSpan = 2;
						button_grant_export.setLayoutData(gridData);

						button_grant_export.addSelectionListener(new SelectionAdapter() {

							@Override
							public void widgetSelected(SelectionEvent arg0) {
								MessageBox messageBox = new MessageBox(button_grant_export.getShell());
								String password = text_export_merge_password.getText();
								String grant_passwrod = text_grant_export_password.getText();
								if (password == null || password.equals("")) {
									messageBox.setMessage("请输入签名密码!!!");
								} else if (grant_passwrod == null || grant_passwrod.equals("")) {
									messageBox.setMessage("请确认密码!!!");
								} else if (!password.equals(grant_passwrod)) {
									messageBox.setMessage("密码不一致，请重新输入密码!!!");
								} else {
									SaveFileDialog saveFileDialog = new SaveFileDialog(
											button_export_test_file.getShell(), new String[] { "Mergable files(*.mf)" },
											new String[] { "*.mf" });
									export_merge_file = saveFileDialog.open();
									if(export_merge_file==null)return;
									String temporalFile = "tem.xml";
									NewsDataPersistence.createXml(NewsDataList.getNewsDataList(), temporalFile);
									Proguard.EncryptOrDecrypt(temporalFile, export_merge_file, password);
									File file = new File(temporalFile);
									if (file.exists()) {
										file.delete();
									}
									messageBox.setMessage("文件导出成功!");
								}
								messageBox.open();
							}

						});
					}
				}

				// innerScrolledComposite
				// .setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
			}
			scrolledComposite.setContent(innerScrolledComposite);
			scrolledComposite.setMinSize(innerScrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setExpandVertical(true);
			scrolledComposite.setAlwaysShowScrollBars(true);

		}

		{
			newsContentPageHolder = new NewsContentPageHolder();
			newsContentPage = newsContentPageHolder.getPage(page);
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.horizontalSpan = 3;

			newsContentPageHolder.display(newsDatas.get(0), false, true);
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
		if (index == 1)
			hasprev = false;
		newsContentPageHolder.display(newsDatas.get(index - 1), hasprev, hasnext);
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
		if (index == newsDatas.size() - 2)
			hasnext = false;
		newsContentPageHolder.display(newsDatas.get(index + 1), hasprev, hasnext);
		page.layout();

	}

}
