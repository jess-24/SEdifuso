package Proceso;



import javax.swing.*;
import javax.swing.text.html.parser.Parser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.util.ArrayList;

public class interfaz extends JFrame{
    private JButton editarVariablesButton;
    private JPanel panel_principal;
    private JButton evaluarButton;
    private JButton agregarNuevasVariablesButton;
    private JPanel panel_izquierdo;
    private JPanel panel_derecho;
    private JPanel panel_1;

    private JMenuBar menuBar;
    private JMenu Archivos,maestro;
    private JMenuItem  indice, traslapes, secuencial;
    Maestro m = new Maestro();
    Indice ind = new Indice();
    FAM fam = new FAM();
    int entrada_real = 0, cantidad;
    ArrayList<Indice> comp= new ArrayList<Indice>();
    //ArrayList<Variable> variables_salida= new ArrayList<Variable>();
    ArrayList<variableSalida> variable_salida = new ArrayList<variableSalida>();
    JLabel[] label;    //Declaración del array de etiquetas
    JTextField[] text;   //Declaración del array de cajas de texto
    String etiq_grad = "";


    ArrayList<variableSalida> competencias_gMem = new ArrayList<variableSalida>();

    public interfaz() throws IOException {
        cantidad= m.cantidad_Competencias();
        label = new JLabel[cantidad];
        text = new JTextField[cantidad];
        Indice in = new Indice();
        try {
            comp=in.obtenerTodasCompetencias();
        }catch (Exception ed){};
        evaluarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] entradas = new int[comp.size()][2];

                for (int i=0;i<comp.size();i++)
                {
                    Difusificacion difu= new Difusificacion();
                    variable_salida.add(new variableSalida(comp.get(i).getExistente() , difu.setEntradaReal(Integer.parseInt(text[i].getText()),comp.get(i).getExistente())) );
                }
                 for (int j=0;j<comp.size();j++)
                 {
                     ArrayList<Variable> vars = new ArrayList<Variable>();
                     for (int i = 0; i < variable_salida.get(j).getVariables().size(); i++) {
                         if(variable_salida.get(j).getVariables().get(i).getEtiqueta().charAt(0) != ' ') {
                             //etiq_grad = etiq_grad + "Llave comp: " + comp.get(j).getExistente() + "  ";
                             etiq_grad = etiq_grad + variable_salida.get(j).getVariables().get(i).getEtiqueta() + "   ";
                             etiq_grad = etiq_grad + "Entrada real: " + variable_salida.get(j).getVariables().get(i).getX() + "   ";
                             etiq_grad = etiq_grad + "Membresia: " + variable_salida.get(j).getVariables().get(i).getY() + "\n";
                             vars.add(new Variable(variable_salida.get(j).getVariables().get(i).getEtiqueta(), variable_salida.get(j).getVariables().get(i).getX(), variable_salida.get(j).getVariables().get(i).getY()));
                         }

                     }

                     JOptionPane.showMessageDialog(null, comp.get(j).getCompetencia()+"\n" + etiq_grad);
                     //System.out.println(comp.get(j).getCompetencia()+"\n" + etiq_grad);
                     etiq_grad = "";
                     competencias_gMem.add(new variableSalida(comp.get(j).getExistente(), vars));
                 }

                try {
                    fam.sacar_maximos(fam.generarMatriz(competencias_gMem));
                    //JOptionPane.showMessageDialog(null, "Resultados:\nBueno: " + fam.getMaxBueno() + "\nSuficiente: " + fam.getMaxSuficiente() + "\nInsuficiente: " + fam.getMaxInsuficiente(), "Resultados Finales", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Calificacion: " + fam.centroide(fam.getMaxInsuficiente(), fam.getMaxSuficiente(), fam.getMaxBueno(), fam.generarConsecuente()), "Calificacion final", JOptionPane.INFORMATION_MESSAGE); // calculo del centroide
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


                /*
                Evaluar evaluar = new Evaluar();
                evaluar.Evaluar(entrada_real);
            */}
        });
        agregarNuevasVariablesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCompetencias ad= new AddCompetencias();
                ad.AddCompetencias();
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

        editarVariablesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateCompetencias up = new UpdateCompetencias();
                try {
                    up.UpdateCompetencias(Integer.parseInt(JOptionPane.showInputDialog("Llave de la competencia a modificar:")));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public void interfaz()
    {
        this.setSize(700,700);
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


        Archivos.add(new AbstractAction("Archivo Maestro(puntos críticos)")
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    m.leerSecuencial();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        Archivos.add(new AbstractAction("Archivo Indice")
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    ind.leerSecuencial();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        panel_izquierdo.setLayout(null);
        panel_derecho.setLayout(null);

        for(int i = 0; i < comp.size(); i++) {
            label[i] = new JLabel();    //Llenamos el array de etiquetas
            text[i] = new JTextField();    //Llemanos el array de cajas de texto
            //(6) PROPIEDADES DE LOS CONTROLES
            if (i==0)
            {
                label[i].setBounds(new Rectangle(15, 1, 500, 25));
                label[i].setText("COMPETENCIAS");
                panel_izquierdo.add(label[i], null);
                label[i] = new JLabel();    //Llenamos el array de etiquetas
                label[i].setBounds(new Rectangle(15, (i+1)*40, 500, 25));
                label[i].setText("CALIFICACION");
                panel_derecho.add(text[i], null);
                label[i] = new JLabel();    //Llenamos el array de etiquetas
                text[i] = new JTextField();
            }

            label[i].setBounds(new Rectangle(15, (i+1)*40, 500, 25));

            label[i].setText(comp.get(i).getExistente() + comp.get(i).getCompetencia());

            label[i].setHorizontalAlignment(SwingConstants.LEFT);
            text[i].setBounds(new Rectangle(100, (i+1)*40, 60, 25));
            //(7) ADICION DE LOS CONTROLES AL CONTENEDOR
            panel_izquierdo.add(label[i], null);
            panel_derecho.add(text[i], null);



        }



        this.setJMenuBar(menuBar);
        this.setVisible(true);





    }

}
