package problema_110;

import java.util.Locale;
import java.util.Scanner;

public class Estrofas {

    private static String entrada;
    public static void main(String[] args){

        String[] estrofa = null;
        byte totalVersos = 0;
        byte iVerso = 0;

        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        sc.useLocale(Locale.UK);
        while( sc.hasNext() ){
            entrada = sc.next();
            if ( entrada.matches("[0-9]+") ){
                // NÚMERO DE VERSOS
                if ( estrofa != null ) System.out.println(analizarEstrofa(estrofa));
                totalVersos = Byte.valueOf(entrada);
                // FIN DE PROCESO
                if ( totalVersos == 0) break;
                // REINICIAR NUEVA ESTROFA
                estrofa = new String[totalVersos];
                iVerso = 0;

            } else {
                // VERSO
                // INCLUIR A ESTROFA
                estrofa[iVerso++] = entrada;
            }
        }
        sc.close();
        System.exit(0);

    }

    private static String analizarEstrofa(String[] estrofa) {

        return null;
    }
}
