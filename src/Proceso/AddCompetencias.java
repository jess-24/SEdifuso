package Proceso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class AddCompetencias extends JFrame {
    private JTextField tfEtiqueta1;
    private JTextField tfEtiqueta1_p1;
    private JTextField tfEtiqueta3;
    private JTextField tfEtiqueta4;
    private JTextField tfEtiqueta5;
    private JTextField tfEtiqueta6;
    private JTextField tfEtiqueta7;
    private JTextField tfEtiqueta8;
    private JTextField tfEtiqueta2_p1;
    private JTextField tfEtiqueta3_p1;
    private JTextField tfEtiqueta4_p1;
    private JTextField tfEtiqueta5_p1;
    private JTextField tfEtiqueta6_p1;
    private JTextField tfEtiqueta7_p1;
    private JTextField tfEtiqueta8_p1;
    private JTextField tfLlave;
    private JTextField tfCompetencia;
    private JButton btnGuardar;
    private JPanel panel_addC;
    private JTextField tfEtiqueta2;
    private JTextField tfEtiqueta1_p2;
    private JTextField tfEtiqueta2_p2;
    private JTextField tfEtiqueta3_p2;
    private JTextField tfEtiqueta4_p2;
    private JTextField tfEtiqueta5_p2;
    private JTextField tfEtiqueta6_p2;
    private JTextField tfEtiqueta7_p2;
    private JTextField tfEtiqueta8_p2;


    Indice indice = new Indice();
    RandomAccessFile maestro;
    long tamaño;
    Indice res;
    int bandera = 1;
    String aux[];
    Scanner scan = new Scanner(System.in);

    public int llave;
    public String competencia,etiqueta1,etiqueta2,etiqueta3,etiqueta4,etiqueta5,etiqueta6,etiqueta7,etiqueta8;
    public String e1_p1,e2_p1,e3_p1,e4_p1,e5_p1,e6_p1,e7_p1,e8_p1,e1_p2,e2_p2,e3_p2,e4_p2,e5_p2,e6_p2,e7_p2,e8_p2;
    //e1_p1 = Etiqueta 1 punto 1
    //e1_p1 = Etiqueta 1 punto 2

    public AddCompetencias() {
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {


                //se obtienen los datos para poder ser usados con variables;
                    if(tfLlave.getText() != null)
                        if(validarNumerico(tfLlave.getText()))
                            llave = Integer.parseInt(tfLlave.getText());
                        else
                            JOptionPane.showMessageDialog(null, "El valor no es un numero");
                    else
                        JOptionPane.showMessageDialog(null, "Llave vacia");

                    competencia = tfCompetencia.getText();
                    //---------------------------------------
                    etiqueta1= tfEtiqueta1.getText();
                    etiqueta2= tfEtiqueta2.getText();
                    etiqueta3= tfEtiqueta3.getText();
                    etiqueta4= tfEtiqueta4.getText();
                    etiqueta5= tfEtiqueta5.getText();
                    etiqueta6= tfEtiqueta6.getText();
                    etiqueta7= tfEtiqueta7.getText();
                    etiqueta8= tfEtiqueta8.getText();
                    //---------------------------------------
                    e1_p1 = tfEtiqueta1_p1.getText();
                    e1_p2 = tfEtiqueta1_p2.getText();
                    e2_p1 = tfEtiqueta2_p1.getText();
                    e2_p2 = tfEtiqueta2_p2.getText();
                    e3_p1 = tfEtiqueta3_p1.getText();
                    e3_p2 = tfEtiqueta3_p2.getText();
                    e4_p1 = tfEtiqueta4_p1.getText();
                    e4_p2 = tfEtiqueta4_p2.getText();
                    e5_p1 = tfEtiqueta5_p1.getText();
                    e5_p2 = tfEtiqueta5_p2.getText();
                    e6_p1 = tfEtiqueta6_p1.getText();
                    e6_p2 = tfEtiqueta6_p2.getText();
                    e7_p1 = tfEtiqueta7_p1.getText();
                    e7_p2 = tfEtiqueta7_p2.getText();
                    e8_p1 = tfEtiqueta8_p1.getText();
                    e8_p2 = tfEtiqueta8_p2.getText();
                    escribir_maestro();
                } catch (Exception ee) {
                    System.out.println("Exiten datos vacios, no pasa nada");
                }
                //Aqui manda a guardar

            }
        });
    }

    public void AddCompetencias()
    {
        this.setSize(600,270);
        this.setLayout(new GridLayout(1,2));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(panel_addC);
        this.repaint();
        this.setVisible(true);
    }

    private void escribir_maestro() throws IOException {
        maestro = new RandomAccessFile("maestro.dat", "rw");
        tamaño = maestro.length();
        maestro.seek(tamaño);//nos vamos hasta el final del archivo
        res = indice.buscarIndice(llave);
        competencia = "";

        if (res.getExistente() == 1) {
            System.out.println("Regla ya existente.");
        } else {
            competencia = tfCompetencia.getText();
            indice.escribir_indice(llave, competencia);//escribimos la entrada en el indice
            maestro.writeInt(llave);//escribimos la regla en el maestro

            escribir_etiqueta(etiqueta1);
            if(e1_p1.length() > 0 && e1_p2.length() > 0) {
                maestro.writeInt(Integer.parseInt(e1_p1));
                maestro.writeInt(Integer.parseInt(e1_p2));
            } else
                JOptionPane.showMessageDialog(null, "los valores de la etiqueta 1 no pueden ir vacios");

            escribir_etiqueta((etiqueta2.length() > 0) ? etiqueta2 : " ");
            maestro.writeInt((e2_p1.length() > 0) ? Integer.parseInt(e2_p1) : -1);
            maestro.writeInt((e2_p2.length() > 0) ? Integer.parseInt(e2_p2) : -1);


            System.out.println(etiqueta3.length() > 0);

            escribir_etiqueta((etiqueta3.length() > 0) ? etiqueta3 : " ");
            maestro.writeInt((e3_p1.length() > 0) ? Integer.parseInt(e3_p1) : -1);
            maestro.writeInt((e3_p2.length() > 0) ? Integer.parseInt(e3_p2) : -1);

            escribir_etiqueta((etiqueta4.length() > 0) ? etiqueta4 : " ");
            maestro.writeInt((e4_p1.length() > 0) ? Integer.parseInt(e4_p1) : -1);
            maestro.writeInt((e4_p2.length() > 0) ? Integer.parseInt(e4_p2) : -1);

            escribir_etiqueta((etiqueta5.length() > 0) ? etiqueta5 : " ");
            maestro.writeInt((e5_p1.length() > 0) ? Integer.parseInt(e5_p1) : -1);
            maestro.writeInt((e5_p2.length() > 0) ? Integer.parseInt(e5_p2) : -1);

            escribir_etiqueta((etiqueta6.length() > 0) ? etiqueta6 : " ");
            maestro.writeInt((e6_p1.length() > 0) ? Integer.parseInt(e6_p1) : -1);
            maestro.writeInt((e6_p2.length() > 0) ? Integer.parseInt(e6_p2) : -1);

            escribir_etiqueta((etiqueta7.length() > 0) ? etiqueta7 : " ");
            maestro.writeInt((e7_p1.length() > 0) ? Integer.parseInt(e7_p1) : -1);
            maestro.writeInt((e7_p2.length() > 0) ? Integer.parseInt(e7_p2) : -1);

            escribir_etiqueta((etiqueta8.length() > 0) ? etiqueta8 : " ");
            maestro.writeInt((e8_p1.length() > 0) ? Integer.parseInt(e8_p1) : -1);
            maestro.writeInt((e8_p2.length() > 0) ? Integer.parseInt(e8_p2) : -1);

            maestro.close();
        }
    }

    private void escribir_etiqueta(String etiq) throws IOException {
        for (int j = 0; j < 15; j++) {
            if (etiq != null)
                if (j < etiq.length())
                    maestro.writeChar(etiq.charAt(j));
                else
                    maestro.writeChar(' ');
        }
    }

    static private boolean validarNumerico(String x){
        if(x.matches("^[1-9]\\d*$"))
            return true;
        else{
            if(!x.equals(""))
                System.out.println("El valor ingresado no es númerico.");
            return false;
        }
    }
}
