package operacionesMatriz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EscalarPorMatriz extends JFrame {

	private static final long serialVersionUID = 290465739965540897L;
	private JTextField[][] textFieldMatriz1;
	private JTextField[][] textFieldMatrizResultado;

	public EscalarPorMatriz(int x, int y, int width, int height) {
		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JButton btnBack = new JButton("Volver");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		btnBack.setBounds(22, 283, 117, 29);
		getContentPane().add(btnBack);

		JLabel lblMatriz1 = new JLabel("Matriz 1");
		lblMatriz1.setBounds(71, 21, 79, 16);
		getContentPane().add(lblMatriz1);

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

		JLabel numeroMultDeMatriz = new JLabel("Numero que multiplica a la matriz");
		numeroMultDeMatriz.setBounds(243, 53, 227, 16);
		getContentPane().add(numeroMultDeMatriz);

		JLabel lblNewLabel = new JLabel("x");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(181, 157, 61, 16);
		getContentPane().add(lblNewLabel);

		JTextField textFieldEscalar = new JTextField();
		textFieldEscalar.setBounds(294, 152, 98, 26);
		getContentPane().add(textFieldEscalar);
		textFieldEscalar.setColumns(10);

		JButton btnIgual = new JButton("=");
		btnIgual.setBounds(420, 141, 61, 29);
		getContentPane().add(btnIgual);

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

		btnIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Escalar escalarPorMatriz = new Escalar();
				escalarPorMatriz.escalarMatriz(textFieldMatriz1, textFieldEscalar,
				EscalarPorMatriz.this, textFieldMatrizResultado);
			}
		});
	}
}
