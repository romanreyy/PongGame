package Matrices;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class SecondScreen extends JFrame {
	private JFrame mainFrame;
	private JTextField[][] textFieldMatriz1;
    private JTextField[][] textFieldMatriz2;
	
	public SecondScreen() {
		this.mainFrame = mainFrame;
		initialize();		
	}

	private void initialize() {
        setBounds(100, 100, 643, 346);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("Volver");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
	            mainFrame.setVisible(true);
			}
		});
		btnBack.setBounds(6, 283, 117, 29);
		getContentPane().add(btnBack);
		
		JComboBox<String> matriz1 = new JComboBox<String>();
		matriz1.setBounds(71, 49, 79, 27);
		getContentPane().add(matriz1);
		matriz1.addItem("1x1");
		matriz1.addItem("1x2");
		matriz1.addItem("1x3");
		matriz1.addItem("2x1");
		matriz1.addItem("2x2");
		matriz1.addItem("2x3");
		matriz1.addItem("3x1");
		matriz1.addItem("3x2");
		matriz1.addItem("3x3");
		
		
		JComboBox<String> matriz2 = new JComboBox<String>();
		matriz2.setBounds(251, 49, 79, 27);
		getContentPane().add(matriz2);
		matriz2.addItem("1x1");
		matriz2.addItem("1x2");
		matriz2.addItem("1x3");
		matriz2.addItem("2x1");
		matriz2.addItem("2x2");
		matriz2.addItem("2x3");
		matriz2.addItem("3x1");
		matriz2.addItem("3x2");
		matriz2.addItem("3x3");
		
		JLabel lblMatriz1 = new JLabel("Matriz 1");
		lblMatriz1.setBounds(71, 21, 61, 16);
		getContentPane().add(lblMatriz1);
		
		JLabel lblMatriz2 = new JLabel("Matriz 2");
		lblMatriz2.setBounds(251, 21, 61, 16);
		getContentPane().add(lblMatriz2);
		
		JButton btnIgual = new JButton("=");
		btnIgual.setBounds(379, 141, 61, 29);
		getContentPane().add(btnIgual);
		
		JLabel lblMas = new JLabel("+");
		lblMas.setBounds(204, 146, 26, 16);
		getContentPane().add(lblMas);
		
		JLabel lblResultado = new JLabel("Suma");
		lblResultado.setBounds(506, 21, 61, 16);
		getContentPane().add(lblResultado);
		
		
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

	                int x = 250;

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
    }
}

