package Proceso;

import java.util.ArrayList;

public class Evaluar {
    public int X,X2;
    public int posicion;
    public double x1,x2;

    public double membrecia,y1,y2,Y;
    public String etiqueta;
    public Boolean bandera=false;
    Difusificacion dif = new Difusificacion();
    public double entrada_real = 0;
    ArrayList<Variable> varibles_difisas = new ArrayList<Variable>();
    Maestro mm = new Maestro();
    public Evaluar(Difusificacion dif){
        this.dif=dif;
    }
    public ArrayList<Variable> Evaluar_M(int entrada_real, ArrayList<Competencia> puntosC) {

        int Otro=0;
        try {
             Otro= mm.cantidad_Competencias();
        }catch (Exception ef){};
for (int i=0;i<Otro; i++)
{
    System.out.println(puntosC.get(i).getP1() +" - "+ puntosC.get(i).getP2());
if (true)
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
                System.out.println("Entra, pero no deberia"+2);
                //varibles_difisas.add(new Variable(etiqueta,entrada_real,Y));
            } else {
                if (entrada_real >= puntosC.get(i).getP1() && puntosC.get(i).getP2()!=-1 &&  entrada_real <= puntosC.get(i).getP2()) {
                    etiqueta= puntosC.get(i).getEtiqueta();
                    bandera=true;
                    Y = 1;
                    posicion = i;
                    X=puntosC.get(i).getP1();
                    if (entrada_real==puntosC.get(i).getP2()) {
                        X = puntosC.get(i).getP2();
                        System.out.println("Entra, pero no deberia" +3);
                    }
                    System.out.println("Entra, pero no deberia"+4);
                    //varibles_difisas.add(new Variable(etiqueta,entrada_real,Y));
                } else {
                    if (entrada_real>=puntosC.get(i).getP1() && (puntosC.get(i).getP2()==100 & puntosC.get(i).getP1()==-1))
                    {
                        etiqueta=puntosC.get(i).getEtiqueta();
                        X=puntosC.get(i).getP1();
                        Y=1;
                        posicion=i;
                        bandera=true;
                        System.out.println("Entra, pero no deberia"+5);
                        //varibles_difisas.add(new Variable(etiqueta,entrada_real,Y));
                    }

                }
            }
        }
}

}

if (bandera==false)
{
    ArrayList<Competencia> puntos = new ArrayList<Competencia>();
    double[][] rangos_de_traspales =  dif.calculaT(puntosC);
    int[][]  ordenadaa = dif.ordenada;

    for (int i = 0; i < rangos_de_traspales.length; i++)
    {
                if (entrada_real<rangos_de_traspales[i][0] && entrada_real>ordenadaa[i][0] )
                {
                    x1=ordenadaa[i][0];
                    y1=1;
                    x2=rangos_de_traspales[i][1];
                    y2=0;
                    etiqueta= puntosC.get(i).getEtiqueta();


                }
                else
                {
                    if (entrada_real>rangos_de_traspales[i][1] && entrada_real<ordenadaa[i][1])
                    {

                            x1=rangos_de_traspales[i][0];
                            y1=0;
                            x2=ordenadaa[i][1];
                            y2=1;
                            etiqueta= puntosC.get(i+1).getEtiqueta();

                    }
                }

    }
    Y=calcularM(x1,y1,x2,y2,entrada_real,"Etiqueta");
    //membrecia= Pendiente(entrada_real,x1,y1,x2,y2);
    //Y=membrecia;




}
        varibles_difisas.add(new Variable(etiqueta,entrada_real,Y));
        for (int t=0;t<puntosC.size();t++)
        {
                if (!puntosC.get(t).getEtiqueta().equals(etiqueta))
                {
                    varibles_difisas.add(new Variable(puntosC.get(t).getEtiqueta(),entrada_real,0));
                }

        }

return varibles_difisas;
    }//metodo

    double m;
    public double calcularM(double valor_X1, double valor_Y1, double valor_X2, double valor_Y2,int entrada_real,String etiqueta) {
        double y_grado_membresia=0;
        m=(valor_Y2-valor_Y1)/(valor_X2-valor_X1);
        y_grado_membresia=(m*entrada_real) - (m*valor_X1) +(valor_Y1);
        return y_grado_membresia;
    }


}//class

