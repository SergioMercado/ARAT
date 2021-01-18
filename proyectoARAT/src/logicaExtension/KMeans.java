/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaExtension;

import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.functions.LinearRegression;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;


public class KMeans extends Unsupervised{
    private SimpleKMeans kmeans = null;
    private int clusters;
    
    public KMeans(String[] cabecera, ArrayList<Vector> datos) {
        super(cabecera, datos);
        this.setClusters(3);
        this.generate();
    }
    
    public KMeans(String[] cabecera, ArrayList<Vector> datos, int clouster) {
        super(cabecera, datos);
        this.setClusters(clouster);
        this.generate();
    }
    @Override
    public Vector<Point> centroides() {
        if(this.getKmeans_simple() != null){
            Instances centros = this.getKmeans_simple().getClusterCentroids();
            Vector<Point> points = new Vector<Point>();
            for(int i=0; i < centros.numInstances(); i++){
                Instance temp = centros.instance(i);
                Point punto = new Point(
                        Double.parseDouble(temp.toString(0)), 
                        Double.parseDouble(temp.toString(1))
                );
                points.add(punto);
            }
            return points;
        }
        return null;
    } 
    
    @Override
    public void generate() {
        try {
            this.setFv(new FastVector(this.getCabecera().length));
            for (String tag : this.getCabecera()) {
                this.getFv().addElement(new Attribute(tag));
            }
            Instances ds = new Instances("dataset", this.getFv(), 4);
            int VE = 0;
            double VB = 1.1;
            for(int i=0; i < this.getDatos().size(); i++){
                Instance instance = new Instance(this.getCabecera().length);
                for(int j=0; j < this.getDatos().get(i).size(); j++){
                    Object dato = this.getDatos().get(i).get(j);
                    instance.setValue((Attribute)this.getFv().elementAt(j), 
                            dato instanceof Integer?(int)dato:(double)dato
                    );
                }
                ds.add(instance);
            }
            this.setInstancias(ds);
            SimpleKMeans skm =new SimpleKMeans();
            skm.setNumClusters(3);
            skm.buildClusterer(ds);
            this.setKmeans_simple(skm);
        } catch (Exception ex) {
            Logger.getLogger(Regresion.class.getName()).log(Level.SEVERE, null, ex);
            this.setKmeans_simple(null);
        }
    }

    public void setKmeans_simple(SimpleKMeans kmeans_simple) {
        this.kmeans = kmeans_simple;
    }

    public SimpleKMeans getKmeans_simple() {
        return kmeans;
    }
  
    public int getClusters() {
        return clusters;
    }

    public void setClusters(int clusters) {
        this.clusters = clusters;
    }
    
    
}
