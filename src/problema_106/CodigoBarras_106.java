package problema_106;

import java.util.Scanner;

public class CodigoBarras_106 {
    private enum Pais{
        EEUU("0"),
        Bulgaria("380"),
        Inglaterra("50"),
        Irlanda("539"),
        Portugal("560"),
        Noruega("70"),
        Venezuela("759"),
        Cuba("850"),
        India("890");

        private final String codigo;

        Pais(String codigo){
            this.codigo = codigo;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in).useDelimiter("\n");

        String entrada;
        while( sc.hasNext() ){
            entrada = sc.next();

            if ( entrada.equals("0") ) break;

            if ( entrada.length() < 8 ) {
                entrada = "00000000".substring(entrada.length()).concat(entrada);
            } else
                if (entrada.length() > 8 && entrada.length() < 13 )
                    entrada = "0000000000000".substring(entrada.length()).concat(entrada);

            int longitud = entrada.length();

            // validar
            int valor = 0;
            int factor;
            for (int i = longitud - 2; i >= 0; i--){
                if ( (longitud - i - 1) % 2 == 0 )
                    factor = 1;
                else
                    factor = 3;

                valor += factor * Integer.parseInt((entrada.substring(i, i+1)));
            }

            if ( (valor + Integer.parseInt(entrada.substring(longitud-1))) % 10 == 0 ){
                // valido
                switch (longitud){
                    case 8:
                        System.out.println("SI");
                        break;
                    case 13:
                        String pais = "Desconocido";
                        for (Pais pais2 : Pais.values()){
                            if (entrada.startsWith( pais2.codigo )){
                                pais = pais2.name();
                                break;
                            }
                        }
                        System.out.println("SI " + pais);
                }
            } else {
                // no valido
                System.out.println("NO");
            }

        }
        sc.close();
        System.exit(0);
    }
}
