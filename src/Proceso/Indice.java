package Proceso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Indice {

    int longitud = 108;
    RandomAccessFile indice;
    int existente;
    int posicion;
    String competencia;

    public Indice(){

    }

    public Indice (int existente, int posicion, String competencia){
        this.existente = existente;
        this.posicion = posicion;
        this.competencia = competencia;
    }

    public Indice buscarIndice(int llave)throws IOException {
        indice = new RandomAccessFile("indice.dat","rw");
        int llaveLeida, posicion = -1, i, existe = -1;
        long tamaño = indice.length();
        String competencia = "";

        if(tamaño == 0){
            existe = -1;
            posicion = 1;
        } else {
            for (i = 0; i < tamaño/8 ; i++){
                llaveLeida = indice.readInt();
                if(llave == llaveLeida){
                    existe = 1;
                    posicion = i + 1;
                    indice.readInt();
                    for (int j = 0; j < 50; j++)
                        competencia = competencia + indice.readChar();
                    break;
                } else {
                    existe = -1;
                    posicion = i + 1;
                    indice.readInt();
                }
            }//fin del for
        }//fin else

        indice.close();
        return new Indice(existe, posicion, competencia);
    }

    public void leerSecuencial() throws IOException {
        indice = new RandomAccessFile("indice.dat","rw");
        int llaveLeida, posicion = -1, i, existe = -1;
        long tamaño = indice.length();
        String competencia = "";

        if(tamaño != 0){
            System.out.println("Llave\tPosicion\tCompetencia");
            while(indice.getFilePointer() < tamaño) {
                System.out.print(indice.readInt() + "\t" + indice.readInt() + "\t");
                for (int j = 0; j < 50; j++) {
                    System.out.print(indice.readChar());
                }
                System.out.println();
            }
        } else {
        }

        indice.close();
    }

    public boolean escribir_indice(int llave, String etiqueta)throws IOException{
        boolean escrito;
        long longitud;
        Indice respuesta;
        RandomAccessFile file;
        respuesta = buscarIndice(llave);
        if(respuesta.getExistente() == -1){
            file = new RandomAccessFile("indice.dat","rw");
            longitud = file.length();
            file.seek(longitud);
            file.writeInt(llave);

            if(respuesta.getPosicion() == 1)
                file.writeInt(1);
            else
                file.writeInt(respuesta.getPosicion() + 1);

            for (int i = 0; i < etiqueta.length(); i++) {
                file.writeChar(etiqueta.charAt(i));
            }
            for (int i = 0; i < 50 - etiqueta.length(); i++) {
                file.writeChar(' ');
            }

            file.close();
            escrito = true;
        }
        else
            escrito = false;
        return escrito;
    }

    public void borrarIndice(int llave) throws IOException {
        Indice posicion;
        int pos;
        long longitud, desplazamiento;
        RandomAccessFile file;
        posicion = buscarIndice(llave);
        if(posicion.getExistente() == 1){
            file = new RandomAccessFile("indice.dat","rw");
            longitud = desplazamiento();
            pos = posicion.getPosicion();
            desplazamiento = (pos - 1) * longitud;
            file.seek(desplazamiento);
            file.writeInt(-1);
        }
        else
            System.out.println("intento borrar un registro que no existe");
    }

    public long desplazamiento() throws IOException {
        long desp;
        RandomAccessFile file = new RandomAccessFile("indice.dat","rw");
        file.readInt();
        file.readInt();
        for (int i = 0; i < 50; i++) {
            file.readChar();
        }
        desp = file.getFilePointer();
        file.close();
        return desp;
    }


    public int getExistente() {
        return existente;
    }

    public void setExistente(int existente) {
        this.existente = existente;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }
}
