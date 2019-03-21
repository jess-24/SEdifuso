package Proceso;

import java.util.ArrayList;

public class Evaluar {
    public int X,X2;
    public int posicion;
    public double x1,x2;

    public double membrecia,y1,y2,Y;
    public String etiqueta;
    public Boolean bandera=false;
    Difusificacion dif;
    public double entrada_real = 0;
    ArrayList<Variable> varibles_difisas = new ArrayList<Variable>();
    //Maestro m = new Maestro();
    public Evaluar(Difusificacion dif){
        this.dif=dif;
    }
    public ArrayList<Variable> Evaluar_M(double entrara_real, ArrayList<Competencia> puntosC) {

for (int i=0;i<puntosC.size(); i++)
{

        if (entrada_real <= puntosC.get(i).getP2() && puntosC.get(i).getP1()==0) {
                etiqueta=puntosC.get(i).getEtiqueta();
                X=puntosC.get(i).getP2();
                Y=1;
                posicion=i;
                bandera=true;
                varibles_difisas.add(new Variable(etiqueta,entrara_real,Y));
        } else {
            if ((entrada_real == puntosC.get(i).getP1() && puntosC.get(i).getP2()==-1)) {
                etiqueta=puntosC.get(i).getEtiqueta();
                X=puntosC.get(i).getP1();
                Y=1;
                posicion=i;
                bandera=true;
                varibles_difisas.add(new Variable(etiqueta,entrara_real,Y));
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
                    varibles_difisas.add(new Variable(etiqueta,entrara_real,Y));
                } else {
                    if (entrada_real>=puntosC.get(i).getP1() && puntosC.get(i).getP2()==100)
                    {
                        etiqueta=puntosC.get(i).getEtiqueta();
                        X=puntosC.get(i).getP1();
                        Y=1;
                        posicion=i;
                        bandera=true;
                        varibles_difisas.add(new Variable(etiqueta,entrara_real,Y));
                    }

                }
            }
        }

}

if (bandera==false)
{
    ArrayList<Competencia> puntos = new ArrayList<Competencia>();
    double[][] rangos_de_traspales = dif.calculaT(puntos);
    for (int i = 0; i <= rangos_de_traspales.length; i++)
    {

                if (entrada_real<rangos_de_traspales[i][0] )
                {
                    if (puntosC.get(i).getP1()==0 || puntosC.get(i).getP2()!=-1)
                    {
                        x1=puntosC.get(i).getP2();
                        y1=1;
                        x2=rangos_de_traspales[i][1];
                        y2=0;
                        etiqueta= puntosC.get(i).getEtiqueta();
                    }
                    else
                    {
                        if(puntosC.get(i).getP2()==-1)
                        {

                        }
                        x1=puntosC.get(i).getP1();
                        y1=1;
                        x2=rangos_de_traspales[i][1];
                        y2=0;
                        etiqueta= puntosC.get(i).getEtiqueta();
                    }

                }
                else
                {
                    if (entrada_real>rangos_de_traspales[i][1])
                    {

                            x1=rangos_de_traspales[i][0];
                            y1=0;
                            x2=puntosC.get(i).getP1();
                            y2=1;
                            etiqueta= puntosC.get(i+1).getEtiqueta();

                    }
                }

    }
    membrecia= Pendiente(entrada_real,x1,y1,x2,y2);
    Y=membrecia;


    varibles_difisas.add(new Variable(etiqueta,entrara_real,Y));

}
        for (int t=0;t<puntosC.size();t++)
        {
                if (!puntosC.get(t).getEtiqueta().equals(etiqueta))
                {
                    varibles_difisas.add(new Variable(puntosC.get(t).getEtiqueta(),entrara_real,0));
                }

        }

return varibles_difisas;
    }//metodo

    public double Pendiente(double x,double x1,double y1,double x2,double y2){
        double y = 0.0;
        y = ((x-x1)*(y2-y1)/(x2-x1)) + y1;
        return y;
    }


}//class

