package Proceso;

import java.io.IOException;
import java.util.ArrayList;

public class FAM {
    Nodo Inicio = new Nodo(0);
    Maestro maestro = new Maestro();

    public void agregarEtiquetas(int Llave) throws IOException {
        ArrayList<Competencia> aux = maestro.buscarCompetencia(Llave);
        ArrayList<Competencia> etiquetas = new ArrayList<Competencia>();

        for (int i = 0; i < aux.size(); i++)
            if(aux.get(i).getP1() != -1 || aux.get(i).getP2() != -1)
                etiquetas.add(aux.get(i));

        System.out.println("Cantidad etiquetas = " + etiquetas.size());

        for (int i = 0; i < etiquetas.size(); i++) {
            Inicio.agregarNodo(new Nodo(0), 1);
        }
    }
}