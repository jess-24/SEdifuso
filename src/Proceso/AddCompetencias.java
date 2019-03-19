package Proceso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public int llave;
    public String competencia,etiqueta1,etiqueta2,etiqueta3,etiqueta4,etiqueta5,etiqueta6,etiqueta7,etiqueta8;
    public int e1_p1,e2_p1,e3_p1,e4_p1,e5_p1,e6_p1,e7_p1,e8_p1,e1_p2,e2_p2,e3_p2,e4_p2,e5_p2,e6_p2,e7_p2,e8_p2;
    //e1_p1 = Etiqueta 1 punto 1
    //e1_p1 = Etiqueta 1 punto 2




    public AddCompetencias() {
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {


                //se obtienen los datos para poder ser usados con variables;
                llave= Integer.parseInt(tfLlave.getText());
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
                e1_p1 = Integer.parseInt(tfEtiqueta1_p1.getText());
                e1_p2 = Integer.parseInt(tfEtiqueta1_p2.getText());
                e2_p1 =Integer.parseInt(tfEtiqueta2_p1.getText());
                e2_p2 = Integer.parseInt(tfEtiqueta2_p2.getText());
                e3_p1 = Integer.parseInt(tfEtiqueta3_p1.getText());
                e3_p2 = Integer.parseInt(tfEtiqueta3_p2.getText());
                e4_p1 = Integer.parseInt(tfEtiqueta4_p1.getText());
                e4_p2 = Integer.parseInt(tfEtiqueta4_p2.getText());
                e5_p1 = Integer.parseInt(tfEtiqueta5_p1.getText());
                e5_p2 = Integer.parseInt(tfEtiqueta5_p2.getText());
                e6_p1 = Integer.parseInt(tfEtiqueta6_p1.getText());
                e6_p2 = Integer.parseInt(tfEtiqueta6_p2.getText());
                e7_p1 = Integer.parseInt(tfEtiqueta7_p1.getText());
                e7_p2 = Integer.parseInt(tfEtiqueta7_p2.getText());
                e8_p1 = Integer.parseInt(tfEtiqueta8_p1.getText());
                e8_p2 = Integer.parseInt(tfEtiqueta8_p2.getText());
                }catch (Exception ee)
                {System.out.println("Exiten datos vacios, no pasa nada");};
                //Aqui manda a guardar

                JOptionPane.showMessageDialog(null, "No hace nada el boton jajaja");
            }
        });
    }

    public void AddCompetencias()
    {
        this.setSize(600,270);
        this.setLayout(new GridLayout(1,2));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel_addC);
        this.repaint();
        this.setVisible(true);
    }
}
