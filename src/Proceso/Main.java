package Proceso;

//import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Difusificacion difusicar;
    private static Maestro maestro = new Maestro();

    public static void menu() throws IOException {
        Scanner scan = new Scanner(System.in);
        int s = 0;
        String llave;
        do{
            System.out.println("1. Evaluar.");
            System.out.println("2. Agregar nuevas variables.");
            System.out.println("3. Editar variables.");
            System.out.println("-");
            System.out.println("4. Leer archivo \"Maestro\" (puntos críticos).");
            System.out.println("5. Leer archivo \"Indice\".");
            System.out.println("6. Leer archivo \"Traslapes\".");
            System.out.println("-");
            System.out.println("7. Salir.");
            System.out.println("-\nElige una opción:");

            s = scan.nextInt();

            switch (s){
                case 1:
                    //evaluar();
                    break;
                case 2:
                    System.out.println("Escribe la llave de la regla a escribir:");
                    do{
                        llave = scan.nextLine();
                    } while(!validarNumerico(llave));
                    maestro.escribirVariable(Integer.parseInt(llave));
                case 4:
                    maestro.leerSecuencial();
            }
        }while(s < 7);
    }

    public static void evaluar(){
        difusicar=new Difusificacion();
        //leer catidad de variables existentes
        //int num_var=
        //pedir valores de cada variable
        //evaluar cada valor real
        int entrada_real=45;
        //for(int x=1; x<=num_var; x++){
           //  difusicar.setEntradaReal(entrada_real,num_var);
        // }

    }
    public void Obtener_hechos(){

    }
    public void Agregar_Hechos(){

    }

    static private boolean validarNumerico(String x){
        if(x.matches("^[1-9]\\d*$"))
            return true;
        else{
            if(!x.equals(""))
                System.out.println("El valor ingresado no es númerico.");
            return false;
        }
    }

    public static void main(String [] args) throws IOException {
        Difusificacion d = new Difusificacion();
        menu();
    }


}
