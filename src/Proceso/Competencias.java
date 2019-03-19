package Proceso;

import javax.swing.*;
import java.awt.*;

public class Competencias extends JFrame {
    private JPanel panel_competencias;
    private JComboBox comboBox1;
    private JButton agregarNuevaCompetenciaButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton guardarPuntosCriticosButton;


    public void Competencias()
    {
        this.setSize(500,270);
        this.setLayout(new GridLayout(1,2));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel_competencias);
        this.repaint();
        this.setVisible(true);
    }
}
