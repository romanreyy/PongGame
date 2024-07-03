package operacionesMatriz;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class Matriz2Numbers {

	public void completeMatriz2(JTextField[][] textFieldMatriz2, JFrame frame, JComboBox<String> matriz2) {
		String seleccion = (String) matriz2.getSelectedItem();
        int filas = Character.getNumericValue(seleccion.charAt(0));
        int columnas = Character.getNumericValue(seleccion.charAt(2));

        if (textFieldMatriz2 != null) {
        	for (int i = 0; i < textFieldMatriz2.length; i++) {
                for (int j = 0; j < textFieldMatriz2[i].length; j++) {
                	frame.getContentPane().remove(textFieldMatriz2[i][j]);
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
                frame.getContentPane().add(textFieldMatriz2[i][j]);
            }
        }
        frame.revalidate();
        frame.repaint();
	}
}
