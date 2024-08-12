import java.awt.Color;

public class EquipoFutbol {
    private String nombre;
    private Color[] colores;

    public EquipoFutbol(String nombre, Color[] colores) {
        this.nombre = nombre;
        this.colores = colores;
    }

    public String getNombre() {
        return nombre;
    }

    public Color[] getColores() {
        return colores;
    }
}