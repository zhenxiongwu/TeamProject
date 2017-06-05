package gui.page;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;


public class MergePageHolder extends PageHolder {

	private Label label_selected_file;
	private Button button_select_file;
	private Button button_merge;
	private String merge_file;
	

	@Override
	protected void createContent(Composite parent) {
		super.createContent(parent);
		page.setLayout(new GridLayout(6, false));

		{
			label_selected_file = new Label(page,SWT.BORDER);
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.horizontalSpan = 4;
			label_selected_file.setLayoutData(gridData);
			
		}
		
		{
			button_select_file = new Button(page, SWT.NONE);
			GridData gridData = new GridData();
			gridData.horizontalSpan = 2;
			button_select_file.setLayoutData(gridData);
			
			button_select_file.setText("选择合并文件");
			button_select_file.addSelectionListener(new SelectionAdapter() {
				
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					FileDialog fileDialog = new FileDialog(parent.getShell(),SWT.OPEN);
					merge_file = fileDialog.open();
					if(merge_file!=null)
						label_selected_file.setText(merge_file);
				}

			});
		}
		
		
		{
			button_merge = new Button(page,SWT.NONE);
			GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
			gridData.horizontalSpan=6;
			button_merge.setLayoutData(gridData);
			button_merge.setText("确认合并");
			
			button_merge.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					// TODO 自动生成的方法存根
					super.widgetSelected(arg0);
				}
				
				
			});
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
