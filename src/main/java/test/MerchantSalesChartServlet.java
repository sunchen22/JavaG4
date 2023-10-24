package test;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.grouporder.dao.GroupOrderDAOHibernateImplC;


@WebServlet("/cproject/pages/chart.do")
public class MerchantSalesChartServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("dinerID");
        Integer dinerID = Integer.parseInt(action);
        CategoryDataset dataset = createDataset(dinerID);
        
        
        JFreeChart chart = createChart(dataset);
        
        
        res.setContentType("image/png");
        
        
        ChartUtilities.writeChartAsPNG(res.getOutputStream(), chart, 750, 500);
    }

    private CategoryDataset createDataset(Integer dinerID) {
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH , -7);
        
        
        
        List<Tuple> results = new GroupOrderDAOHibernateImplC().getAllOrderPrice(dinerID);
        for (Tuple result : results) {
            double totalSales = result.get("totalSales", BigDecimal.class).doubleValue();
            System.out.println("Total Sales: " + totalSales);
            Date currentDate = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
            String formattedDate = sdf.format(currentDate);
            dataset.addValue(totalSales, "", formattedDate);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        
        return dataset;
    }
        
        
//        List<Tuple> results = new GroupOrderDAOHibernateImplC().getAllOrderPrice(1);
//        for (Tuple result : results) {
////            Date orderDate = result.get("orderDate", Date.class);
//            double totalSales = result.get("totalSales", BigDecimal.class).doubleValue();
//
////            System.out.println("Order Date: " + orderDate);
//            System.out.println("Total Sales: " + totalSales);
//        }
//
//        
//    
//        
//        
//        
//        for (int i = 0 ; i < 7 ; i++) { // 从当前日期的前七天到前一天
//            double sales = 5000 + Math.random() * 5000;            
//            Date currentDate = calendar.getTime();
//            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
//            String formattedDate = sdf.format(currentDate);           
//            dataset.addValue(sales, "Sales", formattedDate);
//            calendar.add(Calendar.DAY_OF_MONTH , 1);
//        }
//        
//        return dataset;
//    }

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart(
                "Chart", // 图表标题
                "", // X轴标题
                "", // Y轴标题
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向
                true, // 是否显示图例
                true, // 是否生成工具
                false // 是否生成URL链接
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE); // 设置图表背景为白色
        plot.setRangeGridlinePaint(Color.white);
        plot.setRangeGridlineStroke(new BasicStroke(1.0f));

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);
        renderer.setBaseSeriesVisible(true); // 使線條可見
        renderer.setSeriesPaint(0, Color.BLUE); // 設定線條的顏色為藍色
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setUseFillPaint(true);
        renderer.setBaseFillPaint(Color.white);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        chart.getLegend().setVisible(false);

        return chart;
    }
}





