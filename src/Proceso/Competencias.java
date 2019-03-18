package Proceso;

import javax.swing.*;
import java.awt.*;

public class Competencias extends JFrame {
    private JPanel panel_competencias;
    private JComboBox comboBox1;
    private JButton agregarNuevaCompetenciaButton;


    public void Competencias()
    {
        this.setSize(700,470);
        this.setLayout(new GridLayout(1,2));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel_competencias);
        this.repaint();
        this.setVisible(true);
    }
}
