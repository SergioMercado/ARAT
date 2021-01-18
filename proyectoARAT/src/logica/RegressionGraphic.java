/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Vector;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;


public class RegressionGraphic {

    private Vector datos;
    private double[] cf;

    public RegressionGraphic(Vector datos, double[] coef) {
        this.datos = datos;
        this.cf = coef;
    }

    public Vector getDatos() {
        return datos;
    }

    public void setDatos(Vector datos) {
        this.datos = datos;
    }

    public double[] getCoef() {
        return cf;
    }

    public void setCoef(double[] coef) {
        this.cf = coef;
    }
 
    public ChartPanel generarGrafica() {
        XYDataset dataset = this.createDataset();
        Graphic g = new Graphic("Regresión Temperatura", "Marca de tiempo", "Temperatura", dataset);
        return g.generarLineChart();
    }
    
    private XYDataset createDataset() {
        XYSeries s1 = new XYSeries("Temperatura");
        Parameters v;
        for (int i = 0; i < this.datos.size(); i++) {
            v = (Parameters) this.datos.get(i);
            s1.add(v.getTimestamp(), v.getTemperature());
        }
        XYSeries series2 = new XYSeries("Regresión");
        for (int i = 0; i < this.datos.size(); i++) {
            v = (Parameters) this.datos.get(i);
            Double pred = cf[0] * v.getTimestamp() + cf[2];
            series2.add(v.getTimestamp(), pred);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(series2);
        return dataset;
    }

   
}
