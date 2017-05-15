package gui.utils;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.Chart;
import org.swtchart.IBarSeries;
import org.swtchart.ISeries.SeriesType;


import data.NewsDataList;

//柱状图实例
public class BarChart {

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     */
    
    
   /* public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Bar Chart");
        shell.setSize(500, 400);
        shell.setLayout(new FillLayout());

        createChart(shell);

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
*/
    /**
     * create the chart.
     * 
     * @param parent
     *            The parent composite
     * @return The created chart
     */
    static public Chart createChart(Composite parent) {

        // create a chart
        Chart chart = new Chart(parent, SWT.NONE);

        // set titles
        chart.getTitle().setText("Bar Chart");
        chart.getAxisSet().getXAxis(0).getTitle().setText("报道主题");
        chart.getAxisSet().getYAxis(0).getTitle().setText("数量");

        // create bar series
        chart.getAxisSet().getXAxis(0).enableCategory(true);
        chart.getAxisSet().getXAxis(0).setCategorySeries(
                new String[] { "变量4第四类", "变量4第五类","变量4第七类" });
        
        
        IBarSeries barSeries1 = (IBarSeries) chart.getSeriesSet().createSeries(
                SeriesType.BAR, "男性");
        barSeries1.setYSeries(getYSeriesSex0());
        barSeries1.setBarColor(Display.getDefault().getSystemColor(
                        SWT.COLOR_RED));

        IBarSeries barSeries2 = (IBarSeries) chart.getSeriesSet().createSeries(
                SeriesType.BAR, "女性");
        barSeries2.setYSeries(getYSeriesSex1());
        barSeries1.setBarColor(Display.getDefault().getSystemColor(
                SWT.COLOR_YELLOW));
        
        
        // adjust the axis range
        chart.getAxisSet().adjustRange();

        return chart;
    }
    
    public static double[] getYSeriesSex0(){
    	Map<Object, Object> data = NewsDataList.getStatisticsData();
    	double[] resultData = {(double) data.get("var4Type4Sex0"),
    			(double)data.get("var4Type5Sex0"),
    			(double)data.get("var4Type7Sex0")};
    	return resultData;
    }
    
    public static double[] getYSeriesSex1(){
    	Map<Object, Object> data = NewsDataList.getStatisticsData();
    	double[] resultData = {(double) data.get("var4Type4Sex1"),
    			(double)data.get("var4Type5Sex1"),
    			(double)data.get("var4Type7Sex1")};
    	return resultData;
    }
}