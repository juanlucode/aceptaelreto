package problema_109;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class LigaPadel {

    private static String[] entrada;
    private static Map<String, Integer> parejas = new HashMap<>();
    private static String ganador = null;
    private static Integer maxPuntos = 0;
    private static int totalPartidos = 0;

    private static void sumarPuntos(String _pareja, int _puntos){
        int aux = 0;
        if ( ! parejas.containsKey(_pareja) ) {
            parejas.put(_pareja, _puntos);
        } else {
            aux = parejas.get(_pareja) + _puntos;
            parejas.remove(_pareja);
            parejas.put(_pareja, aux);
        }
        if ( parejas.get(_pareja) > maxPuntos ){
            ganador = _pareja;
            maxPuntos = parejas.get(_pareja);
        } else if (parejas.get(_pareja) == maxPuntos ) {
            ganador = "EMPATE";
        }
    }

    private static int partidosNoJugados(){
        int rondas = 0;
        if ( parejas.size() % 2 == 0)
            rondas = parejas.size() * 2 -2;
        else rondas = parejas.size() * 2;

        return parejas.size()/2 * rondas - totalPartidos;
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
                        System.out.println(ganador.concat(" ").concat(String.valueOf(partidosNoJugados())));
                        parejas = new HashMap<>();
                        ganador = null;
                        maxPuntos = 0;
                        totalPartidos = 0;
                    }
                } else {
                    // imprimir categoría
                    //System.out.println(entrada[0]);
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
