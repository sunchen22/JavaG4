package test;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.math.BigDecimal;
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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
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

     
        List<Tuple> results = new GroupOrderDAOHibernateImplC().getAllOrderPrice(dinerID);
        for (Tuple result : results) {
            double totalSales = result.get("totalSales", BigDecimal.class).doubleValue();
            Date orderDate = result.get("orderDate", Date.class);
          
            dataset.addValue(totalSales, "", orderDate);
           
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
        renderer.setSeriesPaint(0, new Color(0, 102, 204)); 
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setUseFillPaint(true);
        renderer.setBaseFillPaint(Color.white);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        chart.getLegend().setVisible(false);
        

        return chart;
    }
}





