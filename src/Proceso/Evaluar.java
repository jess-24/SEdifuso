package Proceso;

import java.util.ArrayList;

public class Evaluar {

    public void Evaluar(int entrara_real, ArrayList<Competencia> puntosC) {
        int X,X2,Y, posicion;
        String etiqueta;
        Boolean bandera=false;

        int entrada_real = 0;
        double[][] resultado = new double[100][2];
        //String[] puntoss = Consultar_archivo_PC();


for (int i=0;i<puntosC.size(); i++)
{

        if (entrada_real <= puntosC.get(i).getP2() && puntosC.get(i).getP1()==0) {
                etiqueta=puntosC.get(i).getEtiqueta();
                X=puntosC.get(i).getP2();
                Y=1;
                posicion=i;
                bandera=true;
        } else {
            if ((entrada_real == puntosC.get(i).getP1() && puntosC.get(i).getP2()==-1)) {
                etiqueta=puntosC.get(i).getEtiqueta();
                X=puntosC.get(i).getP1();
                Y=1;
                posicion=i;
                bandera=true;
            } else {
                if (entrada_real > puntosC.get(i).getP1() && puntosC.get(i).getP2()!=-1 &&  entrada_real <= puntosC.get(i).getP2()) {
                    etiqueta= puntosC.get(i).getEtiqueta();
                    bandera=true;
                    X=puntosC.get(i).getP1();
                    if (entrada_real==puntosC.get(i).getP2()) {
                        X = puntosC.get(i).getP2();
                        Y = 1;
                        posicion = i;
                    }
                } else {
                    if (entrada_real>=puntosC.get(i).getP1() && puntosC.get(i).getP2()==100)
                    {
                        etiqueta=puntosC.get(i).getEtiqueta();
                        X=puntosC.get(i).getP1();
                        Y=1;
                        posicion=i;
                        bandera=true;
                    }

                }
            }
        }

}

if (bandera==false)
{
    Difusificacion dif= new Difusificacion();
    dif.calculaT();
}
    }//metodo

    public double Pendiente(int x,double x1,double y1,double x2,double y2){
        double y = 0.0;
        y = ((x-x1)*(y2-y1)/(x2-x1)) + y1;
        return y;
    }


}//class

