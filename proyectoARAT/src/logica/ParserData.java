/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Vector;
import java.util.Vector;
import java.util.ArrayList;


public class ParserData {
    private Vector<Parameters> datos;

    public ParserData(Vector<Parameters> datos) {
        this.datos = datos;
    }


    public Tag getDataTemperaturaHumedad() {
        ArrayList<Vector> tem_datos = new ArrayList<Vector>();
        for(Parameters dato:datos){
            Vector tem_vector = new Vector();
            tem_vector.add(dato.getTemperature());
            tem_vector.add(dato.getHumidity());
            tem_datos.add(tem_vector);
        }
        Tag etiqueta = new Tag(tem_datos, new String[]{"Temperatura", "Humedad"});
        return etiqueta;
    }

      public Tag getDataTemperatura() {
        ArrayList<Vector> tem_datos = new ArrayList<Vector>();
        for(Parameters dato:datos){
            Vector tem_vector = new Vector();
            tem_vector.add(dato.getTimestamp());
            tem_vector.add(dato.getTemperature());
            tem_datos.add(tem_vector);
        }
        Tag etiqueta = new Tag(tem_datos, new String[]{"Tiempo", "Temperatura"});
        return etiqueta;
    }
  
    public Tag getDataTemperaturaVelocidad() {
        ArrayList<Vector> tem_datos = new ArrayList<Vector>();
        for(Parameters dato:datos){
            Vector tem_vector = new Vector();
            tem_vector.add(dato.getTemperature());
            tem_vector.add(dato.getSpeed());
            tem_datos.add(tem_vector);
        }
        Tag etiqueta = new Tag(tem_datos, new String[]{"Temperatura", "Velocidad"});
        return etiqueta;
    }
    
    
}
