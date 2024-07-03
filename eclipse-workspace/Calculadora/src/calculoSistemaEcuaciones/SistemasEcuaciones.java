package calculoSistemaEcuaciones;

public class SistemasEcuaciones {
	public static String resolverSistema2x2(double[][] coef) {
        double a1 = coef[0][0];
        double b1 = coef[0][1];
        double c1 = coef[0][2];
        double a2 = coef[1][0];
        double b2 = coef[1][1];
        double c2 = coef[1][2];

        double det = a1 * b2 - a2 * b1;
        if (det == 0) {
            return "No tiene solución única";
        } else {
            double x = (c1 * b2 - c2 * b1) / det;
            double y = (a1 * c2 - a2 * c1) / det;
            return "x = " + x + ", y = " + y;
        }
    }

    public static String resolverSistema3x3(double[][] coef) {
        double a1 = coef[0][0];
        double b1 = coef[0][1];
        double c1 = coef[0][2];
        double d1 = coef[0][3];
        double a2 = coef[1][0];
        double b2 = coef[1][1];
        double c2 = coef[1][2];
        double d2 = coef[1][3];
        double a3 = coef[2][0];
        double b3 = coef[2][1];
        double c3 = coef[2][2];
        double d3 = coef[2][3];

        double det = a1 * (b2 * c3 - b3 * c2) - b1 * (a2 * c3 - a3 * c2) + c1 * (a2 * b3 - a3 * b2);
        if (det == 0) {
            return "No tiene solución única";
        } else {
            double x = (d1 * (b2 * c3 - b3 * c2) - b1 * (d2 * c3 - d3 * c2) + c1 * (d2 * b3 - d3 * b2)) / det;
            double y = (a1 * (d2 * c3 - d3 * c2) - d1 * (a2 * c3 - a3 * c2) + c1 * (a2 * d3 - a3 * d2)) / det;
            double z = (a1 * (b2 * d3 - b3 * d2) - b1 * (a2 * d3 - a3 * d2) + d1 * (a2 * b3 - a3 * b2)) / det;
            return "x = " + x + ", y = " + y + ", z = " + z;
        }
    }
}
