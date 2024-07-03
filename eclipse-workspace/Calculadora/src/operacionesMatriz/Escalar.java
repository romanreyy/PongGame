package operacionesMatriz;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Escalar {
	public void escalarMatriz(JTextField[][] textFieldMatriz1, JTextField textFieldEscalar, JFrame frame, JTextField[][] textFieldMatrizResultado) {
		
		if (textFieldMatriz1 == null) {
            return;
            }
		
		
		int filas1 = textFieldMatriz1.length;
        int columnas1 = textFieldMatriz1[0].length;
        
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
        
        
		for (int i=0; i<filas1; i++) {
			for(int j=0; j<columnas1; j++) {
				textFieldMatrizResultado[i][j] = new JTextField();
    			textFieldMatrizResultado[i][j].setBounds(x + j * (width + 5), y + i * (height + 5), width, height);
    			textFieldMatrizResultado[i][j].setEditable(false);
    			textFieldMatrizResultado[i][j].getHorizontalAlignment();
    			textFieldMatrizResultado[i][j].setEditable(false);
    			int valor1 = Integer.parseInt(textFieldMatriz1[i][j].getText());
    			int valor2 = Integer.parseInt(textFieldEscalar.getText());
    			int multEscalar = valor1 * valor2;
    			textFieldMatrizResultado[i][j].setText(String.valueOf(multEscalar));
    			frame.getContentPane().add(textFieldMatrizResultado[i][j]);
			}
		}
		frame.revalidate();
		frame.repaint();
	}	
}
