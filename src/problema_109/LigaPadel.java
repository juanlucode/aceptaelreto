package problema_109;

import java.util.Locale;
import java.util.Scanner;

public class LigaPadel {


    public static void main(String[] args){
        String[] entrada;


        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        sc.useLocale(Locale.UK);
        while( sc.hasNext() ){
            entrada = sc.next().split(" ");
            if ( entrada.length == 1 ){
                // categoría
            } else {
                // partido
                if ( Byte.parseByte(entrada[1]) > Byte.parseByte(entrada[3]) ) {
                    //ganador = entrada[0];
                    //perdedor = entrada[2];
                } else {
                    //ganador = entrada[2];
                    //perdedor = entrada[0];
                }
            }
            }
        }
        sc.close();
        System.exit(0);

    }
}
