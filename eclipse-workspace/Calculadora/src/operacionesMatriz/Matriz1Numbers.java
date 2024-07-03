package operacionesMatriz;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Matriz1Numbers {

	public void completeMatriz1(JTextField[][] textFieldMatriz1, JFrame frame, JComboBox<String> matriz1) {
		
		String seleccion = (String) matriz1.getSelectedItem();
        int filas = Character.getNumericValue(seleccion.charAt(0));
        int columnas = Character.getNumericValue(seleccion.charAt(2));

        if (textFieldMatriz1 != null) {
            for (int i = 0; i < textFieldMatriz1.length; i++) {
                for (int j = 0; j < textFieldMatriz1[i].length; j++) {
                    frame.getContentPane().remove(textFieldMatriz1[i][j]);
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
                frame.getContentPane().add(textFieldMatriz1[i][j]);
            }
        }

        frame.revalidate();
        frame.repaint();
    }

}
