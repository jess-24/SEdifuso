package Proceso;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Scanner;

public class Main {
    private Difusificacion difusicar;
    public void menu(){
        Scanner s = new Scanner(System.in);
        do{
            System.out.println("1. Evaluar");
            System.out.println("2. Agregar nuevas variables");
            System.out.println("2. Editar variables");
            System.out.println("3. Salir");

            switch (s.nextInt();){
                case 1:
                    evaluar();
                    break;
            }
        }while(s<3);


    }
    public void evaluar(){
        difusicar=new Difusificacion();
        //leer catidad de variables existentes
        //int num_var=
        //pedir valores de cada variable
        //evaluar cada valor real
        int entrada_real=45;
        //for(int x=1; x<=num_var; x++){
             difusicar.setEntradaReal(entrada_real,num_var);
        // }

    }
    public void Obtener_hechos(){

    }
    public void Agregar_Hechos(){

    }
    public static void main(String [] args){
        Difusificacion d = new Difusificacion();
    }
}
