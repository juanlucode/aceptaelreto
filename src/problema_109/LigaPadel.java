package problema_109;

import java.util.Locale;
import java.util.Scanner;

public class LigaPadel {

    private static class Partido{
        private String ganador;
        private String perdedor;

        protected Partido(String _entradaPartido){
            String[] entrada = _entradaPartido.split(" ");
            if ( Byte.parseByte(entrada[1]) > Byte.parseByte(entrada[3]) ) {
                ganador = entrada[0];
                perdedor = entrada[2];
            } else {
                ganador = entrada[2];
                perdedor = entrada[0];
            }
        }

        protected String getGanador(){
            return ganador;
        }

        protected String getPerdedor(){
            return perdedor;
        }
    }
    public static void main(String[] args){
        String[] entrada;

        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        sc.useLocale(Locale.UK);
        while( sc.hasNext() ){
            entrada = sc.next().split(" ");
        }
        sc.close();
        System.exit(0);

    }
}
