package Proceso;
import java.util.*;

public class Difusificacion {
    int entrada_real;
    int num_var;
    double valor_X1;
    double valor_Y1;
    double valor_X2;
    double valor_Y2;
    private Archivos archivos=new Archivos();
    public void setEntradaReal(int entrada_real,int num_var){
        this.entrada_real=entrada_real;
        this.num_var=num_var;

        if(archivos.buscar_ArchivoTraslapes(entrada_real)){
            valor_X2=archivos.getValor_X2();
            calcularM(archivos.getValor_X1(),1,archivos.getValor_X2(),0);
            calcularM(archivos.getValor_XX1(),0,archivos.getValor_XX2(),1);
        }else{

        }
    }
    public void calcularM(double valor_X1, int valor_Y1, double valor_X2, double valor_Y2){

    }

    public void calculaT(int puntoC1, int puntoC2_1, int puntoC2_2, int puntoC3)
    {
        double traslape1,traslape2,traslape3,traslape4;
        //primer rango de traslape
        traslape1 = puntoC2_1-((puntoC2_1-puntoC1)*0.75);
        traslape2 = puntoC1+((puntoC2_1-puntoC1)*0.75);
        if (puntoC2_2!=0) {
            //segundo rango
            traslape3 = puntoC3-((puntoC3-puntoC2_1)*0.75);
            traslape4 = puntoC2_1+((puntoC3-puntoC2_1)*0.75);
        }
        else
        {
            //segundo rango con 2 puntos criticos
            traslape3 = puntoC3-((puntoC3-puntoC2_2)*0.75);
            traslape3 = puntoC2_2+((puntoC3-puntoC2_2)*0.75);
        }



    }
}
