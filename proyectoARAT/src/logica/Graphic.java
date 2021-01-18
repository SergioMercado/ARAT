/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author mario
 */
public class Graphic {

    private String title;
    private String l1;
    private String l2;
    private XYDataset ds;

    public Graphic(String title, String l1, String l2, XYDataset ds) {
        this.title = title;
        this.l1 = l1;
        this.l2 = l2;
        this.ds = ds;
    }

    public ChartPanel generarLineChart() {
        JFreeChart chart = ChartFactory.createXYLineChart(
                this.title,
                this.l1,
                this.l2,
                this.ds,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );

        XYPlot plot = (XYPlot) chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        ChartPanel cp = new ChartPanel(chart, false);
        return cp;
    }

    public ChartPanel generarScatterChart() {
        JFreeChart chart = ChartFactory.createScatterPlot(
                this.title,
                this.l1,
                this.l2,
                this.ds
        );

        XYPlot plot = (XYPlot) chart.getXYPlot();
        plot.setBackgroundPaint(Color.white);
        ChartPanel cp = new ChartPanel(chart, false);
        return cp;
    }
}
