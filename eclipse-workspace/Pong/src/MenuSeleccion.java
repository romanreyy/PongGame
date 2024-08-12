import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuSeleccion extends JPanel {
    private static final long serialVersionUID = 1L;
	private ArrayList<EquipoFutbol> equipos;
    private JComboBox<String> equipoIzquierda;
    private JComboBox<String> equipoDerecha;
    private JButton startButton;
    private EquipoFutbol equipoSeleccionadoIzquierda;
    private EquipoFutbol equipoSeleccionadoDerecha;

    public MenuSeleccion() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(400, 300));

        equipos = new ArrayList<>();
        equipos.add(new EquipoFutbol("Boca Juniors", new Color[]{new Color(10, 40, 107), new Color(255, 230, 0), new Color(10, 40, 107)}));
        equipos.add(new EquipoFutbol("River Plate", new Color[]{Color.WHITE, new Color(206, 17, 38), Color.WHITE}));
        equipos.add(new EquipoFutbol("Independiente", new Color[]{new Color(206, 17, 38)}));
        equipos.add(new EquipoFutbol("Racing", new Color[]{Color.WHITE, new Color(108, 174, 223), Color.WHITE}));
        equipos.add(new EquipoFutbol("San Lorenzo", new Color[]{new Color(19, 39, 79), new Color(206, 17, 38), new Color(19, 39, 79)}));

        String[] nombreEquipos = equipos.stream().map(EquipoFutbol::getNombre).toArray(String[]::new);

        JLabel labelIzquierda = new JLabel("Equipo Izquierda:");
        equipoIzquierda = new JComboBox<>(nombreEquipos);
        
        JLabel labelDerecha = new JLabel("Equipo Derecha:");
        equipoDerecha = new JComboBox<>(nombreEquipos);

        startButton = new JButton("Iniciar Partido");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equipoSeleccionadoIzquierda = equipos.get(equipoIzquierda.getSelectedIndex());
                equipoSeleccionadoDerecha = equipos.get(equipoDerecha.getSelectedIndex());
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(MenuSeleccion.this);
                frame.getContentPane().removeAll();
                frame.add(new fondo(equipoSeleccionadoIzquierda, equipoSeleccionadoDerecha));
                frame.pack();
                frame.revalidate();
            }
        });

        add(Box.createVerticalGlue());
        add(labelIzquierda);
        add(equipoIzquierda);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(labelDerecha);
        add(equipoDerecha);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(startButton);
        add(Box.createVerticalGlue());

        equipoIzquierda.setAlignmentX(Component.CENTER_ALIGNMENT);
        equipoDerecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}