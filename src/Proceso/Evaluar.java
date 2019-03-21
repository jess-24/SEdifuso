package Proceso;

public class Evaluar {

    public void Evaluar(int entrara_real, int[][] puntosC) {
        int X,X2,Y, posicion;
        String etiqueta;

        int entrada_real = 0;
        double[][] resultado = new double[100][2];
        //String[] puntoss = Consultar_archivo_PC();


for (int i=0;i<puntosC.length; i++)
{
 for (int j=0;i<2; j++)
 {
        if (entrada_real == puntosC[i][j]) {
                etiqueta="";
                X=puntosC[i][j];
                Y=1;
                posicion=i;
        } else {
            if ((entrada_real < puntosC[i][j] && puntosC[i][j-1]==0)|| (entrada_real > puntosC[i][j] && puntosC[i][j+1]==0) ) {
                etiqueta="";
                X=puntosC[i][j];
                Y=1;
                posicion=i;
            } else {
                if (entrada_real > puntosC[i][j] && puntosC[i][j+1]!=-1 && j==0 && entrada_real < puntosC[i][j+1]) {
                    X=puntosC[i][j];
                    X2=puntosC[i][j+1];
                    Y=1;
                    posicion=i;
                } else {

                }
            }
        }

}}
    }//metodo

    public double Pendiente(int x,double x1,double y1,double x2,double y2){
        double y = 0.0;
        y = ((x-x1)*(y2-y1)/(x2-x1)) + y1;
        return y;
    }


}//class

