package escuelatecnica.quintoprimera.trabajopractico.calculadora;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;



public class MyCalculatorMenuPrincipal {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyCalculatorMenuPrincipal window = new MyCalculatorMenuPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MyCalculatorMenuPrincipal() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(224, 255, 255));
		frame.getContentPane().setLayout(new GridLayout(4, 1, 10, 10));
		
		JButton btnOne = this.createNewButton("Calculos Basicos de Matrices", 6, 20, 268, 50);
		btnOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyCalculatorMatriz matrixCalculator = new MyCalculatorMatriz(100, 100, 643, 346);
				matrixCalculator.setVisible(true);
			}
		});
		frame.getContentPane().add(btnOne);
		
		JButton btnTwo = this.createNewButton("Sistema Ecuaciones", 6, 75, 268, 50);
		btnTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyCalculatorSistemaEcuaciones addScreen = new MyCalculatorSistemaEcuaciones();
				addScreen.setVisible(true);
			}
		});
		frame.getContentPane().add(btnTwo);
		
		JButton btnThree = this.createNewButton("Calculos de Vectores", 6, 130, 268, 50);
		btnThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyCalculatorVectores addScreen = new MyCalculatorVectores(100, 100, 643, 346);
				addScreen.setVisible(true);
			}
		});
		frame.getContentPane().add(btnThree);
		
		JButton btnFour = this.createNewButton("Calculos Basicos", 6, 185, 268, 50);
		btnFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyCalculatorCalculosBasicos addScreen = new MyCalculatorCalculosBasicos();
				addScreen.setVisible(true);
			}
		});
		frame.getContentPane().add(btnFour);
	}
	private JButton createNewButton(String name, int x, int y, int width, int height) {
		JButton btn = new JButton(name);
		btn.setBounds(x, y, width, height);
		return btn;
	}

}
