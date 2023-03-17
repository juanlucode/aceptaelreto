package problema_109;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class LigaPadel {

    private static void sumarPuntos(Map<String, Integer> _parejas, String _pareja, Integer _puntos, Integer _maxPuntos, String _ganador){
        int aux = 0;
        if ( ! _parejas.containsKey(_pareja) ) {
            _parejas.put(_pareja, _puntos);
        } else {
            aux = _parejas.get(_pareja) + _puntos;
            _parejas.remove(_pareja);
            _parejas.put(_pareja, aux);
        }
        if ( _parejas.get(_pareja) > _maxPuntos ){
            _ganador = _pareja;
            _maxPuntos = _parejas.get(_pareja);
        } else if (_parejas.get(_pareja) == _maxPuntos ) {
            _ganador = "EMPATE";
        }
    }

    private static int partidosNoJugados(Map<String, Integer> _parejas, int _totalPartidos){
        int rondas = 0;
        if ( _parejas.size() % 2 == 0)
            rondas = _parejas.size() * 2 -2;
        else rondas = _parejas.size() * 2;

        return _parejas.size()/2 * rondas - _totalPartidos;
    }
    public static void main(String[] args){
        String[] entrada;
        Map<String, Integer> parejas = new HashMap<>();
        String ganador = null;
        Integer maxPuntos = 0;
        int totalPartidos = 0;

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
                        System.out.println(ganador.concat(" ").concat(String.valueOf(partidosNoJugados(parejas, totalPartidos))));
                        parejas = new HashMap<>();
                        ganador = null;
                        maxPuntos = 0;
                        totalPartidos = 0;
                    }
                } else {
                    System.out.println(entrada[0]);
                }
            } else {
                // partido
                if ( Byte.parseByte(entrada[1]) > Byte.parseByte(entrada[3]) ) {
                    sumarPuntos(parejas, entrada[0], 2, maxPuntos, ganador);
                    sumarPuntos(parejas,entrada[2], 1, maxPuntos, ganador);
                } else {
                    sumarPuntos(parejas, entrada[2], 2,maxPuntos, ganador);
                    sumarPuntos(parejas,entrada[0], 1, maxPuntos, ganador);
                }
                totalPartidos++;
            }
        }
        sc.close();
        System.exit(0);

    }
}
