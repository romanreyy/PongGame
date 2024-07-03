package operacionesMatriz;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

/**
 * @author Roman Rey
 */
public class MatrixCalculator extends JFrame {

	private static final long serialVersionUID = -7580717973032844223L;

	private JTextField[][] textFieldMatriz1;
	private JTextField[][] textFieldMatriz2;
	private JTextField[][] textFieldMatrizResultado;

	public MatrixCalculator(int x, int y, int width, int height) {
		this.setBounds(x, y, width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JButton btnBack = new JButton("Volver");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
//				MatrixCalculator.this.setVisible(true);
			}
		});
		btnBack.setBounds(22, 260, 117, 29);
		this.getContentPane().add(btnBack);

		JComboBox<String> matriz1 = new JComboBox<String>();
		matriz1.setBounds(71, 49, 79, 27);
		matriz1.addItem("1x1");
		matriz1.addItem("1x2");
		matriz1.addItem("1x3");
		matriz1.addItem("2x1");
		matriz1.addItem("2x2");
		matriz1.addItem("2x3");
		matriz1.addItem("3x1");
		matriz1.addItem("3x2");
		matriz1.addItem("3x3");
		getContentPane().add(matriz1);

		JComboBox<String> matriz2 = new JComboBox<String>();
		matriz2.setBounds(316, 49, 79, 27);
		matriz2.addItem("1x1");
		matriz2.addItem("1x2");
		matriz2.addItem("1x3");
		matriz2.addItem("2x1");
		matriz2.addItem("2x2");
		matriz2.addItem("2x3");
		matriz2.addItem("3x1");
		matriz2.addItem("3x2");
		matriz2.addItem("3x3");
		this.getContentPane().add(matriz2);

		JLabel lblMatriz1 = new JLabel("Matriz 1");
		lblMatriz1.setBounds(71, 21, 79, 16);
		getContentPane().add(lblMatriz1);

		JLabel lblMatriz2 = new JLabel("Matriz 2");
		lblMatriz2.setBounds(304, 21, 79, 16);
		getContentPane().add(lblMatriz2);

		JButton btnIgual = new JButton("=");
		btnIgual.setBounds(420, 141, 61, 29);
		getContentPane().add(btnIgual);

		JComboBox<String> optionsOperations = new JComboBox<String>();
		optionsOperations.setBounds(167, 147, 115, 16);
		getContentPane().add(optionsOperations);
		optionsOperations.addItem("Suma (+)");
		optionsOperations.addItem("Resta (-)");
		optionsOperations.addItem("Multiplicacion (x)");
		optionsOperations.addItem("Division (/)");
		
		JButton btnClean = new JButton("limpiar");
		btnClean.setBounds(500, 260, 80, 29);
		getContentPane().add(btnClean);

		matriz1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccion = (String) matriz1.getSelectedItem();
		        int filas = Character.getNumericValue(seleccion.charAt(0));
		        int columnas = Character.getNumericValue(seleccion.charAt(2));

		        if (textFieldMatriz1 != null) {
		            for (int i = 0; i < textFieldMatriz1.length; i++) {
		                for (int j = 0; j < textFieldMatriz1[i].length; j++) {
		                    getContentPane().remove(textFieldMatriz1[i][j]);
		                }
		            }
		        }

		        textFieldMatriz1 = new JTextField[filas][columnas];
		        int x = 70;
		        int y = 100;
		        int width = 30;
		        int height = 30;
		        for (int i = 0; i < filas; i++) {
		            for (int j = 0; j < columnas; j++) {
		                textFieldMatriz1[i][j] = new JTextField();
		                textFieldMatriz1[i][j].setBounds(x + j * (width + 5), y + i * (height + 5), width, height);
		                getContentPane().add(textFieldMatriz1[i][j]);
		            }
		        }

		        	revalidate();
		        	repaint();
			}
		});

		matriz2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccion = (String) matriz2.getSelectedItem();
		        int filas = Character.getNumericValue(seleccion.charAt(0));
		        int columnas = Character.getNumericValue(seleccion.charAt(2));

		        if (textFieldMatriz2 != null) {
		        	for (int i = 0; i < textFieldMatriz2.length; i++) {
		                for (int j = 0; j < textFieldMatriz2[i].length; j++) {
		                	getContentPane().remove(textFieldMatriz2[i][j]);
		                }
		            }
		        }

		        textFieldMatriz2 = new JTextField[filas][columnas];

		        int x = 300;
		        int y = 100;
		        int width = 30;
		        int height = 30;
		        
		        for (int i = 0; i < filas; i++) {
		        	for (int j = 0; j < columnas; j++) {
		                textFieldMatriz2[i][j] = new JTextField();
		                textFieldMatriz2[i][j].setBounds(x + j * (width + 5), y + i * (height + 5), width, height);
		                getContentPane().add(textFieldMatriz2[i][j]);
		            }
		        }
		        	revalidate();
		        	repaint();
			}
		});

		btnIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateCleanMatrizButton calculateMatrizButton = new CalculateCleanMatrizButton();
				calculateMatrizButton.calculateButton(textFieldMatriz1, textFieldMatriz2, textFieldMatrizResultado, MatrixCalculator.this, optionsOperations);
			}
		});
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateCleanMatrizButton cleanScreen = new CalculateCleanMatrizButton();
				cleanScreen.cleanMatriz(textFieldMatriz1, textFieldMatriz2, textFieldMatrizResultado, MatrixCalculator.this);
				textFieldMatriz1 = null;
				textFieldMatriz2 = null;
				textFieldMatrizResultado = null;
			}
		});
	}
}
