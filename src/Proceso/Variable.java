package Proceso;

public class Variable {
    String etiqueta;
    double x;
    double y;

    public Variable(){

    }

    public Variable(String etiqueta, double x, double ye) {
        this.etiqueta = etiqueta;
        this.x = x;
        this.y = y;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
