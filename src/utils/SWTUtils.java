package utils;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;

public class SWTUtils {

	private SWTUtils(){}
	
	public static void setCenter(Shell shell){
		
		Rectangle rtg = shell.getMonitor().getClientArea();
		int width = rtg.width;
		int height = rtg.height;
		
		int x = shell.getSize().x;
		int y = shell.getSize().y;
		
		Point point = new Point((width-x)/2,(height-y)/2);
		shell.setLocation(point);
	}
	
	public static void fullScreen(Shell shell){
		Rectangle rtg = shell.getMonitor().getClientArea();
		shell.setBounds(rtg);
		shell.setMaximized(false);
	}
}
