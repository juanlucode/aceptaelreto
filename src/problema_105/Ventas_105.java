package problema_105;

import java.util.Scanner;

public class Ventas_105 {
	private enum Dias {
		MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in).useDelimiter("\n");
		double venta;
		double maximo;
		String diaMax = "";
		double minimo;
		String diaMin = "";
		double total;
		do {
			venta = 0;
			maximo = 0.0;
			minimo = -1.0;
			total = 0.0;
			for (Dias dia : Dias.values()) {
				// venta = sc.nextDouble();
				venta = Double.parseDouble(sc.next());
				if (venta == -1 && dia == Dias.MARTES)
					break;
				if (venta > maximo) {
					maximo = venta;
					diaMax = dia.toString();
				}
				if (venta < minimo || minimo < 0) {
					minimo = venta;
					diaMin = dia.toString();
				}
				total += venta;
				if (dia == Dias.DOMINGO) {
					if (venta < total / 6)
						total *= -1;
				}
			}
			if (venta == -1)
				break;
			if (diaMax.equals(diaMin))
				System.out.print("EMPATE");
			else
				System.out.print(diaMax + " " + diaMin);
			System.out.print(" ");
			if (total > 0)
				System.out.println("SI");
			else
				System.out.println("NO");

		} while (sc.hasNext());

		sc.close();
		System.exit(0);
	}
}
