package Proceso;

import java.io.IOException;
import java.util.ArrayList;

public class FAM {
    Maestro maestro = new Maestro();
    Indice indice = new Indice();
    int dim_row = 0, dim_col = 0;

    public void generarMatriz() throws IOException {
        ArrayList<Indice> columna = new ArrayList<Indice>(); // Arreglo que almacenara los datos que van del lado de arriba
        ArrayList<Indice> fila = new ArrayList<Indice>(); // arreglo que almacenará los datos que van del lado izquierdo
        ArrayList<Indice> ordenada = ordenarMatriz(); // Ordena la matriz por jerarquia de competencias

        for (int i = 0; i < ordenada.size(); i++) { // Separa las competencias
            if(i % 2 == 0) {
                System.out.println("↑\t" + ordenada.get(i).getExistente() + " " + ordenada.get(i).getCompetencia());
                columna.add(ordenada.get(i));
            }else{
                System.out.println("←\t" + ordenada.get(i).getExistente() + " " + ordenada.get(i).getCompetencia());
                fila.add(ordenada.get(i));
            }
        }

        dim_row = calcularDimension(fila); // Cantidad de filas
        dim_col = calcularDimension(columna); // Cantidad de columnas
        double matrizFam[][] = new double[dim_row][dim_col]; // Matriz FAM
        System.out.println(calcularDimension(fila) + " x " + calcularDimension(columna));
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
            for (int j = 0; j < dim_col; j++) {  // Recorre columnas
                if(j % 27 == 0){
                    ind_a[0]++;
                    ind_a[1] = 0;
                }
                if(j % 9 == 0){
                    ind_a[1]++;
                    ind_a[2] = 0;
                }
                if(j % 3 == 0){
                    ind_a[2]++;
                    ind_a[3] = 0;
                }
                if(i % 27 == 0){
                    ind_i[0]++;
                    ind_i[1] = 0;
                }
                if(i % 9 == 0){
                    ind_i[1]++;
                    ind_i[2] = 0;
                }
                if(i % 3 == 0){
                    ind_i[2]++;
                    ind_i[3] = 0;
                }
                matrizFam[i][j] = 0;// aqui se deberia agregar el minimo, pero ya no se como hacerlo, estoy muerto
            }
            System.out.println(i); // Numero Iteracion / Renglon
        }


        System.out.println("FiN FAM");
    }

    public int minimo(int a, int b, int c, int d, int e, int f, int g, int h){
        return 0;
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
}