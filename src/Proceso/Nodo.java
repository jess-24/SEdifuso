package Proceso;

public class Nodo {
    Nodo Siguiente;
    double grados_mem;
    int indice_hijo;

    public Nodo(double Grados_Membresia){
        Siguiente = null;
        grados_mem = Grados_Membresia;
    }

    public void agregarNodo(Nodo Siguiente, int Indice){
        this.Siguiente = Siguiente;
        this.indice_hijo = Indice;
    }
}