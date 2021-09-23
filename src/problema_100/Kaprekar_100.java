/**
 * 
 */
package problema_100;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author juanluis Problema Id 100 - Constante de Kaprekar
 *         https://www.aceptaelreto.com/problem/statement.php?id=100
 */
public class Kaprekar_100 {

	private static boolean isRepDigits(char[] cifras) {
		int i = 1;
		do {
			if (cifras[0] != cifras[i])
				return false;
			i++;
		} while (i < cifras.length);
		return true;
	}

	private static char[] formarNumero(String cifras) {
		cifras = "0000" + cifras;
		return cifras.substring(cifras.length() - 4).toCharArray();
	}

	private static int obtenerMayor(char[] cifras) {
		StringBuffer sb = new StringBuffer(String.valueOf(cifras));
		return Integer.valueOf(sb.reverse().toString());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final String CT_KAPREKAR = "6174";
		int numPruebas;
		int menor;
		int mayor;
		int numero = 0;
		int ciclos;
		char[] entrada;

		Scanner sc = new Scanner(System.in);
		numPruebas = Integer.valueOf(sc.nextLine());

		for (int prueba = 0; prueba < numPruebas; prueba++) {
			entrada = sc.nextLine().toCharArray();

			ciclos = 0;

			while (!String.valueOf(entrada).equals(CT_KAPREKAR)) {
				entrada = formarNumero(String.valueOf(entrada));
				if (isRepDigits(entrada)) {
					ciclos = 8;
					break;
				}
				Arrays.sort(entrada);
				menor = Integer.valueOf(String.valueOf(entrada));
				mayor = obtenerMayor(entrada);
				numero = mayor - menor;
				entrada = Integer.toString(numero).toCharArray();
				ciclos++;
			}
			System.out.println(ciclos);
		}
		sc.close();
		//System.exit(0);
	}

}
