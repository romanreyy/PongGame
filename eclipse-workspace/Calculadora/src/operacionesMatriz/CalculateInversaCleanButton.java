package operacionesMatriz;


import javax.swing.JFrame;
import javax.swing.JTextField;

public class CalculateInversaCleanButton {
	
	public void calculateInversa(JTextField[][] textFieldMatriz1, JTextField[][] textFieldMatrizResultado, JFrame frame) {
		
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
