package Proceso;



import javax.swing.*;
import javax.swing.text.html.parser.Parser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class interfaz extends JFrame{
    private JButton editarVariablesButton;
    private JPanel panel_principal;
    private JButton evaluarButton;
    private JButton agregarNuevasVariablesButton;

    private JMenuBar menuBar;
    private JMenu Archivos,maestro;
    private JMenuItem  indice, traslapes, secuencial;

    int entrada_real = 0;

    public interfaz() {
        evaluarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Evaluar evaluar = new Evaluar();
                evaluar.Evaluar(entrada_real);
            }
        });

        agregarNuevasVariablesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCompetencias c = new AddCompetencias();
                c.AddCompetencias();
                /*
                int llave =  Integer.parseInt( JOptionPane.showInputDialog(null,"Llave de la regla a escribir:"));
                Maestro m = new Maestro();
                try {
                    m.escribirVariable(llave);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }*/
            }
        });


    }

    public void interfaz()
    {
        this.setSize(700,470);
        this.setLayout(new GridLayout(1,2));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel_principal);
        menuBar = new JMenuBar();
        Archivos = new JMenu("Archivos");
        maestro = new JMenu("Archivo Maestro(puntos críticos)");
        secuencial = new JMenuItem("Ver archivo secuencial");
        indice = new JMenuItem("Archivo Indice");
        traslapes = new JMenuItem("Archivo Traslapes");

        menuBar.add(Archivos);

        Archivos.add(maestro);
        maestro.add(new AbstractAction("Ver archivo secuencial")
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("action");

            }
        });
        /*Archivos.add(new AbstractAction("Archivo Maestro(puntos críticos)")
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("action");

            }
        });*/
        Archivos.add(new AbstractAction("Archivo Indice")
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("action");

            }
        });
        Archivos.add(new AbstractAction("Archivo Traslapes")
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("action");

            }
        });


        this.setJMenuBar(menuBar);
        this.setVisible(true);





    }



}
