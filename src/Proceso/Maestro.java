package Proceso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class Maestro {
    int num_etiqueta;
    double valor_X1;
    double valor_X2;
    double valor_XX1;
    double valor_XX2;
    Indice indice = new Indice();
    int longitud = 68;
    RandomAccessFile maestro;
    long tamaño;
    Indice res;
    int bandera = 1, llave;
    String aux[];
    Scanner scan = new Scanner(System.in);

    String competencia, ca, etiqueta, pc;
    boolean band;
    int pcs[] = new int[16];
    String etiqs[] = new String[16];


    public void escribirVariable(int id) throws IOException {
        llave = id;
        do {
            maestro = new RandomAccessFile("maestro.dat", "rw");
            tamaño = maestro.length();
            maestro.seek(tamaño);//nos vamos hasta el final del archivo
            res = indice.buscarIndice(llave);
            competencia = "";

            if (res.getExistente() == 1) {
                System.out.println("Regla ya existente.");
                System.out.println("Escriba una nueva existente para continuar o 0 para terminar.");
                llave = scan.nextInt();
            } else {
                System.out.println("Coloque el nombre de la competencia");

                competencia = scan.nextLine();
                indice.escribir_indice(llave, competencia);//escribimos la entrada en el indice
                maestro.writeInt(llave);//escribimos la regla en el maestro

                int cant = 0;
                System.out.println("Cantidad de puntos criticos (funciones), maximo 8:");
                do {
                    ca = scan.next();
                } while (!validarNumerico(ca));
                cant = Integer.parseInt(ca);

                // guarda en arreglos los datos que serán escritos en lso archivos
                for (int i = 0; i < cant; i++) {

                    System.out.println("Ingrese la etiqueta para el punto critico " + (i+1));
                    etiqueta = scan.nextLine();
                    etiqueta = scan.nextLine();
                    etiqs[i] = etiqueta;

                    System.out.println("Ingresa el valor del punto critico " + (i + 1) + " (si son 2 puntos, separar por comas)");
                    pc = scan.next();
                    aux = pc.split(",");

                    if(aux.length > 0)
                        if(validarNumerico(aux[0])) {
                            if(i == 0 && aux.length == 1){
                                pcs[0] = 0;
                                pcs[1] = Integer.parseInt(aux[0]);
                            } else if(i == 0 && aux.length > 1){
                                pcs[0] = Integer.parseInt(aux[0]);
                            } else {
                                pcs[i * 2] = Integer.parseInt(aux[0]);
                                pcs[(i * 2) + 1] = -1;
                            }
                        }
                    if(aux.length > 1)
                        if(validarNumerico(aux[1]))
                            pcs[(i * 2) + 1] = Integer.parseInt(aux[1]);
                }

                for (int i = cant; i < 8; i++) {
                    pcs[(i * 2)] = -1;
                    pcs[(i * 2) + 1] = -1;
                }

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 15; j++) {
                        if (etiqs[i] != null)
                            if(j < etiqs[i].length())
                                maestro.writeChar(etiqs[i].charAt(j));
                            else
                                maestro.writeChar(' ');
                        else
                            maestro.writeChar(' ');
                    }
                    maestro.writeInt(pcs[(i*2)]);
                    maestro.writeInt(pcs[(i*2) + 1]);
                }
                System.out.println(competencia);
                for (int i = 0; i < 8; i++) {
                    if(etiqs[i] != null)
                        System.out.println(etiqs[i] + ", Rango de [" + pcs[(i*2)] + ", " + pcs[(i*2)+1] + "]");
                }
                maestro.close();
                System.out.println("Presione 0 para terminar o 1 para ingresar una nueva regla");
                bandera = scan.nextInt();
            }//fin else
        }while (bandera != 0);
    }


    public void leerSecuencial() throws IOException {
        maestro = new RandomAccessFile("maestro.dat", "rw");
        long tamaño = maestro.length();
        String competencia = "";

        if(tamaño > 0) {
            while(maestro.getFilePointer() < tamaño) {
                System.out.print(maestro.readInt() + "\t");
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 15; k++) {
                        System.out.print(maestro.readChar());
                    }
                    System.out.print("\t" + maestro.readInt() + "\t");
                    System.out.print(maestro.readInt() + "\t");
                }
                System.out.print("\n");
            }
            System.out.println(competencia);
        } else {
            System.out.println("El archivo está vacio");
        }
        maestro.close();
    }

    public long desplazamiento() throws IOException{
        long despl;
        maestro = new RandomAccessFile("maestro.dat", "rw");
        maestro.readInt();
        for (int j = 0; j < 8; j++) {
            for (int k = 0; k < 15; k++)
                maestro.readChar();
            maestro.readInt();
            maestro.readInt();
        }
        despl = maestro.getFilePointer();
        return despl;
    }

    public int[][] buscarCompetencia(int llave) throws IOException {
        int puntos_criticos[][] = new int[8][2];
        maestro = new RandomAccessFile("maestro.dat", "rw");
        Indice inx = indice.buscarIndice(llave);
        System.out.println("Existe: " + inx.getExistente() + "\nPosicion: " + inx.getPosicion() + "\nCompetencia: " + inx.getCompetencia());

        long tamaño = maestro.length();
        System.out.println("Despl: " + desplazamiento());
        System.out.println("Posic: " + inx.getPosicion());
        System.out.println("Seek : " + desplazamiento() * (inx.getPosicion()-1));
        System.out.println("Tamañ: " + tamaño);

        long seek = desplazamiento() * (inx.getPosicion() - 1);
        if(tamaño > 0){
            maestro.seek(seek);
        }

        System.out.println("puntero del archivo: " + maestro.getFilePointer());

        if(inx.getExistente() != -1){
            maestro.readInt();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 15; j++)
                    maestro.readChar();
                puntos_criticos[i][0] = maestro.readInt();
                puntos_criticos[i][1] = maestro.readInt();
            }
        } else {
            System.out.println("No existe una competencia con esa llave");
        }

        System.out.println("puntero del archivo: " + maestro.getFilePointer());

        for (int i = 0; i < 8; i++) {
                System.out.println("[" + puntos_criticos[i][0] + "] [" + puntos_criticos[i][1] + "]");

        }

        return puntos_criticos;
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

    /*
    public void escribirMaestro() throws IOException {
        int bandera = 1, existente, n;
        int[] res;
        String[] aux;

        StringBuffer datos;
        long tamaño;
        String premisa, salida, prueba, dato;
        RandomAccessFile Maestro;
        Scanner entrada = new Scanner(System.in);
        do {
            String[] premisas = new String[configuracion];
            Maestro = new RandomAccessFile("Maestro", "rw");
            tamaño = Maestro.length();
            Maestro.seek(tamaño);//nos vamos hasta el final del archivo
            System.out.println("ingrese el numero de regla a ingresar");
            existente = entrada.nextInt();
            res = indice.buscarIndice(existente);
            if (res[0] == 1) {
                System.out.println("Regla ya existente");
                System.out.println("Presione 0 para terminar o 1 para ingresar una nueva regla");
                bandera = entrada.nextInt();
            } else {
                indice.escribir_indice(existente);//escribimos la entrada en el indice
                Maestro.writeInt(existente);//escribimos la regla en el maestro
                System.out.println("ingrese las premisas separadas por una coma p1,p2,p3");
                premisa = entrada.next();
                aux = premisa.split(",");
                for (int i = 0; i < aux.length; i++) {
                    premisas[i] = aux[i];
                }
                //Esto es un cambio hasta el siguiente comentario  de cierre de cambio

                for (n = 0; n < configuracion; n++) {
                    if (premisas[n] != null) {
                        dato = premisas[n];
                        datos = new StringBuffer(dato);
                        datos.setLength(largo);
                        prueba = datos.toString();
                        Maestro.writeChars(prueba);
                        iPremisas.EscribirPremisa(prueba, existente);
                    } else {
                        dato = "Pv";
                        datos = new StringBuffer(dato);
                        datos.setLength(largo);
                        prueba = datos.toString();
                        Maestro.writeChars(prueba);
                    }
                }//fin for cuando salga del for ya tiene que haber escrito todas las premisas ingresadas
                dato = "->";
                datos = new StringBuffer(dato);
                datos.setLength(largo);
                prueba = datos.toString();
                Maestro.writeChars(prueba);
                System.out.println("ingrese la salida !solo una premisa!");
                dato = entrada.next();
                datos = new StringBuffer(dato);
                datos.setLength(largo);
                prueba = datos.toString();
                Maestro.writeChars(prueba);
                Maestro.close();
                System.out.println("Presione 0 para terminar o 1 para ingresar una nueva regla");
                bandera = entrada.nextInt();
            }//fin else
        } while (bandera != 0);
    }

    public long desplazamiento() throws IOException {
        long desp;
        int i, c;
        char[] dato = new char[largo];
        RandomAccessFile file = new RandomAccessFile("Maestro", "rw");
        file.readInt();//lee la existente
        for (i = 0; i < configuracion; i++) {
            for (c = 0; c < dato.length; c++) {
                dato[c] = file.readChar();
            }
        }
        for (c = 0; c < dato.length; c++) {//este solo lee el caracter de la condicional para avanzar
            dato[c] = file.readChar();
        }//fin del f
        for (c = 0; c < dato.length; c++) {//este solo lee el caracter de la condicional para avanzar
            dato[c] = file.readChar();
        }//fin del f
        desp = file.getFilePointer();
        file.close();
        return desp;
    }

    public void buscarReglaAleatorio(int existente) throws IOException {
        RandomAccessFile file;
        int posicion, i, dt, key, c;
        char temp;
        int[] pos;
        long longitud = desplazamiento();
        long desplazamiento;
        char[] premisa = new char[largo];
        String[] premisas = new String[configuracion];//del tamaño de las configuraciones*2
        String puente, salida = "", predicado = "";
        pos = indice.buscarIndice(existente);
        posicion = pos[1];
        System.out.println("posicion:" + posicion);
        if (pos[0] == -1) {
            System.out.println("el registro no existe");
        } else {
            file = new RandomAccessFile("Maestro", "rw");
            desplazamiento = (posicion - 1) * longitud;
            System.out.println("desplazamiento:" + desplazamiento);
            System.out.println("longitud:" + longitud);
            file.seek(desplazamiento);
            key = file.readInt();
            //System.out.println(key);
            for (i = 0; i < configuracion; i++) {//este for debe leer todas las premisas antes del ->
                //de nuevo corregir el 8 por variable configuraciones
                for (c = 0; c < premisa.length; c++) {
                    temp = file.readChar();
                    predicado = predicado + temp;
                    //premisa[c]=temp;
                }//fin del for
                //puente=premisa.toString();
                premisas[i] = predicado;
                predicado = "";
            }//fin del for
            for (c = 0; c < premisa.length; c++) {//este solo lee el caracter de la condicional para avanzar
                premisa[c] = file.readChar();
            }//fin del for
            for (c = 0; c < premisa.length; c++) {//// obtiene la premisa de salida
                temp = file.readChar();
                predicado = predicado + temp;
                //premisa[c]=file.readChar();
            }//fin del for
            puente = predicado;
            for (c = 0; c < premisas.length; c++) {
                salida = salida + premisas[c] + "^";
            }
            salida = salida + "->" + puente;
            System.out.println(key + ".-" + salida);
            file.close();
        }//fin else mas exterior
    }

    public void borrarRegistro() throws IOException {
        int existente;
        Scanner entrada = new Scanner(System.in);
        System.out.println("ingrese la regla a borrar");
        existente = entrada.nextInt();
        indice.borrarIndice(existente);
    }

    public void actualizarRegistro() throws IOException {
        int existente, pos, n;
        int[] posicion;
        String[] aux, premisas = new String[configuracion];;
        StringBuffer datos;
        long longitud, desplazamiento;
        String premisa, prueba, dato;
        RandomAccessFile Maestro = new RandomAccessFile("Maestro", "rw");
        Scanner entrada = new Scanner(System.in);
        indice in = new indice();

        existente = Integer.parseInt(JOptionPane.showInputDialog(null, "Escribe el numero de regla a actualizar", "Numero de Regla", 1));

        posicion = in.buscarIndice(existente);
        longitud = desplazamiento();
        pos = posicion[1];
        desplazamiento = (pos - 1) * longitud;
        Maestro.seek(desplazamiento);
        Maestro.readInt();

        premisa = JOptionPane.showInputDialog(null, "Escribe las premisas separadas por comas, por ejemplo: (p1,p2,p3)", "Ingresa las premisas", 2);

        aux = premisa.split(",");
        for (int i = 0; i < aux.length; i++) {
            premisas[i] = aux[i];
        }

        //Esto es un cambio hasta el siguiente comentario  de cierre de cambio
        for (n = 0; n < configuracion; n++) {
            if (premisas[n] != null) {
                dato = premisas[n];
                datos = new StringBuffer(dato);
                datos.setLength(largo);
                prueba = datos.toString();
                Maestro.writeChars(prueba);
            } else {
                dato = "Pv";
                datos = new StringBuffer(dato);
                datos.setLength(largo);
                prueba = datos.toString();
                Maestro.writeChars(prueba);
            }
        } // Premisas Escritas

        dato = "->";
        datos = new StringBuffer(dato);
        datos.setLength(largo);
        prueba = datos.toString();
        Maestro.writeChars(prueba);
        dato = JOptionPane.showInputDialog(null, "Escribe la premisa de salida (Solo una premisa).", "Consecuente", 1);
        datos = new StringBuffer(dato);
        datos.setLength(largo);
        prueba = datos.toString();
        Maestro.writeChars(prueba);
        Maestro.close();
    }



    public void buscar_ArchivoPC(int num_etiqueta) {
        //leer e ir comparando con el numero de competencia hasta encontrarlo
        valor_X1=0;//primer punto critico encontrado

        num_etiqueta++;
        //leer e ir comparando con el numero de competencia hasta encontrarlo
        valor_XX2=0;//=//primer punto critico encontrado

    }


    public int getNum_etiqueta(){
        return num_etiqueta;
    }

    public static String[] Consultar_archivo_PC()
    {
        String[] puntosCriticos = new String[8];
        return puntosCriticos;
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
    */
}
