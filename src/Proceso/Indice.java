package Proceso;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Indice {

    int longitud = 108;
    RandomAccessFile indice;

    public int[] buscarIndice(int llave)throws IOException {
        indice = new RandomAccessFile("indice.dat","rw");
        int llaveLeida, posicion = -1, i, existe = -1;
        int respuesta[] = new int[2];
        long tamaño = indice.length();

        if(tamaño==0){
            existe=-1;
            posicion=1;
        }

        else {
            for (i=0;i<tamaño/8;i++){
                llaveLeida= indice.readInt();
                if(llave==llaveLeida){
                    existe=1;
                    posicion=i+1;
                    indice.readInt();
                    break;
                }
                else{
                    existe=-1;
                    posicion=i+1;
                    indice.readInt();
                }
            }//fin del for
        }//fin else
        respuesta[0]=existe;
        respuesta[1]=posicion;
        indice.close();
        return respuesta;
    }

    public boolean escribir_indice(int llave)throws IOException{
        boolean escrito;
        long longitud;
        int [] respuesta;
        RandomAccessFile file;
        respuesta=buscarIndice(llave);
        if(respuesta[0]==-1){
            file=new RandomAccessFile("indice.dat","rw");
            longitud=file.length();
            file.seek(longitud);
            file.writeInt(llave);
            if(respuesta[1]==1)
                file.writeInt(1);
            else
                file.writeInt(respuesta[1]+1);
            file.close();
            escrito=true;
        }
        else
            escrito=false;

        return escrito;
    }
    public void borrarIndice(int llave) throws IOException {
        int[] posicion;
        int pos;
        long longitud,desplazamiento;
        RandomAccessFile file;
        posicion=buscarIndice(llave);
        if(posicion[0]==1){
            file=new RandomAccessFile("indice","rw");
            longitud=desplazamiento();
            pos=posicion[1];
            desplazamiento=(pos-1)*longitud;
            file.seek(desplazamiento);
            file.writeInt(-1);
        }
        else
            System.out.println("intento borrar un registro que no existe");
    }
    public long desplazamiento() throws IOException {
        long desp;
        RandomAccessFile file=new RandomAccessFile("indice","rw");
        file.readInt();
        file.readInt();
        desp=file.getFilePointer();
        file.close();
        return desp;
    }
}
