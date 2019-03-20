package Proceso;

import java.util.*;

public class Difusificacion {
    int entrada_real;
    int num_var;
    double valor_X1;
    double valor_Y1;
    double valor_X2;
    double valor_Y2;
    private Archivos archivos = new Archivos();

    public void setEntradaReal(int entrada_real, int num_var) {
        this.entrada_real = entrada_real;
        this.num_var = num_var;

        if (archivos.buscar_ArchivoTraslapes(entrada_real)) {
            valor_X2 = archivos.getValor_X2();
            calcularM(archivos.getValor_X1(), 1, archivos.getValor_X2(), 0);
            calcularM(archivos.getValor_XX1(), 0, archivos.getValor_XX2(), 1);
        } else {

        }
    }

    public void calcularM(double valor_X1, int valor_Y1, double valor_X2, double valor_Y2) {

    }

    public double[][] calculaT(int[][] puntosC) {
        double[][] puntosCO = new double[100][2];
        double[][] traspales = new double[100][2];
        double puntoi, puntof;
        int F = 0, C = 0;
        //double traslape1, traslape2, traslape3, traslape4;
        int i = 0, j = 0;
        //odena los puntos para sacar traslape
        for (i = 0; i <= puntosC.length; i++) {
            for (j = 0; j < 2; j++) {
                if (puntosC[i][j] != 0 || puntosC[i][j] != -1) {
                    puntosCO[F][C] = puntosC[i][j];
                    if (C == 1) {
                        F = F + 1;
                        C = 0;
                    }
                    C = C + 1;
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
