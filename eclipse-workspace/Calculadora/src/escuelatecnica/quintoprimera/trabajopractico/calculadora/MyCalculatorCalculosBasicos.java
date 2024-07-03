package escuelatecnica.quintoprimera.trabajopractico.calculadora;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class MyCalculatorCalculosBasicos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField txtPantalla;

    // Variables
    List<Double> numeros;
    List<String> operaciones;
	
	public MyCalculatorCalculosBasicos() {
		numeros = new ArrayList<>();
        operaciones = new ArrayList<>();

        setTitle("CalculosBasicos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 340);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnBack = new JButton("Volver");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(10, 283, 117, 29);
		getContentPane().add(btnBack);
        btnBack.setFont(new Font("Arial", Font.BOLD, 15));
        
        txtPantalla = new JTextField();
        txtPantalla.setHorizontalAlignment(SwingConstants.RIGHT);
        txtPantalla.setFont(new Font("Arial", Font.BOLD, 18));
        txtPantalla.setBounds(10, 11, 300, 47);
        contentPane.add(txtPantalla);
        txtPantalla.setColumns(10);

        
        ActionListener numberListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String IngreseNumero = txtPantalla.getText() + ((JButton) e.getSource()).getText();
                txtPantalla.setText(IngreseNumero);
            }
        };

        
        String[] buttonLabels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        int[] buttonBounds = {
            10, 69, 52, 47,   72, 69, 52, 47,   134, 69, 52, 47,
            10, 122, 52, 47,  72, 122, 52, 47,  134, 122, 52, 47,
            10, 178, 52, 47,  72, 178, 52, 47,  134, 178, 52, 47,
            10, 236, 52, 47
        };

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.addActionListener(numberListener);
            button.setFont(new Font("Arial", Font.BOLD, 15));
            button.setBounds(buttonBounds[i * 4], buttonBounds[i * 4 + 1], buttonBounds[i * 4 + 2], buttonBounds[i * 4 + 3]);
            contentPane.add(button);
        }

        // Botones de operaciones
        addButton("C", 196, 69, 114, 47, new Color(255, 0, 0), e -> clearScreen());
        addButton("+", 196, 122, 52, 47, new Color(255, 0, 0), e -> setOperation("+"));
        addButton("-", 258, 122, 52, 47, new Color(255, 0, 0), e -> setOperation("-"));
        addButton("\u00D7", 196, 178, 52, 47, new Color(255, 0, 0), e -> setOperation("*"));
        addButton("\u00F7", 258, 178, 52, 47, new Color(255, 0, 0), e -> setOperation("/"));
        addButton("^", 196, 236, 52, 47, new Color(255, 0, 0), e -> setOperation("^"));
        addButton("\u221A", 258, 236, 52, 47, new Color(255, 0, 0), e -> calculateSquareRoot());

        // Botón igual
        addButton("=", 72, 236, 114, 47, new Color(255, 0, 0), e -> calculateResult());
    }
    
    private void addButton(String text, int x, int y, int width, int height, Color color, ActionListener listener) {
        JButton button = new JButton(text);
        button.setForeground(color);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setBounds(x, y, width, height);
        button.addActionListener(listener);
        contentPane.add(button);
    }

    private void setOperation(String op) {
        try {
            double numero = Double.parseDouble(txtPantalla.getText().split(" ")[txtPantalla.getText().split(" ").length - 1]);
            numeros.add(numero);
            operaciones.add(op);
            txtPantalla.setText(txtPantalla.getText() + " " + op + " ");
        } catch (NumberFormatException e) {
            txtPantalla.setText("Error");
        }
    }

    private void calculateResult() {
        try {
            String[] tokens = txtPantalla.getText().split(" ");
            if (tokens.length < 3) return;  // No hay suficiente información para calcular

            numeros.clear();
            operaciones.clear();
            for (String token : tokens) {
                if (isNumeric(token)) {
                    numeros.add(Double.parseDouble(token));
                } else {
                    operaciones.add(token);
                }
            }

            double resultado = numeros.get(0);
            for (int i = 0; i < operaciones.size(); i++) {
                switch (operaciones.get(i)) {
                    case "+": resultado += numeros.get(i + 1); break;
                    case "-": resultado -= numeros.get(i + 1); break;
                    case "*": resultado *= numeros.get(i + 1); break;
                    case "/": resultado /= numeros.get(i + 1); break;
                    case "^": resultado = Math.pow(resultado, numeros.get(i + 1)); break;
                }
            }
            txtPantalla.setText(String.valueOf(resultado));
        } catch (Exception e) {
            txtPantalla.setText("Error");
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void calculateSquareRoot() {
        try {
            double numero = Double.parseDouble(txtPantalla.getText().split(" ")[txtPantalla.getText().split(" ").length - 1]);
            double resultado = Math.sqrt(numero);
            txtPantalla.setText(String.valueOf(resultado));
        } catch (NumberFormatException e) {
            txtPantalla.setText("Error");
        }
    }

    private void clearScreen() {
        txtPantalla.setText("");
        numeros.clear();
        operaciones.clear();
	}

}
