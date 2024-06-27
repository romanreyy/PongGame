package Matrices;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;

public class SixthScreen extends JFrame{
	
	private JFrame mainFrame;
	
	public SixthScreen() {
		this.mainFrame = mainFrame;
		initialize();
	}

	
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
		btnNewButton.setBounds(140, 112, 117, 29);
		getContentPane().add(btnNewButton);
		
		
	}

}
