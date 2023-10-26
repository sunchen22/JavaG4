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
        
        
//       

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart(
                "Chart", 
                "", 
                "", 
                dataset, 
                PlotOrientation.VERTICAL, 
                true, 
                true, 
                false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white); 
        plot.setRangeGridlinePaint(Color.white);
        plot.setRangeGridlineStroke(new BasicStroke(1.0f));

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);
        renderer.setBaseSeriesVisible(true); 
        renderer.setSeriesPaint(0, Color.blue); 
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setUseFillPaint(true);
        renderer.setBaseFillPaint(Color.white);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        chart.getLegend().setVisible(false);

        return chart;
    }
}





