package problema_104;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Moviles_104 {

	private static Integer[] leerEntrada(String entrada) {
		if (entrada.equals("\n")) {
			// SI SE DETECTA \n FINAL
			return null;
		} else {
			Integer[] valores = new Integer[4];
			StringTokenizer st = new StringTokenizer(entrada);
			for (byte i = 0; st.hasMoreTokens(); i++)
				valores[i] = Integer.parseInt(st.nextToken());
			return valores;
		}
	}

	private static Integer esBalanceado(Scanner entrada) {

		// SI ESTA BALANCEADO DEVUELVE EL PESO (int)
		// SI NO ESTA BALANCEADO DEVUELVE null

		final byte PI = 0;
		final byte DI = 1;
		final byte PD = 2;
		final byte DD = 3;

		Integer peso = null;

		if (entrada.hasNext()) {
			Integer[] movil;
			movil = leerEntrada(entrada.next());

			// SI SE HA LEIDO EL \n FINAL
			if (movil == null)
				return 0;

			/*
			 * System.out.print(movil[PI] + " "); System.out.print(movil[DI] + " ");
			 * System.out.print(movil[PD] + " "); System.out.print(movil[DD]);
			 * System.out.println();
			 */

			if (movil[PI] == 0 && movil[DI] == 0 && movil[PD] == 0 && movil[DD] == 0) {
				// MOVIL FINAL
				return 0;
			}

			if (movil[PI] == 0) {
				// EXISTE SUB-MOVIL IZQUIERDO
				peso = esBalanceado(entrada);
				// if (peso == null) return null; else movil[PI] = peso;
				movil[PI] = peso;
			}
			if (movil[PD] == 0) {
				// EXISTE SUB-MOVIL DERECHO
				peso = esBalanceado(entrada);
				// if (peso == null) return null; else movil[PD] = peso;
				movil[PD] = peso;
			}

			if (movil[PI] == null || movil[PD] == null)
				peso = null;
			else {
				// ES UN MOVIL COMPLETO
				if (movil[PI] * movil[DI] == movil[PD] * movil[DD])
					peso = movil[PI] + movil[PD];
				else
					peso = null;
			}
		}

		return peso;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in).useDelimiter("\n");

		Integer result;
		do {
			result = esBalanceado(sc);
			if (result == null)
				System.out.println("NO");
			else if (result == 0)
				break;
			else
				System.out.println("SI");
		} while (sc.hasNext());
		sc.close();

	}

}
