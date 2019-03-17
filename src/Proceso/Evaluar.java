package Proceso;

public class Evaluar {

    public void Evaluer(int entrara_real) {
        int PC1 = 1, PC2 = 1, PC3 = 1;
        int primera_etiqueda = 0, ultima_etiqueta = 0;
        int entrada_real = 0;

        //String[] puntoss = Consultar_archivo_PC();


        if (entrada_real == PC1) {
            PC1 = 1;
            PC2 = 0;
            PC3 = 0;
        } else {
            if (entrada_real == PC2) {
                PC1 = 0;
                PC2 = 1;
                PC3 = 0;
            } else {
                if (entrada_real == PC3) {
                    PC1 = 0;
                    PC2 = 0;
                    PC3 = 1;
                } else {
                    if (entrada_real <= primera_etiqueda) {
                        PC1 = 1;
                        PC2 = 0;
                        PC3 = 3;
                    } else {
                        if (entrada_real >= ultima_etiqueta) {
                            PC1 = 0;
                            PC2 = 0;
                            PC3 = 1;
                        } else {
                            //aqui viene lo chido
                            int pt_izquierda=0, pt_derecha=0,st_izquierda=0,st_derecha=0;
                            ////extraer los traslapes
                            if (entrada_real< pt_izquierda)
                            {
                                //llamar calcular pendiente
                                Double pertenenciaY  = Pendiente(entrada_real,PC1,1,pt_derecha,0);
                                System.out.println();
                            }
                        }
                    }
                }
            }
        }
    }//metodo

    public double Pendiente(int x,double x1,double y1,double x2,double y2){
        double y = 0.0;
        y = ((x-x1)*(y2-y1)/(x2-x1)) + y1;
        return y;
    }


}//class

