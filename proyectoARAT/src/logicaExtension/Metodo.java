/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaExtension;

import java.util.Vector;
import weka.core.FastVector;
import java.util.ArrayList;
import weka.core.Instances;
/**
 *
 * @author dark
 */
public abstract class Metodo{
    private FastVector fv;
    private ArrayList<Vector> datos;
    private String[] titulo;
    private Instances instancias;
            
    public Metodo(String titulo[], ArrayList<Vector> datos){
        this.titulo = titulo;
        this.datos = datos;
        this.instancias = null;
    }

    public FastVector getFv() {
        return fv;
    }

    public ArrayList<Vector> getDatos() {
        return datos;
    }

    public String[] getCabecera() {
        return titulo;
    }

    public void setFv(FastVector fv) {
        this.fv = fv;
    }

    public void setDatos(ArrayList<Vector> datos) {
        this.datos = datos;
    }

    public void setCabecera(String[] cabecera) {
        this.titulo = cabecera;
    }

    public Instances getInstancias() {
        return instancias;
    }

    public void setInstancias(Instances instancias) {
        this.instancias = instancias;
    }
    
      public String evaModelo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double[] coeficienteModelo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        return "Metodo{" + "fv=" + fv + ", datos=" + datos + ", titulo=" + titulo + '}';
    }
    

  
    
}
