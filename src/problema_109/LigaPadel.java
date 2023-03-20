package problema_109;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class LigaPadel {

    private static String[] entrada;
    private static Map<String, Integer> parejas = new HashMap<>(2000);
    private static String ganador = null;
    private static Integer maxPuntos = 0;
    private static int totalPartidos = 0;

    private static void sumarPuntos(String _pareja, int _puntos){
        int aux = 0;
        if (  parejas.containsKey(_pareja) ) {
            aux = parejas.get(_pareja);
            parejas.remove(_pareja);
        }
        _puntos += aux;
        parejas.put(_pareja, _puntos);
        if ( _puntos > maxPuntos ){
            ganador = _pareja;
            maxPuntos = _puntos;
        } else if (_puntos == maxPuntos ) {
            ganador = "EMPATE";
        }
    }

    private static int partidosNoJugados(int _numParejas){
        int rondas = 0;
        if ( _numParejas % 2 == 0)
            rondas = _numParejas * 2 -2;
        else rondas = _numParejas * 2;

        return _numParejas/2 * rondas - totalPartidos;
    }
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        sc.useLocale(Locale.UK);
        while( sc.hasNext() ){
            entrada = sc.next().split(" ");
            if ( entrada.length == 1 ){
                if (entrada[0].equals("FIN")){
                    if ( parejas.size() == 0){
                        // FIN DE PROCESO
                        break;
                    } else {
                        // CÓMPUTO DE CATEGORÍA
                        System.out.println(ganador.concat(" ").concat(String.valueOf(partidosNoJugados(parejas.size()))));
                        parejas.clear();
                        ganador = null;
                        maxPuntos = 0;
                        totalPartidos = 0;
                    }
                }
            } else {
                // partido
                if ( Byte.parseByte(entrada[1]) > Byte.parseByte(entrada[3]) ) {
                    sumarPuntos(entrada[0], 2);
                    sumarPuntos(entrada[2], 1);
                } else {
                    sumarPuntos(entrada[2], 2);
                    sumarPuntos(entrada[0], 1);
                }
                totalPartidos++;
            }
        }
        sc.close();
        System.exit(0);

    }
}
