/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Vector;
import java.util.Vector;
import java.util.ArrayList;


public class Tag {
    private ArrayList<Vector> datos;
    private String[] titulo;

    public Tag(ArrayList<Vector> datos, String[] titulo) {
        this.datos = datos;
        this.titulo = titulo;
    }

    public ArrayList<Vector> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<Vector> datos) {
        this.datos = datos;
    }

    public String[] getTitulo() {
        return titulo;
    }

    public void setTitulo(String[] titulo) {
        this.titulo = titulo;
    }

    
}
