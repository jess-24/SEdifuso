package Proceso;

import java.io.IOException;
import java.util.*;

public class FAM {
    Maestro maestro = new Maestro();
    Indice indice = new Indice();
    int dim_row = 0, dim_col = 0;

    public double[][] generarMatriz(ArrayList<variableSalida> grados_membresia) throws IOException {
        ArrayList<Indice> columna = new ArrayList<Indice>(); // Arreglo que almacenara los datos que van del lado de arriba
        ArrayList<Indice> fila = new ArrayList<Indice>(); // arreglo que almacenará los datos que van del lado izquierdo
        ArrayList<Indice> ordenada = ordenarMatriz(); // Ordena la matriz por jerarquia de competencias


        /*
        System.out.println("Grados de membresia");
        for (int k = 0; k < grados_membresia.size(); k++) {
            System.out.println("Tam ext: " + grados_membresia.size() + ", Tam int: " + grados_membresia.get(k).getVariables().size());
            for (int i = 0; i < grados_membresia.get(k).getVariables().size(); i++) {
                System.out.println("k = " + k + ": " + grados_membresia.get(k).getNumCompetencias() + ", " + grados_membresia.get(k).getVariables().get(i).getEtiqueta() + " " + grados_membresia.get(k).getVariables().get(i).getY());
            }
        }
        //System.out.println("------");
        //System.out.println("Grados de membresia ordenados");

        */
        ArrayList<variableSalida> gm_ordenados = ordenarMatriz(grados_membresia);
        /*
        for (int k = 0; k < gm_ordenados.size(); k++) {
            System.out.println("Tam ext: " + gm_ordenados.size() + ", Tam int: " + gm_ordenados.get(k).getVariables().size());
            for (int i = 0; i < gm_ordenados.get(k).getVariables().size(); i++) {
                System.out.println("k = " + k + ": " + gm_ordenados.get(k).getNumCompetencias() + ", " + gm_ordenados.get(k).getVariables().get(i).getEtiqueta() + " " + gm_ordenados.get(k).getVariables().get(i).getX() + " " + gm_ordenados.get(k).getVariables().get(i).getY());
            }
        }
        */
        for (int i = 0; i < ordenada.size(); i++) { // Separa las competencias
            if(i % 2 == 0) {
                //System.out.println("↑\t" + ordenada.get(i).getExistente() + " " + ordenada.get(i).getCompetencia());
                columna.add(ordenada.get(i));
            }else{
                //System.out.println("←\t" + ordenada.get(i).getExistente() + " " + ordenada.get(i).getCompetencia());
                fila.add(ordenada.get(i));
            }
        }

        dim_row = calcularDimension(fila); // Cantidad de filas
        dim_col = calcularDimension(columna); // Cantidad de columnas
        double matrizFam[][] = new double[dim_row][dim_col]; // Matriz FAM
        //System.out.println(calcularDimension(fila) + " x " + calcularDimension(columna));
        int etiqueta_indice = 0, competencia_indice = 0;

        int tam_comp_a[][] = etiquetasPorCompetencia(columna);
        int ind_a[] = new int[columna.size()];
        for (int i = 0; i < columna.size(); i++)
            ind_a[i] = 0;

        int tam_comp_i[][] = etiquetasPorCompetencia(fila);
        int ind_i[] = new int[fila.size()];
        for (int i = 0; i < fila.size(); i++)
            ind_i[i] = 0;

        for (int i = 0; i < dim_row; i++) { // Recorre filas
            //System.out.println(i); // Numero Iteracion / Renglon

            if(i > 0 && i % 27 == 0){
                ind_i[0]++;
                ind_i[1] = 0;
                ind_i[2] = 0;
                ind_i[3] = 0;
            }
            if(i > 0 && i % 9 == 0 && i % 27 != 0){
                ind_i[1]++;
                ind_i[2] = 0;
                ind_i[3] = 0;
            }
            if(i > 0 && i % 3 == 0 && i % 9 != 0){
                ind_i[2]++;
                ind_i[3] = 0;
            }

            for (int j = 0; j < dim_col; j++) {  // Recorre columnas
                if(j > 0 && j % 27 == 0){
                    ind_a[0]++;
                    ind_a[1] = 0;
                    ind_a[2] = 0;
                    ind_a[3] = 0;
                }
                if(j > 0 && j % 9 == 0 && j % 27 != 0){
                    ind_a[1]++;
                    ind_a[2] = 0;
                    ind_a[3] = 0;
                }
                if(j > 0 && j % 3 == 0 && j % 9 != 0){
                    ind_a[2]++;
                    ind_a[3] = 0;
                }

                /*
                for (int k = 0; k < 4; k++) {
                    System.out.println("ind_a[" + k + "] = " + ind_a[k]);
                }
                System.out.println("--");
                for (int k = 0; k < 4; k++) {
                    System.out.println("ind_i[" + k + "] = " + ind_i[k]);
                }
                System.out.println("----- Fin de iteracion " + j);
                */

                matrizFam[i][j] = minimo(gm_ordenados.get(0).getVariables().get(ind_a[0]).getY(),
                                        gm_ordenados.get(2).getVariables().get(ind_a[1]).getY(),
                                        gm_ordenados.get(4).getVariables().get(ind_a[2]).getY(),
                                        gm_ordenados.get(6).getVariables().get(ind_a[3]).getY(),
                                        gm_ordenados.get(1).getVariables().get(ind_i[0]).getY(),
                                        gm_ordenados.get(3).getVariables().get(ind_i[1]).getY(),
                                        gm_ordenados.get(5).getVariables().get(ind_i[2]).getY(),
                                        gm_ordenados.get(7).getVariables().get(ind_i[3]).getY());
                ind_a[3]++;
            }
            ind_a[0] = 0;
            ind_a[1] = 0;
            ind_a[2] = 0;
            ind_a[3] = 0;

            ind_i[3]++;

        }
/*
        for (int i = 0; i < dim_row; i++) {
            for (int j = 0; j < dim_col; j++) {
                System.out.print("[" + matrizFam[i][j] + "]");
                if(j % 27 == 0 && j > 0)
                    System.out.print(" | ");
            }
            System.out.println();
            if(i % 27 == 0 && i > 0){
                for (int j = 0; j < (5 * dim_col) + 6; j++) {
                    System.out.print("-");
                }
                System.out.println();
            }
        }
*/
        //System.out.println("FiN FAM");
        return matrizFam;
    }

    public double minimo(double a, double b, double c, double d, double e, double f, double g, double h){
        double min;

        min = (a < b) ? a : b;
        min = (min < c) ? min : c;
        min = (min < d) ? min : d;
        min = (min < e) ? min : e;
        min = (min < f) ? min : f;
        min = (min < g) ? min : g;
        min = (min < h) ? min : h;

        /*
        if(min != 0){
            System.out.println("A: " + a);
            System.out.println("B: " + b);
            System.out.println("C: " + c);
            System.out.println("D: " + d);
            System.out.println("E: " + e);
            System.out.println("F: " + f);
            System.out.println("G: " + g);
            System.out.println("H: " + h);
            System.out.println("Min: " + min);
            System.out.println("----------------");
        }
        */
        return min;
    }

    public int[][] etiquetasPorCompetencia(ArrayList<Indice> aux) throws IOException {
        int tam_comp[][] = new int[aux.size()][2];
        for (int i = 0; i < aux.size(); i++) {
            tam_comp[i][0] = aux.get(i).getExistente();
            tam_comp[i][1] = cantidadEtiquetas(maestro.buscarCompetencia(aux.get(i).getExistente()));
        }
        return tam_comp;
    }

    public ArrayList<Indice> ordenarMatriz() throws IOException {
        ArrayList<Indice> todasComp = indice.obtenerTodasCompetencias();
        ArrayList<Indice> ordenada = indice.obtenerTodasCompetencias();

        for (int i = 0; i < todasComp.size(); i++) {
            switch (todasComp.get(i).getExistente()){
                case 1:
                    ordenada.set(2, todasComp.get(i));
                    break;
                case 2:
                    ordenada.set(0, todasComp.get(i));
                    break;
                case 3:
                    ordenada.set(1, todasComp.get(i));
                    break;
                case 4:
                    ordenada.set(5, todasComp.get(i));
                    break;
                case 5:
                    ordenada.set(3, todasComp.get(i));
                    break;
                case 6:
                    ordenada.set(4, todasComp.get(i));
                    break;
                case 7:
                    ordenada.set(6, todasComp.get(i));
                    break;
                case 8:
                    ordenada.set(7, todasComp.get(i));
                    break;
            }
        }
        return ordenada;
    }

    public ArrayList<variableSalida> ordenarMatriz(ArrayList<variableSalida> arr){
        ArrayList<variableSalida> aux = arr;
        ArrayList<variableSalida> temp = new ArrayList<variableSalida>();

        for (int i = 0; i < aux.size(); i++) {
            temp.add(aux.get(i));
        }

        for (int i = 0; i < aux.size(); i++) {
            switch (aux.get(i).getNumCompetencias()){
                case 1:
                    temp.set(2, aux.get(i));
                    break;
                case 2:
                    temp.set(0, aux.get(i));
                    break;
                case 3:
                    temp.set(1, aux.get(i));
                    break;
                case 4:
                    temp.set(5, aux.get(i));
                    break;
                case 5:
                    temp.set(3, aux.get(i));
                    break;
                case 6:
                    temp.set(4, aux.get(i));
                    break;
                case 7:
                    temp.set(6, aux.get(i));
                    break;
                case 8:
                    temp.set(7, aux.get(i));
                    break;
            }
        }
        return temp;
    }

    public int calcularDimension(ArrayList<Indice> arr) throws IOException {
        int cant = 0, dim = 1;
        ArrayList<Competencia> aux;

        for (int i = 0; i < arr.size(); i++) {
            aux = maestro.buscarCompetencia(arr.get(i).getExistente());
            for (int j = 0; j < aux.size(); j++)
                if(aux.get(j).getP1() != -1 || aux.get(j).getP2() != -1)
                    cant ++;
            dim = dim * cant;
            cant = 0;
        }
        return dim;
    }

    public int cantidadEtiquetas(ArrayList<Competencia> aux){
        int cant = 0;
        for (int j = 0; j < aux.size(); j++)
            if(aux.get(j).getP1() != -1 || aux.get(j).getP2() != -1)
                cant ++;

        return cant;
    }
    ArrayList<Double> insuficiente;
    ArrayList<Double> suficiente;
    ArrayList<Double> bueno;

    public void sacar_maximos(double[][] fam) {
        //obtener el numero de etiquetas de la competencia mas interna izquierdo y arriba
        int num_etiquetas_izq=3,num_etiquetas_arriba=3;
        insuficiente=new ArrayList<Double>();
        suficiente=new ArrayList<Double>();
        bueno=new ArrayList<Double>();
        char[] con={'I','I','S','I','S','B','S','B','B'};
        char consecuente=con[0];
        //determina el cambio de insuficiente, suficiente y bueno
        int cambioIzq=fam.length/num_etiquetas_izq;
        int contadorIz=1,contadorArriba=1;
        int i=0;
        int cambioArriba=fam[i].length/num_etiquetas_arriba;
        boolean cambio=false;
        for (int fila = 0; fila < fam.length; fila++) { //cantidad de filas
            //if(contadorIz<=cambioIzq)
            for (int col=0; col < fam[fila].length; col ++){ //cant columnas
                if (contadorIz<=cambioIzq){
                    if (contadorArriba<=cambioArriba){
                        //System.out.println("que tiene iiiiiiiiiiii"+i);
                        if (contadorArriba==cambioArriba){
                            consecuente=con[i];
                            if (i!=8)
                                i++;
                        }
                        //leera la primer regla seek(0) y traera el consecuente
                        switch (consecuente){
                            case 'I': insuficiente.add(fam[fila][col]);
                                break;
                            case 'S': suficiente.add(fam[fila][col]);
                                break;
                            case 'B': bueno.add(fam[fila][col]);
                                break;
                        }
                    }
                }
                if (contadorArriba==cambioArriba){
                    contadorArriba=0;
                }
                contadorArriba++;
            }
            if (contadorIz==cambioIzq){

                contadorIz=0;
            }
            contadorIz++;
            if (contadorIz!=cambioIzq)
                i-=2;
        }

        //System.out.println("maximooo insuf: "+getMaxInsuficiente());
        //System.out.println("max suf: "+getMaxSuficiente());
        //System.out.println("max bueno: "+getMaxBueno());
    }
    public double getMaxInsuficiente(){
        Comparator<Double> comparador = Collections.reverseOrder();
        if (insuficiente.isEmpty()){
            insuficiente.add(0.0);
        }
        Collections.sort(insuficiente, comparador);
        return insuficiente.get(0);
    }
    public double getMaxSuficiente(){
        Comparator<Double> comparador = Collections.reverseOrder();
        if (suficiente.isEmpty()){
            suficiente.add(0.0);
        }
        Collections.sort(suficiente, comparador);
        return suficiente.get(0);
    }
    public double getMaxBueno(){
        Comparator<Double> comparador = Collections.reverseOrder();
        if (bueno.isEmpty()){
            bueno.add(0.0);
        }
        Collections.sort(bueno, comparador);
        return bueno.get(0);
    }


    private double membresia(double calificacion, int[] rango, double maximo) {
        double gMembresia = 0;
        int rangoM, rangoMax, puntoCritico1, puntoCritico2;
        if (maximo > 0) {
            rangoM = rango[0];
            rangoMax = rango[1];
            if (rango[0] >= 0) {
                if (rango[1] == -1) {
                    puntoCritico1 = (rango[0] == 0) ? rango[0] : rango[0] - 10;
                    puntoCritico2 = (rango[0] == 100) ? rango[0] : rango[0] + 10;
                    if (calificacion >= puntoCritico1 && calificacion <= puntoCritico2) {
                        if (calificacion == rangoM)
                            return maximo;
                        if (calificacion < rangoM)
                            gMembresia = (double) (calificacion - puntoCritico1) / (rangoM - puntoCritico1);
                        if (calificacion > rangoM)
                            gMembresia = (double) (puntoCritico2 - calificacion) / (puntoCritico2 - rangoM);
                    } else
                        return 0;
                } else {
                    puntoCritico1 = (rango[0] == 0) ? rango[0] : rango[0] + 10;
                    puntoCritico2 = (rango[1] == 100) ? rango[1] : rango[1] - 10;
                    if (calificacion >= rangoM && calificacion <= rangoMax) {
                        if (puntoCritico1 <= calificacion && calificacion <= puntoCritico2)
                            return maximo;
                        if (calificacion < puntoCritico1)
                            gMembresia = (double) (calificacion - rangoM) / (puntoCritico1 - rangoM);
                        if (calificacion > puntoCritico2)
                            gMembresia = (double) (rangoMax - calificacion) / (rangoMax - puntoCritico2);
                    } else
                        return 0;
                }
            } else
                return 0;
            return gMembresia > maximo ? maximo : gMembresia;
        } else
            return 0;
    }

    public ArrayList<Competencia> generarConsecuente() {
        ArrayList<Competencia> cons = new ArrayList<Competencia>();
        cons.add(new Competencia("Insuficiente", 0, 45));
        cons.add(new Competencia("Suficiente", 50, 85));
        cons.add(new Competencia("Bueno", 90, 100));
        return cons;
    }

    public double centroide(double gmInsuficiente, double gmSuficiente, double gmBueno, ArrayList<Competencia> cons) {
        double sum = 0;
        double prod = 0;
        int punto1[] = {cons.get(0).getP1(), cons.get(0).getP2()};
        int punto2[] = {cons.get(1).getP1(), cons.get(1).getP2()};
        int punto3[] = {cons.get(2).getP1(), cons.get(2).getP2()};

        for (double i = 0.0; i < 100.0; i += 1) {
            prod += i * membresia(i, punto1, gmInsuficiente);
            prod += i * membresia(i, punto2, gmSuficiente);
            prod += i * membresia(i, punto3, gmBueno);
            sum += membresia(i, punto1, gmInsuficiente);
            sum += membresia(i, punto2, gmSuficiente);
            sum += membresia(i, punto3, gmBueno);
        }
        return prod / sum;
    }
}