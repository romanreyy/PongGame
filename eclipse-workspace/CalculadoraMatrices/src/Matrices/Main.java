package Matrices;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {
	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Main() {
		initialize();
	}
	
	private void initialize() {
		
		frame = new JFrame();
		frame.setBackground(Color.DARK_GRAY);
		frame.setTitle("Calculadora");
		frame.setBounds(100, 100, 557, 343);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		JButton btnOne = new JButton("SUMA");
		btnOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondScreen addScreen = new SecondScreen();
	            addScreen.setVisible(true);		
			}
		});
		btnOne.setBounds(39, 27, 283, 50);
		frame.getContentPane().add(btnOne);
		
		JButton btnTwo = new JButton("RESTA");
		btnTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThirdScreen addScreen = new ThirdScreen();
	            addScreen.setVisible(true);		
			}
		});
		btnTwo.setBounds(354, 27, 192, 50);
		frame.getContentPane().add(btnTwo);
		
		JButton btnThree = new JButton("Multiplicación de escalar por una matriz");
		btnThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FourthScreen addScreen = new FourthScreen();
	            addScreen.setVisible(true);		
			}
		});
		btnThree.setBounds(39, 87, 283, 50);
		frame.getContentPane().add(btnThree);
		
		JButton btnFour = new JButton("Inversa de una Matriz");
		btnFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FifthScreen addScreen = new FifthScreen();
	            addScreen.setVisible(true);		
			}
		});
		btnFour.setBounds(354, 150, 192, 48);
		frame.getContentPane().add(btnFour);
		
		JButton btnFive = new JButton("Multiplicación de matrices");
		btnFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SixthScreen addScreen = new SixthScreen();
	            addScreen.setVisible(true);		
			}
		});
		btnFive.setBounds(354, 211, 192, 48);
		frame.getContentPane().add(btnFive);
		
		JButton btnSix = new JButton("Multiplicación de escalar por vector");
		btnSix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeventhScreen addScreen = new SeventhScreen();
	            addScreen.setVisible(true);		
			}
		});
		btnSix.setBounds(39, 149, 283, 50);
		frame.getContentPane().add(btnSix);
		
		JButton btnSeven = new JButton("Determinante de una matriz");
		btnSeven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EighthScreen addScreen = new EighthScreen();
	            addScreen.setVisible(true);		
			}
		});
		btnSeven.setBounds(354, 89, 192, 50);
		frame.getContentPane().add(btnSeven);
		
		JButton btnEight= new JButton("División de matrices");
		btnEight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NinethScreen addScreen = new NinethScreen();
	            addScreen.setVisible(true);		
			}
		});
		btnEight.setBounds(39, 210, 283, 50);
		frame.getContentPane().add(btnEight);
	}
}
