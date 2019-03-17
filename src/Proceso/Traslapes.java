package Proceso;

public class Traslapes {

    int num_etiqueta;
    double valor_X1;
    double valor_X2;
    double valor_XX1;
    double valor_XX2;

    public boolean buscar_ArchivoTraslapes(int entrada_real){
        //rescatar número de etiqueta
        //num_etiqueta es el numero de traslape en el que se encuentre la entrada real
        //num_etiqueta=;
        //Rescatar el valor de X2 Y DE XX1;
        valor_X2=0;//el final del traslape en el que se encuentra la entrada real;
        valor_XX1=0;//el inicio del traslape en el que se encuentra la entrada real;
        return true;
    }

    public void buscar_ArchivoPC(int num_etiqueta) {
        //leer e ir comparando con el numero de etiqueta hasta encontrarlo
        valor_X1=0;//primer punto critico encontrado

        num_etiqueta++;
        //leer e ir comparando con el numero de etiqueta hasta encontrarlo
        valor_XX2=0;//=//primer punto critico encontrado

    }



    public double getValor_X2(){
        return valor_X2;
    }

    public double getValor_XX1() {
        return valor_XX1;
    }
    public double getValor_X1(){
        return valor_X1;
    }
    public double getValor_XX2(){
        return valor_XX2;
    }
}
