/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Vector;
import logicaExtension.Point;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;


public class ClusteringGraphic {

    private Vector datos;
    private Vector<Point> TemHum;
    private Vector<Point> TemVel;

    public ClusteringGraphic(Vector datos, Vector<Point> TemHum, Vector<Point> TemVel) {
        this.datos = datos;
        this.TemHum = TemHum;
        this.TemVel = TemVel;
    }

    public Vector getDatos() {
        return datos;
    }

    public void setDatos(Vector datos) {
        this.datos = datos;
    }

    private XYDataset CreateTemperatureAndHumidityDatabase() {
        XYSeries s1 = new XYSeries("Temperatura y Humedad");
        Parameters v;
        for (int i = 0; i < this.datos.size(); i++) {
            v = (Parameters) this.datos.get(i);
            s1.add(v.getTemperature(), (Number) v.getHumidity());
        }
        XYSeries s2 = new XYSeries("Temperatura vs Humedad");
        for (int i = 0; i < this.TemHum.size(); i++) {
            s2.add(this.TemHum.get(i).getX(), this.TemHum.get(i).getY());
        }
        XYSeriesCollection ds = new XYSeriesCollection();
        ds.addSeries(s1);
        ds.addSeries(s2);
        return ds;
    }

      public ChartPanel generarGraficaTemHum() {
        XYDataset ds = this.CreateTemperatureAndHumidityDatabase();
        Graphic grafico = new Graphic("Clostering Temperatura y Humedad", "Temperatura", "Humedad", ds);
        return grafico.generarScatterChart();
    }

    public ChartPanel generarGraficaTemVel() {
        XYDataset ds = this.crearDatasetTemVel();
        Graphic grafico = new Graphic("Clostering Temperatura y Velocidad", "Temperatura", "Velocidad", ds);
        return grafico.generarScatterChart();
    }
    
    private XYDataset crearDatasetTemVel() {
        XYSeries s1 = new XYSeries("Temperatura y Velocidad");
        Parameters v;
        for (int i = 0; i < this.datos.size(); i++) {
            v = (Parameters) this.datos.get(i);
            s1.add(v.getTemperature(), v.getSpeed());
        }
        XYSeries s2 = new XYSeries("Kmeans temperatura vs Velocidad");
        for (int i = 0; i < this.TemVel.size(); i++) {
            s2.add(this.TemVel.get(i).getX(), this.TemVel.get(i).getY());
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        return dataset;
    }

  
}
