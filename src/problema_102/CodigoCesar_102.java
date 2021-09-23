package problema_102;

import java.util.Scanner;

public class CodigoCesar_102 {

	/*
	 * CÓDIGOS ASCII
	 * 
	 * A .. Z --> 65 .. 90
	 * 
	 * a .. z --> 97 .. 122
	 * 
	 */

	private static final byte P_MAYUS = (byte) 'P';
	private static final byte P_MINUS = (byte) 'p';

	private static boolean esLetra(char letra) {
		if ((letra > 64 && letra < 91) || (letra > 96 && letra < 123))
			return true;
		else
			return false;
	}

	private static boolean esMayuscula(char letra) {
		if (letra > 64 && letra < 91)
			return true;
		else
			return false;
	}

	private static short calcularDesplazamiento(char letra) {
		if (esMayuscula(letra)) {
			return (short) (P_MAYUS - letra);
		} else {
			return (short) (P_MINUS - letra);
		}
	}

	private static char aplicarDesp(char caracter, short desplazamiento) {
		int nuevo;
		nuevo = (byte) caracter + desplazamiento;
		if (esMayuscula(caracter)) {
			// Mayúscula
			if (nuevo > 90)
				nuevo = nuevo - 26;
			else if (nuevo < 65)
				nuevo = nuevo + 26;
		} else {
			// Minúscula
			if (nuevo > 122)
				nuevo = nuevo - 26;
			else if (nuevo < 97)
				nuevo = nuevo + 26;
		}
		return (char) nuevo;
	}

	private static String descifrar(String texto) {
		char[] caracteres = texto.toCharArray();

		short desplazamiento = calcularDesplazamiento(caracteres[0]);
		for (short i = 0; i < caracteres.length; i++) {
			if (esLetra(caracteres[i]))
				caracteres[i] = aplicarDesp(caracteres[i], desplazamiento);
		}
		return String.valueOf(caracteres);
	}

	private static short contarVocales(String texto) {
		short contador = 0;
		final String vocales = "AEIOUaeiou";
		for (short i = 0; i < texto.toCharArray().length; i++) {
			if (vocales.indexOf(texto.toCharArray()[i]) >= 0)
				contador++;
		}
		return contador;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in).useDelimiter("\n");
		String entrada;

		while (sc.hasNext()) {
			entrada = sc.next();
			entrada = descifrar(entrada);
			if (entrada.equals("pFIN"))
				break;
			System.out.println(contarVocales(entrada));
		}
		sc.close();
		System.exit(0);
	}

}
