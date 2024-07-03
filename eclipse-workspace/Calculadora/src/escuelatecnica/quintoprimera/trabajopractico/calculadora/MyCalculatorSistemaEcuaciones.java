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
import calculoSistemaEcuaciones.OperacionesBasicas;
import calculoSistemaEcuaciones.SistemasEcuaciones;

public class MyCalculatorSistemaEcuaciones extends JFrame {

	    private static final long serialVersionUID = 1L;
		private JTextField txtTxtnum;
	    private JTextField txtTxtnum_1;
	    private JTextField[][] txtCoef2x2;
	    private JTextField[][] txtCoef3x3;
	    private JLabel lblResu;


	    public MyCalculatorSistemaEcuaciones(int i, int j, int width, int height) {
	        setBounds(i, j , width, height);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        getContentPane().setLayout(null);
	        getContentPane().setBackground(new Color(224, 255, 255));
	        
	        JButton btnBack = new JButton("Volver");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnBack.setBounds(22, 260, 117, 29);
			getContentPane().add(btnBack);
			
	        JMenuBar menuBar = new JMenuBar();
	        setJMenuBar(menuBar);

	        JMenu menu = new JMenu("Operaciones");
	        menuBar.add(menu);

	        JMenuItem menuItemSuma = new JMenuItem("Suma");
	        menu.add(menuItemSuma);
	        JMenuItem menuItemResta = new JMenuItem("Resta");
	        menu.add(menuItemResta);
	        JMenuItem menuItemMult = new JMenuItem("Multiplicación");
	        menu.add(menuItemMult);
	        JMenuItem menuItemDiv = new JMenuItem("División");
	        menu.add(menuItemDiv);
	        JMenuItem menuItem2x2 = new JMenuItem("Sistema de Ecuaciones 2x2");
	        menu.add(menuItem2x2);
	        JMenuItem menuItem3x3 = new JMenuItem("Sistema de Ecuaciones 3x3");
	        menu.add(menuItem3x3);

	        JPanel mainPanel = new JPanel();
	        mainPanel.setBounds(10, 10, 565, 500);
	        getContentPane().add(mainPanel);
	        mainPanel.setLayout(null);

	        menuItemSuma.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                mainPanel.removeAll();
	                mainPanel.revalidate();
	                mainPanel.repaint();

	                txtTxtnum = new JTextField();
	                txtTxtnum.setHorizontalAlignment(SwingConstants.RIGHT);
	                txtTxtnum.setText("0");
	                txtTxtnum.setBounds(165, 131, 96, 19);
	                mainPanel.add(txtTxtnum);
	                txtTxtnum.setColumns(10);

	                txtTxtnum_1 = new JTextField();
	                txtTxtnum_1.setHorizontalAlignment(SwingConstants.RIGHT);
	                txtTxtnum_1.setText("0");
	                txtTxtnum_1.setColumns(10);
	                txtTxtnum_1.setBounds(291, 131, 96, 19);
	                mainPanel.add(txtTxtnum_1);

	                lblResu = new JLabel("Resultado");
	                lblResu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	                lblResu.setHorizontalAlignment(SwingConstants.CENTER);
	                lblResu.setBounds(200, 200, 150, 30);
	                mainPanel.add(lblResu);

	                JButton btnSuma = new JButton("SUMA");
	                btnSuma.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        try {
	                            int num1 = Integer.parseInt(txtTxtnum.getText());
	                            int num2 = Integer.parseInt(txtTxtnum_1.getText());
	                            int suma = OperacionesBasicas.suma(num1, num2);
	                            lblResu.setText(String.valueOf(suma));
	                        } catch (NumberFormatException ex) {
	                            lblResu.setText("Error: Entrada no válida");
	                        }
	                    }
	                });
	                btnSuma.setBounds(200, 250, 150, 30);
	                mainPanel.add(btnSuma);

	                revalidate();
	                repaint();
	            }
	        });

	        menuItemResta.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                mainPanel.removeAll();
	                mainPanel.revalidate();
	                mainPanel.repaint();

	                txtTxtnum = new JTextField();
	                txtTxtnum.setHorizontalAlignment(SwingConstants.RIGHT);
	                txtTxtnum.setText("0");
	                txtTxtnum.setBounds(165, 131, 96, 19);
	                mainPanel.add(txtTxtnum);
	                txtTxtnum.setColumns(10);

	                txtTxtnum_1 = new JTextField();
	                txtTxtnum_1.setHorizontalAlignment(SwingConstants.RIGHT);
	                txtTxtnum_1.setText("0");
	                txtTxtnum_1.setColumns(10);
	                txtTxtnum_1.setBounds(291, 131, 96, 19);
	                mainPanel.add(txtTxtnum_1);

	                lblResu = new JLabel("Resultado");
	                lblResu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	                lblResu.setHorizontalAlignment(SwingConstants.CENTER);
	                lblResu.setBounds(200, 200, 150, 30);
	                mainPanel.add(lblResu);

	                JButton btnResta = new JButton("RESTA");
	                btnResta.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        try {
	                            int num1 = Integer.parseInt(txtTxtnum.getText());
	                            int num2 = Integer.parseInt(txtTxtnum_1.getText());
	                            int resta = OperacionesBasicas.resta(num1, num2);
	                            lblResu.setText(String.valueOf(resta));
	                        } catch (NumberFormatException ex) {
	                            lblResu.setText("Error: Entrada no válida");
	                        }
	                    }
	                });
	                btnResta.setBounds(200, 250, 150, 30);
	                mainPanel.add(btnResta);

	                revalidate();
	                repaint();
	            }
	        });

	        menuItemMult.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                mainPanel.removeAll();
	                mainPanel.revalidate();
	                mainPanel.repaint();

	                txtTxtnum = new JTextField();
	                txtTxtnum.setHorizontalAlignment(SwingConstants.RIGHT);
	                txtTxtnum.setText("0");
	                txtTxtnum.setBounds(165, 131, 96, 19);
	                mainPanel.add(txtTxtnum);
	                txtTxtnum.setColumns(10);

	                txtTxtnum_1 = new JTextField();
	                txtTxtnum_1.setHorizontalAlignment(SwingConstants.RIGHT);
	                txtTxtnum_1.setText("0");
	                txtTxtnum_1.setColumns(10);
	                txtTxtnum_1.setBounds(291, 131, 96, 19);
	                mainPanel.add(txtTxtnum_1);

	                lblResu = new JLabel("Resultado");
	                lblResu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	                lblResu.setHorizontalAlignment(SwingConstants.CENTER);
	                lblResu.setBounds(200, 200, 150, 30);
	                mainPanel.add(lblResu);

	                JButton btnMult = new JButton("MULT");
	                btnMult.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        try {
	                            int num1 = Integer.parseInt(txtTxtnum.getText());
	                            int num2 = Integer.parseInt(txtTxtnum_1.getText());
	                            int mult = OperacionesBasicas.multiplicacion(num1, num2);
	                            lblResu.setText(String.valueOf(mult));
	                        } catch (NumberFormatException ex) {
	                            lblResu.setText("Error: Entrada no válida");
	                        }
	                    }
	                });
	                btnMult.setBounds(200, 250, 150, 30);
	                mainPanel.add(btnMult);

	                revalidate();
	                repaint();
	            }
	        });

	        menuItemDiv.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                mainPanel.removeAll();
	                mainPanel.revalidate();
	                mainPanel.repaint();

	                txtTxtnum = new JTextField();
	                txtTxtnum.setHorizontalAlignment(SwingConstants.RIGHT);
	                txtTxtnum.setText("0");
	                txtTxtnum.setBounds(165, 131, 96, 19);
	                mainPanel.add(txtTxtnum);
	                txtTxtnum.setColumns(10);

	                txtTxtnum_1 = new JTextField();
	                txtTxtnum_1.setHorizontalAlignment(SwingConstants.RIGHT);
	                txtTxtnum_1.setText("0");
	                txtTxtnum_1.setColumns(10);
	                txtTxtnum_1.setBounds(291, 131, 96, 19);
	                mainPanel.add(txtTxtnum_1);

	                lblResu = new JLabel("Resultado");
	                lblResu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	                lblResu.setHorizontalAlignment(SwingConstants.CENTER);
	                lblResu.setBounds(200, 200, 150, 30);
	                mainPanel.add(lblResu);

	                JButton btnDivi = new JButton("DIV");
	                btnDivi.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        try {
	                            float num1 = Float.parseFloat(txtTxtnum.getText());
	                            float num2 = Float.parseFloat(txtTxtnum_1.getText());

	                            if (num2 == 0) {
	                                lblResu.setText("Error: División por cero");
	                            } else {
	                                float divi = OperacionesBasicas.division(num1, num2);
	                                lblResu.setText(String.valueOf(divi));
	                            }
	                        } catch (NumberFormatException ex) {
	                            lblResu.setText("Error: Entrada no válida");
	                        }
	                    }
	                });
	                btnDivi.setBounds(200, 250, 150, 30);
	                mainPanel.add(btnDivi);

	                revalidate();
	                repaint();
	            }
	        });

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

	                lblResu = new JLabel("Resultado");
	                lblResu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	                lblResu.setHorizontalAlignment(SwingConstants.CENTER);
	                lblResu.setBounds(200, 150, 150, 30);
	                mainPanel.add(lblResu);

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
	                            String resultado = SistemasEcuaciones.resolverSistema2x2(coef);
	                            lblResu.setText(resultado);
	                        } catch (NumberFormatException ex) {
	                            lblResu.setText("Error: Entrada no válida");
	                        }
	                    }
	                });
	                btnSolve2x2.setBounds(200, 250, 150, 30);
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

	                lblResu = new JLabel("Resultado");
	                lblResu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	                lblResu.setHorizontalAlignment(SwingConstants.CENTER);
	                lblResu.setBounds(200, 150, 150, 30);
	                mainPanel.add(lblResu);

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
	                            String resultado = SistemasEcuaciones.resolverSistema3x3(coef);
	                            lblResu.setText(resultado);
	                        } catch (NumberFormatException ex) {
	                            lblResu.setText("Error: Entrada no válida");
	                        }
	                    }
	                });
	                btnSolve3x3.setBounds(200, 250, 150, 30);
	                mainPanel.add(btnSolve3x3);

	                revalidate();
	                repaint();
	            }
	        });
	    }
	}
