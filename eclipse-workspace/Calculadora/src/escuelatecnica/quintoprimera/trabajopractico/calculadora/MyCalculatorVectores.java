package escuelatecnica.quintoprimera.trabajopractico.calculadora;


import javax.swing.JFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class MyCalculatorVectores extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldVector1;
    private JTextField textFieldVector2;
    private JTextField textFieldEscalar;
    private JLabel lblResultado;
	
	public MyCalculatorVectores(int i, int j, int width, int height) {
		setBounds(i, j, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(224, 255, 255));
		
		JButton btnBack = new JButton("Volver");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(22, 283, 117, 29);
		getContentPane().add(btnBack);
		
        JLabel lblVector1 = new JLabel("Vector 1:");
        lblVector1.setBounds(10, 42, 86, 14);
        getContentPane().add(lblVector1);

        textFieldVector1 = new JTextField();
        textFieldVector1.setBounds(106, 39, 307, 20);
        getContentPane().add(textFieldVector1);
        textFieldVector1.setColumns(10);

        JLabel lblVector2 = new JLabel("Vector 2:");
        lblVector2.setBounds(10, 75, 86, 14);
        getContentPane().add(lblVector2);

        textFieldVector2 = new JTextField();
        textFieldVector2.setBounds(106, 72, 307, 20);
        getContentPane().add(textFieldVector2);
        textFieldVector2.setColumns(10);

        JLabel lblEscalar = new JLabel("Escalar:");
        lblEscalar.setBounds(10, 106, 86, 14);
        getContentPane().add(lblEscalar);

        textFieldEscalar = new JTextField();
        textFieldEscalar.setBounds(106, 103, 86, 20);
        getContentPane().add(textFieldEscalar);
        textFieldEscalar.setColumns(10);

        lblResultado = new JLabel("Resultado:");
        lblResultado.setBounds(10, 186, 500, 14);
        getContentPane().add(lblResultado);

        JButton btnSuma = new JButton("Suma");
        btnSuma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double[] v1 = parseVector(textFieldVector1.getText());
                    double[] v2 = parseVector(textFieldVector2.getText());
                    double[] result = suma(v1, v2);
                    lblResultado.setText("Resultado: " + vectorToString(result));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        btnSuma.setBounds(7, 152, 89, 23);
        getContentPane().add(btnSuma);

        JButton btnResta = new JButton("Resta");
        btnResta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double[] v1 = parseVector(textFieldVector1.getText());
                    double[] v2 = parseVector(textFieldVector2.getText());
                    double[] result = resta(v1, v2);
                    lblResultado.setText("Resultado: " + vectorToString(result));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        btnResta.setBounds(106, 152, 89, 23);
        getContentPane().add(btnResta);

        JButton btnMultiplicacionEscalar = new JButton("Multiplicar Escalar");
        btnMultiplicacionEscalar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double[] v1 = parseVector(textFieldVector1.getText());
                    double escalar = Double.parseDouble(textFieldEscalar.getText());
                    double[] result = multiplicacionEscalar(v1, escalar);
                    lblResultado.setText("Resultado: " + vectorToString(result));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        btnMultiplicacionEscalar.setBounds(205, 152, 150, 23);
        getContentPane().add(btnMultiplicacionEscalar);

        JButton btnProductoEscalar = new JButton("Producto Escalar");
        btnProductoEscalar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double[] v1 = parseVector(textFieldVector1.getText());
                    double[] v2 = parseVector(textFieldVector2.getText());
                    double result = productoEscalar(v1, v2);
                    lblResultado.setText("Resultado: " + result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        btnProductoEscalar.setBounds(365, 152, 142, 23);
        getContentPane().add(btnProductoEscalar);

        JButton btnProductoVectorial = new JButton("Producto Vectorial");
        btnProductoVectorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double[] v1 = parseVector(textFieldVector1.getText());
                    double[] v2 = parseVector(textFieldVector2.getText());
                    double[] result = productoVectorial(v1, v2);
                    lblResultado.setText("Resultado: " + vectorToString(result));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        btnProductoVectorial.setBounds(502, 152, 150, 23);
        getContentPane().add(btnProductoVectorial);
        
        JLabel lblAviso = new JLabel("Los vectores deben ser del mismo tama\u00F1o");
        lblAviso.setBounds(10, 11, 351, 23);
        getContentPane().add(lblAviso);
    }

    private double[] parseVector(String text) {
        String[] parts = text.split(",");
        double[] vector = new double[parts.length];
        for (int i = 0; i < parts.length; i++) {
            vector[i] = Double.parseDouble(parts[i]);
        }
        return vector;
    }

    private String vectorToString(double[] vector) {
        StringBuilder sb = new StringBuilder();
        for (double v : vector) {
            sb.append(v).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    public static double[] suma(double[] v1, double[] v2) {
        if (v1.length != v2.length) throw new IllegalArgumentException("Los vectores deben tener la misma longitud.");
        double[] result = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] + v2[i];
        }
        return result;
    }

    public static double[] resta(double[] v1, double[] v2) {
        if (v1.length != v2.length) throw new IllegalArgumentException("Los vectores deben tener la misma longitud.");
        double[] result = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] - v2[i];
        }
        return result;
    }

    public static double[] multiplicacionEscalar(double[] v, double escalar) {
        double[] result = new double[v.length];
        for (int i = 0; i < v.length; i++) {
            result[i] = v[i] * escalar;
        }
        return result;
    }

    public static double productoEscalar(double[] v1, double[] v2) {
        if (v1.length != v2.length) throw new IllegalArgumentException("Los vectores deben tener la misma longitud.");
        double result = 0;
        for (int i = 0; i < v1.length; i++) {
            result += v1[i] * v2[i];
        }
        return result;
    }

    public static double[] productoVectorial(double[] v1, double[] v2) {
        if (v1.length != 3 || v2.length != 3) throw new IllegalArgumentException("Los vectores deben tener 3 elementos.");
        double[] result = new double[3];
        result[0] = v1[1] * v2[2] - v1[2] * v2[1];
        result[1] = v1[2] * v2[0] - v1[0] * v2[2];
        result[2] = v1[0] * v2[1] - v1[1] * v2[0];
        return result;
	}

}
