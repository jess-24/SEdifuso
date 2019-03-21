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
    ArrayList<Competencia> variable = new ArrayList<Competencia>();
    Maestro maestro = new Maestro();
    Evaluar evaluar;
    ArrayList<Variable> varibles_difisas = new ArrayList<Variable>();
    public int[][] ordenada = new int[8][2];

    public ArrayList<Variable> setEntradaReal(int entrada_real, int num_variable) {
        this.entrada_real = entrada_real;
        this.num_variable = num_variable;
        System.out.println(num_variable);
        System.out.println(entrada_real);
        variable=new ArrayList<Competencia>();
        int num_etiqueta=0;
        //num_etiqueta es el numero de traslape en el que se encuentre la entrada real
        try {
            variable = maestro.buscarCompetencia(num_variable);
            int [][] traslapes=calculaT(variable);
            int col=0;
            for (int fila = 0; fila < 8; fila++) {
                if(entrada_real>= traslapes[fila][col]){
                    if (entrada_real<=traslapes[fila][col+1]) {
                        num_etiqueta = fila + 1;
                        varibles_difisas = evaluarX_En_Traslape(entrada_real, traslapes[fila][col + 1], traslapes[fila][col], num_etiqueta, variable);
                    }else {
                        evaluar=new Evaluar(this);
                        varibles_difisas=evaluar.Evaluar_M(entrada_real,variable);
                    }

                }/*else {
                        evaluar=new Evaluar(this);
                        varibles_difisas=evaluar.Evaluar_M(entrada_real,variable);
                }*/
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return varibles_difisas;
    }
    public ArrayList<Variable> evaluarX_En_Traslape(double entrada_real,double valor_X2, double valor_XX1,int num_etiqueta,ArrayList<Competencia> variable){
        double valor_XX2;
        ArrayList<Variable> var_difisas = new ArrayList<Variable>();
        String etiqueta=null,etiquetaAux=null;
        if(variable.get(num_etiqueta).getP2() == -1){
            valor_X1=variable.get(num_etiqueta).getP1();
        }else{
        valor_X1=variable.get(num_etiqueta).getP2();}
        etiqueta=variable.get(num_etiqueta).getEtiqueta();
        valor_XX2=variable.get((num_etiqueta+1)).getP1();
        etiquetaAux=variable.get((num_etiqueta+1)).getEtiqueta();

        calcularM(valor_X1, 1, valor_X2, 0,entrada_real,etiqueta);
        var_difisas.add(new Variable(etiqueta,entrada_real,getGradoMem_salidaDifusa()));
        calcularM(valor_XX1, 0, valor_XX2, 1,entrada_real,etiquetaAux);
        var_difisas.add(new Variable(etiquetaAux,entrada_real,getGradoMem_salidaDifusa()));
        for (int t=0;t<variable.size();t++)
        {
            if (!variable.get(t).getEtiqueta().equals(etiqueta))
            {
                var_difisas.add(new Variable(variable.get(t).getEtiqueta(),entrada_real,0));
            }

        }
        return var_difisas;
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
    public int[][] calculaT(ArrayList<Competencia> puntosCC) {
        int[][] puntosC = new int[8][2];
        int[][] puntosCO = new int[8][2];
        int[][] traspales = new int[8][2];
        double puntoi, puntof;
        int F = 0, C = 0;
        //double traslape1, traslape2, traslape3, traslape4;

        //odena los puntos para sacar traslape

        for (int l = 0; l < puntosCC.size(); l++)
        {
                puntosC[l][0]=puntosCC.get(l).getP1();
                puntosC[l][1]=puntosCC.get(l).getP2();
        }

        for (int i = 0; i < puntosC.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (puntosC[i][j] != 0.0  ) {
                    if (puntosC[i][j] != -1 || puntosC[i][j] != 100) {
                        puntosCO[F][C] = puntosC[i][j];
                        if (C == 1) {
                            F = F + 1;
                            C = 0;
                        } else {
                            C = C + 1;
                        }
                    }
                }
            }
        }
        ordenada=puntosCO;
        for (int v=0;v<ordenada.length;v++)
        {
            System.out.println("Ordenada  "+ordenada[v][0]+" - "+ordenada[v][1]);
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
            if (puntosCO[h][0]!=-1 || puntosCO[h][1]!=-1)
            {
                int resta = puntosCO[h][1] - puntosCO[h][0];
                int porcentaje = (int)(resta * 0.75);
                traspales[h][0] = puntosCO[h][1] -  porcentaje;
                traspales[h][1] = puntosCO[h][0] + porcentaje;
            }

        }

         //Retornamos el arreglo
        return traspales;
    }
}
