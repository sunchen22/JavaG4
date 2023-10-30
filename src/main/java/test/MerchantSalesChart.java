package test;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class MerchantSalesChart {
    public static void main(String[] args) {
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        
        chart.setBackgroundPaint(Color.WHITE);

        try {
            
            ChartUtilities.saveChartAsPNG(new File("MerchantSalesChart.png"), chart, 1000, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static XYDataset createDataset() {
        TimeSeries series = new TimeSeries("");
        Day currentDay = new Day();
        for (int i = 0; i < 7; i++) {
            double sales;
            do {
                
                sales = 5000 + Math.random() * 5000;
            } while (sales < 5000 || sales > 10000);

            series.add(currentDay, sales);
            

            currentDay = (Day) currentDay.previous();
        }
        return new TimeSeriesCollection(series);
    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "",
                "",
                "",
                dataset,
                false,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

      
        plot.setBackgroundPaint(new Color(245, 245, 245));
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.WHITE);

        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);
        renderer.setSeriesPaint(0, new Color(0, 102, 204)); 
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        DateAxis xAxis = (DateAxis) plot.getDomainAxis();
        xAxis.setAxisLineVisible(true);
        xAxis.setTickMarksVisible(true);
        xAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 16));
        xAxis.setLabelFont(new Font("Arial", Font.BOLD, 18));
        xAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1));
        xAxis.setDateFormatOverride(new SimpleDateFormat("MM-dd"));
        
      
        
        

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAxisLineVisible(true);
        yAxis.setTickMarksVisible(true);
        yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 16));
        yAxis.setLabelFont(new Font("Arial", Font.BOLD, 18));
        yAxis.setRange(0, 10000);
        yAxis.setTickUnit(new NumberTickUnit(1000));

        chart.setTitle(new TextTitle("", new Font("Arial", Font.BOLD, 16)));
        chart.setBackgroundPaint(Color.WHITE);

        

        return chart;
    }
}




