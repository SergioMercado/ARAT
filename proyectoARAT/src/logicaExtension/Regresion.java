/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaExtension;


import weka.core.Attribute;
import java.util.logging.Level;
import weka.core.FastVector;
import java.text.DecimalFormat;
import java.util.Vector;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;
import weka.classifiers.Evaluation;

public class Regresion extends Supervisado{
    private LinearRegression regresion;

    public Regresion(String[] titulo, ArrayList<Vector> datos) {
        super(titulo, datos);
        this.regresion = null;
        this.generate();
    }
    
    
    public LinearRegression getRegresion() {
        return regresion;
    }

    public void setRegresion(LinearRegression regresion) {
        this.regresion = regresion;
    }

    @Override
    public void generate() {
        try {
            this.setFv(new FastVector(this.getCabecera().length));
            for (String etiqueta : this.getCabecera()) {
                this.getFv().addElement(new Attribute(etiqueta));
            }
            Instances dataset = new Instances("dataset", this.getFv(), this.getDatos().size());
            dataset.setClassIndex(dataset.numAttributes() - 1);
            int VE = 0;
            double VD = 1.1;
            for(int i=0; i < this.getDatos().size(); i++){
                Instance instance = new Instance(this.getCabecera().length);
                for(int j=0; j < this.getDatos().get(i).size(); j++){
                    Object valor = this.getDatos().get(i).get(j);
                    instance.setValue((Attribute)this.getFv().elementAt(j), 
                            valor instanceof Integer?(int)valor:(double)valor
                    );
                }
                dataset.add(instance);
            }
            this.setInstancias(dataset);
            LinearRegression lr=new LinearRegression();
            lr.buildClassifier(dataset);
            this.setRegresion(lr);
        } catch (Exception ex) {
            Logger.getLogger(Regresion.class.getName()).log(Level.SEVERE, null, ex);
            this.setRegresion(null);
            this.setInstancias(null);
        }
    }

    @Override
    public double predecir(double valor) {
        double[] coeficientes = this.coeficienteModelo();
        return (valor*coeficientes[0]+coeficientes[2]);
    }


    @Override
    public String evaModelo() {
       if(this.getInstancias() != null){
            try {
                DecimalFormat df = new DecimalFormat("#.0000");
                Evaluation ev = new Evaluation(this.getInstancias());
                ev.crossValidateModel(this.getRegresion(), this.getInstancias(), 5, new Random(1), new String[]{});
                String informacion="\n\tValores de evaluación ";
                informacion+="\nCoeficiente de correlacion: "+df.format(ev.correlationCoefficient());
                informacion+="\nError absoluto medio      : "+df.format(ev.meanAbsoluteError());
                informacion+="\nError del cuadrado medio  : "+df.format(ev.rootMeanSquaredError());
                informacion+="\nError absoluto relativo   : "+df.format(ev.relativeAbsoluteError())+" %";
                informacion+="\nError cuadratico relativo : "+df.format(ev.rootRelativeSquaredError())+" %";
                informacion+="\nNumero de instancias      : "+((int)ev.numInstances());
                return informacion;
            } catch (Exception ex) {
                Logger.getLogger(Regresion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }

    @Override
    public double[] coeficienteModelo() {
        if(this.getRegresion() != null){
            return this.getRegresion().coefficients();
        }
        return (new double[]{});
    }
    
    
}
