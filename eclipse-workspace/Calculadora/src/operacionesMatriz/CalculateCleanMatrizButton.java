package operacionesMatriz;

import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JComboBox;

public class CalculateCleanMatrizButton {

	public void calculateButton(JTextField[][] textFieldMatriz1, JTextField[][] textFieldMatriz2, JTextField[][] textFieldMatrizResultado, JFrame frame, JComboBox<String> optionsOperations) {
		if (textFieldMatriz1 == null || textFieldMatriz2 == null) {
            return;
            }  
		int filas1 = textFieldMatriz1.length;
        int columnas1 = textFieldMatriz1[0].length;
        int filas2 = textFieldMatriz2.length;
        int columnas2 = textFieldMatriz2.length;
        
        if (textFieldMatrizResultado != null) {
        	for (int i=0; i<textFieldMatrizResultado.length; i++) {
            for (int j=0; i<textFieldMatrizResultado[i].length; i++) {
            	frame.getContentPane().remove(textFieldMatrizResultado[i][j]);
            	}
            }
        }
           
        textFieldMatrizResultado = new JTextField[filas1][columnas1];
        int x = 515;
        int y = 100;
        int width = 30;
        int height = 30;
        String selectedOption = (String) optionsOperations.getSelectedItem();
        
        if ("Suma (+)".equals(selectedOption)) {
        	if (filas1 != filas2 || columnas1 != columnas2) {
                System.out.print("Las matrices deben ser de igual tamaño");
                return;
            }
        	for (int i = 0; i < filas1; i++) {
        		for (int j = 0; j < columnas1; j++) {
        			textFieldMatrizResultado[i][j] = new JTextField();
        			textFieldMatrizResultado[i][j].setBounds(x + j * (width + 5), y + i * (height + 5), width, height);
        			textFieldMatrizResultado[i][j].setEditable(false);
        			textFieldMatrizResultado[i][j].getHorizontalAlignment();
        			textFieldMatrizResultado[i][j].setEditable(false);
        			frame.getContentPane().add(textFieldMatrizResultado[i][j]);
        			int valor1 = Integer.parseInt(textFieldMatriz1[i][j].getText());
        			int valor2 = Integer.parseInt(textFieldMatriz2[i][j].getText());
        			int suma = valor1 + valor2;
        			textFieldMatrizResultado[i][j].setText(String.valueOf(suma));
        		}
        	}
        }
        
        if ("Resta (-)".equals(selectedOption)) {
        	for (int i = 0; i < filas1; i++) {
        		for (int j = 0; j < columnas1; j++) {
        			textFieldMatrizResultado[i][j] = new JTextField();
        			textFieldMatrizResultado[i][j].setBounds(x + j * (width + 5), y + i * (height + 5), width, height);
        			textFieldMatrizResultado[i][j].setEditable(false);
        			textFieldMatrizResultado[i][j].getHorizontalAlignment();
        			textFieldMatrizResultado[i][j].setEditable(false);
        			frame.getContentPane().add(textFieldMatrizResultado[i][j]);
        			int valor1 = Integer.parseInt(textFieldMatriz1[i][j].getText());
        			int valor2 = Integer.parseInt(textFieldMatriz2[i][j].getText());
        			int resta = valor1 - valor2;
        			textFieldMatrizResultado[i][j].setText(String.valueOf(resta));
        		}
        	}
        }
        
        if ("Multiplicacion (x)".equals(selectedOption)) {
        	if(columnas1 != filas2) {
        		System.out.print("Para sumar matrices la cantidad de columnas en la matriz 1 debe ser igual a la cantidad de filas en la matriz 2");
                return;
        	}
        	for (int i = 0; i < filas1; i++) {
                for (int j = 0; j < columnas2; j++) {
                    textFieldMatrizResultado[i][j] = new JTextField();
                    textFieldMatrizResultado[i][j].setBounds(x + j * (width + 5), y + i * (height + 5), width, height);
                    textFieldMatrizResultado[i][j].setEditable(false);
                    textFieldMatrizResultado[i][j].setHorizontalAlignment(JTextField.CENTER);
                    frame.getContentPane().add(textFieldMatrizResultado[i][j]);
                    int valorResultado = 0;
                    for (int k = 0; k < columnas1; k++) {
                        int valor1 = Integer.parseInt(textFieldMatriz1[i][k].getText());
                        int valor2 = Integer.parseInt(textFieldMatriz2[k][j].getText());
                        valorResultado += valor1 * valor2;
                    }
                    textFieldMatrizResultado[i][j].setText(String.valueOf(valorResultado));
                }
            }
        }
        
        if ("Division (/)".equals(selectedOption)) {
        	for (int i = 0; i < filas1; i++) {
        		for (int j = 0; j < columnas1; j++) {
        			textFieldMatrizResultado[i][j] = new JTextField();
        			textFieldMatrizResultado[i][j].setBounds(x + j * (width + 5), y + i * (height + 5), width, height);
        			textFieldMatrizResultado[i][j].setEditable(false);
        			textFieldMatrizResultado[i][j].getHorizontalAlignment();
        			textFieldMatrizResultado[i][j].setEditable(false);
        			frame.getContentPane().add(textFieldMatrizResultado[i][j]);
        			//double valor1 = Double.parseDouble(textFieldMatriz1[i][j].getText());
        			//double valor2 = Double.parseDouble(textFieldMatriz2[i][j].getText());
        			//textFieldMatrizResultado[i][j].setText(String.valueOf());
        		}
        	}
        }
               
        frame.revalidate();
        frame.repaint();
	}
	
	public void cleanMatriz(JTextField[][] textFieldMatriz1, JTextField[][] textFieldMatriz2, JTextField[][] textFieldMatrizResultado, JFrame frame) {
		
		if (textFieldMatriz1 != null) {
			for (int i=0; i<textFieldMatriz1.length; i++) {
				for(int j=0; j<textFieldMatriz1[i].length; j++) {
                	frame.getContentPane().remove(textFieldMatriz1[i][j]);
				}
			}
			textFieldMatriz1 = null;
		}
           
		if (textFieldMatriz2 != null) {
			for (int i=0; i<textFieldMatriz2.length; i++) {
				for (int j=0; j<textFieldMatriz2[i].length; j++) {
					frame.getContentPane().remove(textFieldMatriz2[i][j]);
				}
			}
			textFieldMatriz2 = null;
		}
           
		if (textFieldMatrizResultado != null) {
			for (int i=0; i<textFieldMatrizResultado.length; i++) {
				for (int j=0; j<textFieldMatrizResultado[i].length; j++) {
					frame.getContentPane().remove(textFieldMatrizResultado[i][j]);
				}
			}
			textFieldMatrizResultado = null;
		}

		frame.revalidate();
		frame.repaint();
	}
}

