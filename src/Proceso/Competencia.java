package Proceso;

public class Competencia {
    String etiqueta;
    int p1, p2;

    public Competencia(){

    }

    public Competencia(String etiqueta, int p1, int p2) {
        this.etiqueta = etiqueta;
        this.p1 = p1;
        this.p2 = p2;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public int getP1() {
        return p1;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public int getP2() {
        return p2;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }
}
