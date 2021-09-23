package problema_103;

import java.util.Scanner;
import java.util.StringTokenizer;

public class RepartoRiemann_103 {

	private static enum TipoEntrada {
		GRADO, COEFICIENTES, RECTANGULOS
	}

	private static enum EvaluacionReparto {
		CAIN, ABEL, JUSTO
	}

	private static void formarPolinomio(int[] ref, String cadena) {
		StringTokenizer st = new StringTokenizer(cadena);
		int i = ref.length - 1;
		while (st.hasMoreTokens() && i >= 0) {
			ref[i] = Integer.parseInt(st.nextToken());
			i -= 1;
		}
	}

	private static Double evaluarFuncion(int[] funcion, Double valor) {
		Double resultado = 0d;
		for (int i = 0; i < funcion.length; i++) {
			resultado += funcion[i] * Math.pow(valor, i);
		}
		/*
		 * Tomamos solo valores positivos para no ocupar tierras colindantes
		 */

		if (resultado < 0)
			resultado = 0d;
		else if (resultado > 1)
			resultado = 1d;
		return resultado;
	}

	private static Double sumaRiemann(int[] funcion, int rectangulos) {
		Double suma = 0d;

		for (int i = 0; i < rectangulos; i++) {
			suma += (double) evaluarFuncion(funcion, (double) i / rectangulos) / rectangulos;
		}

		return suma;
	}

	private static EvaluacionReparto evaluarReparto(double supCain, double supAbel) {
		double diferencia = supCain - supAbel;
		final double MARGEN = 0.001;
		if (diferencia > MARGEN)
			return EvaluacionReparto.CAIN;
		if (diferencia < -MARGEN)
			return EvaluacionReparto.ABEL;
		return EvaluacionReparto.JUSTO;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in).useDelimiter("\n");
		String entrada = "";
		TipoEntrada tipoEntrada = TipoEntrada.GRADO;
		short grado = 0;
		int[] polinomio = null;
		int rectangulos = 0;
		double cain = 0;
		double abel = 0;
		while (sc.hasNext()) {
			entrada = sc.next();
			switch (tipoEntrada) {
			case GRADO:
				grado = Short.parseShort(entrada);
				if (grado == 20)
					break;
				polinomio = new int[grado + 1];
				tipoEntrada = TipoEntrada.COEFICIENTES;
				break;
			case COEFICIENTES:
				formarPolinomio(polinomio, entrada);
				tipoEntrada = TipoEntrada.RECTANGULOS;
				break;
			case RECTANGULOS:
				rectangulos = Integer.parseInt(entrada);
				cain = sumaRiemann(polinomio, rectangulos);
				abel = 1 - cain;
				switch (evaluarReparto(cain, abel)) {
				case CAIN:
					System.out.println("CAIN");
					break;
				case ABEL:
					System.out.println("ABEL");
					break;
				case JUSTO:
					System.out.println("JUSTO");
					break;
				}
				tipoEntrada = TipoEntrada.GRADO;
				break;
			}
			if (grado == 20)
				break;
		}
		sc.close();
		System.exit(0);
	}

}
