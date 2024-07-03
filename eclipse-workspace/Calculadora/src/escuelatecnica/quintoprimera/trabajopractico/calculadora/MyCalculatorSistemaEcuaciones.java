package escuelatecnica.quintoprimera.trabajopractico.calculadora;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import calculoSistemaEcuaciones.SistemasEcuaciones;

public class MyCalculatorSistemaEcuaciones extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField[][] txtCoef2x2;
    private JTextField[][] txtCoef3x3;
    private JLabel lblResuX;
    private JLabel lblResuY;
    private JLabel lblResuZ;

    public MyCalculatorSistemaEcuaciones() {
        setBounds(100, 100, 500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(224, 255, 255));

        JButton btnBack = new JButton("Volver");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnBack.setBounds(22, 230, 117, 29);
        getContentPane().add(btnBack);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Operaciones");
        menuBar.add(menu);

        JMenuItem menuItem2x2 = new JMenuItem("Sistema de Ecuaciones 2x2");
        menu.add(menuItem2x2);
        JMenuItem menuItem3x3 = new JMenuItem("Sistema de Ecuaciones 3x3");
        menu.add(menuItem3x3);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(10, 10, 565, 500);
        getContentPane().add(mainPanel);
        mainPanel.setLayout(null);

        menuItem2x2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();

                txtCoef2x2 = new JTextField[2][3];
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        txtCoef2x2[i][j] = new JTextField();
                        txtCoef2x2[i][j].setBounds(50 + j * 60, 50 + i * 30, 50, 20);
                        mainPanel.add(txtCoef2x2[i][j]);
                    }
                }

                lblResuX = new JLabel("x=");
                lblResuX.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblResuX.setHorizontalAlignment(SwingConstants.CENTER);
                lblResuX.setBounds(100, 120, 300, 30);
                mainPanel.add(lblResuX);

                lblResuY = new JLabel("y=");
                lblResuY.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblResuY.setHorizontalAlignment(SwingConstants.CENTER);
                lblResuY.setBounds(100, 160, 300, 30);
                mainPanel.add(lblResuY);

                JButton btnSolve2x2 = new JButton("Resolver 2x2");
                btnSolve2x2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            double[][] coef = new double[2][3];
                            for (int i = 0; i < 2; i++) {
                                for (int j = 0; j < 3; j++) {
                                    coef[i][j] = Double.parseDouble(txtCoef2x2[i][j].getText());
                                }
                            }
                            String[] resultados = SistemasEcuaciones.resolverSistema2x2(coef).split(",");
                            lblResuX.setText("" + resultados[0]);
                            lblResuY.setText("" + resultados[1]);
                        } catch (NumberFormatException ex) {
                            lblResuX.setText("Error: Entrada no válida");
                            lblResuY.setText("");
                        }
                    }
                });
                btnSolve2x2.setBounds(200, 200, 150, 30);
                mainPanel.add(btnSolve2x2);

                revalidate();
                repaint();
            }
        });

        menuItem3x3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();

                txtCoef3x3 = new JTextField[3][4];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 4; j++) {
                        txtCoef3x3[i][j] = new JTextField();
                        txtCoef3x3[i][j].setBounds(50 + j * 60, 50 + i * 30, 50, 20);
                        mainPanel.add(txtCoef3x3[i][j]);
                    }
                }

                lblResuX = new JLabel("x=");
                lblResuX.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblResuX.setHorizontalAlignment(SwingConstants.CENTER);
                lblResuX.setBounds(100, 120, 300, 30);
                mainPanel.add(lblResuX);

                lblResuY = new JLabel("y=");
                lblResuY.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblResuY.setHorizontalAlignment(SwingConstants.CENTER);
                lblResuY.setBounds(100, 160, 300, 30);
                mainPanel.add(lblResuY);

                lblResuZ = new JLabel("z=");
                lblResuZ.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblResuZ.setHorizontalAlignment(SwingConstants.CENTER);
                lblResuZ.setBounds(100, 200, 300, 30);
                mainPanel.add(lblResuZ);

                JButton btnSolve3x3 = new JButton("Resolver 3x3");
                btnSolve3x3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            double[][] coef = new double[3][4];
                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < 4; j++) {
                                    coef[i][j] = Double.parseDouble(txtCoef3x3[i][j].getText());
                                }
                            }
                            String[] resultados = SistemasEcuaciones.resolverSistema3x3(coef).split(",");
                            lblResuX.setText("" + resultados[0]);
                            lblResuY.setText("" + resultados[1]);
                            lblResuZ.setText("" + resultados[2]);
                        } catch (NumberFormatException ex) {
                            lblResuX.setText("Error: Entrada no válida");
                            lblResuY.setText("");
                            lblResuZ.setText("");
                        }
                    }
                });
                btnSolve3x3.setBounds(200, 240, 150, 30);
                mainPanel.add(btnSolve3x3);

                revalidate();
                repaint();
            }
        });
    }
}
