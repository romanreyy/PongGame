package Matrices;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FourthScreen extends JFrame{

	private JFrame mainFrame;
	
	public FourthScreen() {
		this.mainFrame = mainFrame;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
	            mainFrame.setVisible(true);
			}
		});
		btnNewButton.setBounds(296, 153, 117, 29);
		getContentPane().add(btnNewButton);
	}

}
