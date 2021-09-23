package problema_382;

import java.util.Scanner;

public class MovilMetro_382 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in).useDelimiter("\n");

		// CASOS DE PRUEBA
		int casos = sc.nextInt();

		for (; casos > 0; casos--) {

			// LONGITUD TUNELES Y NUMERO DE ANTENAS
			String[] entrada = sc.next().split(" ");

			final int longTunel = Integer.parseInt(entrada[0]);

			final int numAntenas = Integer.parseInt(entrada[1]);

			// DATOS ANTENAS
			entrada = sc.next().split(" ");

			int tramoCubierto = 0;
			int posicion = 0;
			// int cobertura = 0;

			for (int j = 0; j < numAntenas * 2; j++) {
				if (j % 2 == 0) {
					posicion = Integer.parseInt(entrada[j]);
				} else {
					int cobertura = Integer.parseInt(entrada[j]);
					if ((tramoCubierto < posicion + cobertura) && (tramoCubierto >= posicion - cobertura))
						tramoCubierto = posicion + cobertura;
				}
			}

			// VERIFICAR QUE CUBRE HASTA FINAL DEL TUNEL
			if (tramoCubierto >= longTunel)
				System.out.println("SI");
			else
				System.out.println("NO");

		}

		sc.close();
	}
}
