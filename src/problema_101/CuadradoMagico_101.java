package problema_101;

import java.util.*;

/*
 * @author juanluis
 * Problema 101 - Cuadrados mágicos diabólicos y esotéricos
 * https://www.aceptaelreto.com/problem/statement.php?id=101
 */
public class CuadradoMagico_101 {

	// declaraciones
	// dimensión del cuadrado
	private static short n = 0;

	// cuadrado
	private static int[] cuadrado;

	// tipos de cuadrados
	private static boolean esDiabolico;
	private static boolean esEsoterico;

	// constante magica
	private static int cm;

	private static void cargarCuadrado(String entrada){
		cuadrado = new int[n*n];
		StringTokenizer st = new StringTokenizer(entrada);
		int i = 0;
		while ( st.hasMoreTokens() )
			cuadrado[i++] = Integer.parseInt(st.nextToken().trim());
	}

	private static void comprobarDiabolico(){
		// Hallamos la constante mágica candidata sumando la primera fila.
		for (int i = 0; i < n; i++)
			cm += cuadrado[i];

		// comprobamos que la suma del resto de filas coincide con la constante mágica.
		// variable para comprobar las sumas
		int suma;
		for (int j = 1; j < n; j++) {
			suma = 0;
			for (int i = (int) (j * n); i < (j + 1) * n; i += 1) {
				suma += cuadrado[i];
			}
			if (suma != cm){
				esDiabolico = false;
				break;
			}

		}

		// comprobamos la suma columnas, si no se ha refutado que sea diabólico
		if (esDiabolico){
			for (int j = 0; j < n; j++) {
				suma = 0;
				for (int i = j; i <= n * (n - 1) + j; i += n) {
					suma += cuadrado[i];
				}
				if (suma != cm){
					esDiabolico = false;
					break;
				}
			}
		}

		// comprobamos diagonales
		// de izquierda a derecha
		if (esDiabolico){
			suma = 0;
			for (int i = 0; i < n*n; i += n+1) suma += cuadrado[i];
			if (suma != cm) esDiabolico = false;
		}
		// de derecha a izquierda
		if (esDiabolico){
			suma = 0;
			for (int i = (n-1); i <= n*n - n; i += n - 1) suma += cuadrado[i];
			if ( suma != cm) esDiabolico = false;
		}

	}

	private static void comprobarEsoterico(){
		// batería de comprobaciones para saber si es esotérico, solo si es diabólico
		if (esDiabolico){

			// calculamos la constante mágica 2
			int cm2 = 4*cm / n;

			// la suma de las cuatro esquinas debe ser igual a cm2
			if (cuadrado[0] + cuadrado[n-1] + cuadrado[n*n -n] + cuadrado[n*n - 1] != cm2)
				esEsoterico = false;

			// suma de casillas centrales
			if (esEsoterico){
				if ( n % 2 == 0){
					// es par

					// La suma de las dos casillas centrales de cada uno de los cuatro laterales
					// suman el doble de la constante mágica 2 (2 · CM2)
					if (cuadrado[(n / 2) - 1] + cuadrado[n / 2] +
							cuadrado[n * (n / 2) - 1] + cuadrado[n * (n / 2) - 1 + n] +
							cuadrado[n * (n - 1) + (n / 2) - 1] + cuadrado[n * (n - 1) + (n / 2)] +
							cuadrado[(n / 2) * n - n] + cuadrado[(n / 2) * n] != 2*cm2
					) esEsoterico = false;

					// La suma de las cuatro casillas centrales da como resultado la constante mágica 2.
					if ( 	esEsoterico &&
							cuadrado[ ((n / 2) - 1) + ((n / 2) * n - n) ] +
									cuadrado[((n / 2) - 1) + ((n / 2) * n - n + 1)] +
									cuadrado[((n / 2) - 1) + ( (n / 2) * n)] +
									cuadrado[((n / 2) - 1) + ( (n / 2) * n + 1)] != cm2
					) esEsoterico = false;


				} else {
					// es impar

					// La suma de las cifras de las cuatro casillas de
					// la mitad de los laterales suman la constante mágica 2.
					if ( 	cuadrado[(n - 1)/2] +
							cuadrado[ n * (n / 2)] +
							cuadrado[(n - 1) + n / 2 * n] +
							cuadrado[n * (n - 1) + n / 2] != cm2
					) esEsoterico = false;

					// Si se multiplica el valor de la casilla central por 4,
					// se obtiene la constante mágica 2.

					if (esEsoterico && 4 * cuadrado[(n * n - 1) / 2] != cm2)
						esEsoterico = false;
				}

				// comprobamos, si es diabólico, si además es esotérico
				// La 1ª condición es que tenga todos los números naturales de 1 a n*n
				if (esEsoterico){
					Arrays.sort(cuadrado);
					for (int i = 0; i < n*n; i++){
						if ( cuadrado[i] != i + 1 ){
							esEsoterico = false;
							break;
						}
					}
				}
			}
		}  else esEsoterico = false; // si no es diabólico no puede ser esotérico

	}

	private static void mostrarResultado(){
		if (esEsoterico)
			System.out.println("ESOTERICO");
		else
		if (esDiabolico)
			System.out.println("DIABOLICO");
		else
			System.out.println("NO");

	}

	public static void main(String[] args) {

		// ENTRADA
		/*
		 * Los cuadrados se leen uno tras otro. Cada cuadrado tiene tiene dos líneas: 1
		 * - nº de filas/ columnas 2 - valores de celdas.
		 *
		 * Se finaliza cuando le llega 0 a nº de filas/columnas
		 */

		Scanner sc = new Scanner(System.in).useDelimiter("\n");
		sc.useLocale(Locale.UK);

		/*
		 * tipoEntrada tomará el valor 1 ó 2 alternativamente: 1: nº filas/columna 2:
		 * valores
		 */
		byte tipoEntrada = 1;

		while (sc.hasNext()) {
			// identificar tipo de entrada
			if (tipoEntrada == 1) {
				// dimensionar cuadrado
				n = Short.parseShort(sc.next().trim());
				if (n == 0) break;
				tipoEntrada = 2;
			} else {
				tipoEntrada = 1;
				// Extraemos de la cadena la matriz que describe al cuadrado

				// cargar cuadrado
				cargarCuadrado(sc.next().trim());

				// Partimos afirmando ambos tipos, que luego se tratará de rebatir
				esDiabolico = true;
				esEsoterico = true;

				// inicializar constante magica
				cm = 0;

				// comprobar si es diabólico
				comprobarDiabolico();

				// comprobar si es esotérico
				comprobarEsoterico();

				// mostrar resultado
				mostrarResultado();
			}
		}

		sc.close();
		System.exit(0);
	}

}