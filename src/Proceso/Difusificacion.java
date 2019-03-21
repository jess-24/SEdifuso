
package Proceso;

import java.io.IOException;
import java.util.*;

public class Difusificacion {
    double entrada_real;
    int num_variable;
    double valor_X1;
    double valor_Y1;
    double valor_X2;
    double valor_Y2;
    Maestro maestro;
    ArrayList<Competencia> variable;
    /* se crean los rangos de traslapes
     * Matriz de traslapes
     * ___________
     * | 23 | 34 |
     * | 38 | 45 |
     * | 56 | 67 |
     * -----------
     * */
    public void setEntradaReal(double entrada_real, int num_variable) {
        this.entrada_real = entrada_real;
        this.num_variable = num_variable;
        double valor_XX1,vallor_XX2;
        variable=new ArrayList<Competencia>();
        String etiqueta=null,etiquetaAux=null;
        int num_etiqueta=0;
        //num_etiqueta es el numero de traslape en el que se encuentre la entrada real
        //num_etiqueta=;
        //Rescatar el valor de X2 Y DE XX1;
        valor_X2=0;//el final del traslape en el que se encuentra la entrada real;
        valor_XX1=0;//el inicio del traslape en el que se encuentra la entrada real;
        //leer e ir comparando con el numero de competencia hasta encontrarlo
        try {
            variable = maestro.buscarCompetencia(num_variable);
            double [][] traslapes=calculaT(variable);

            valor_X1=variable.get(num_etiqueta).getP2();
            etiqueta=variable.get(num_etiqueta).getEtiqueta();
            vallor_XX2=variable.get((num_etiqueta+1)).getP1();
            etiquetaAux=variable.get((num_etiqueta+1)).getEtiqueta();
        } catch (IOException e) {
            e.printStackTrace();
        }
            calcularM(valor_X1, 1, valor_X2, 0,entrada_real,etiqueta);
            calcularM(valor_XX1, 0, valor_XX2, 1,entrada_real,etiquetaAux);

    }
    double m;
    public void calcularM(double valor_X1, int valor_Y1, double valor_X2, double valor_Y2,double entrada_real,String etiqueta) {
        m=(valor_Y2-valor_Y1)/(valor_X2-valor_X1);
        y_grado_membresia=m*entrada_real - (m*valor_X1 +(valor_Y1));
    }
    public double getGradoMem_salidaDifusa(){
        return y_grado_membresia;
    }
    double y_grado_membresia;

    public double[][] calculaT(ArrayList<Competencia> puntosCC) {
        double[][] puntosC = new double[8][2];
        double[][] puntosCO = new double[8][2];
        double[][] traspales = new double[8][2];
        double puntoi, puntof;
        int F = 0, C = 0;
        //double traslape1, traslape2, traslape3, traslape4;

        //odena los puntos para sacar traslape

        for (int l = 0; l <= puntosCC.size(); l++)
        {
                puntosC[l][0]=puntosCC.get(l).getP1();
                puntosC[l][1]=puntosCC.get(l).getP2();
        }

        for (int i = 0; i <= puntosC.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (puntosC[i][j] != 0 || puntosC[i][j] != -1) {
                    puntosCO[F][C] = puntosC[i][j];
                    if (C == 1) {
                        F = F + 1;
                        C = 0;
                    }
                    else
                    {
                        C = C + 1;
                    }

                }
            }
        }
         /* se crean los rangos de traslapes
         * Matriz de traslapes
         * ___________
         * | 23 | 34 |
         * | 38 | 45 |
         * | 56 | 67 |
         * -----------
         * */
        for (int h = 0; h < puntosCO.length; h++) {
            double resta = puntosCO[h][1] - puntosCO[h][0];
            double porcentaje = resta * 0.75;
            traspales[h][0] = puntosCO[h][1] - porcentaje;
            traspales[h][1] = puntosCO[h][0] + porcentaje;
        }

         //Retornamos el arreglo
        return traspales;
    }
}
