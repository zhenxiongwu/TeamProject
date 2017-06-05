package gui.window;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class Window {

	protected static Display display = Display.getDefault();

	protected Shell shell;

	public void open() {
		shell = new Shell(display, SWT.CLOSE | SWT.MIN | SWT.MAX | SWT.RESIZE);
		createContents(shell);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		dispose();
	}

	protected void dispose() {
		shell.dispose();
	}

	abstract void createContents(Shell shell);

}
