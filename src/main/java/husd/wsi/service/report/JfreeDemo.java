package husd.wsi.service.report;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by shengdong on 2015/1/18.
 */
public class JfreeDemo {

    public static void main(String [] args) throws IOException {
        DefaultPieDataset dpd = new DefaultPieDataset();
        dpd.setValue("管理人员", 25);  //输入数据
        dpd.setValue("市场人员", 25);
        dpd.setValue("开发人员", 45);
        dpd.setValue("其他人员", 10);

        JFreeChart chart= ChartFactory.createPieChart("某公司人员组织数据图", dpd, true, true, false);
        //可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL

        ChartFrame chartFrame=new ChartFrame("某公司人员组织数据图",chart);
        //chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
        chartFrame.pack(); //以合适的大小展现图形
        chartFrame.setVisible(true);//图形是否可见
        OutputStream os = new FileOutputStream("D:\\1.jpeg");
        ChartUtilities.writeChartAsJPEG(os,chart,1000,800);
        os.close();
    }
}
