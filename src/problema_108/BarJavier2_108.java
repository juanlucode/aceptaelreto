package problema_108;

import java.util.*;

import static java.lang.Math.abs;


/*

PRUEBAS
=======

CASOS EXTREMOS:
    _ días vacíos

REQUISITO 3:

   v Media comidas = total de comidas / nº de entradas "A" para ese día > media del día (excluída comida)

 */

public class BarJavier2_108 {

    private static class Dia{

        private int numComidas = 0;
        private float totalComidas = 0f;

        private int numResto = 0;
        private float totalResto = 0f;

        private final String CATEGORIAS = "DAMIC";

        private float[] ventas = {0f, 0f, 0f, 0f, 0f};

        private String denCategoria(char codigoCategoria){
            String denominacion = "";
            switch (codigoCategoria){
                case 'D':
                    denominacion = "DESAYUNOS";
                    break;
                case 'A':
                    denominacion =  "COMIDAS";
                    break;
                case 'M':
                    denominacion =  "MERIENDAS";
                    break;
                case 'I':
                    denominacion =  "CENAS";
                    break;
                case 'C':
                    denominacion =  "COPAS";
                    break;
            }
            return denominacion;
        }

        void anotar(String codigoCategoria, float beneficio ){

            ventas[CATEGORIAS.indexOf(codigoCategoria)] += beneficio;

            if ( codigoCategoria.equals("A") ){
                this.totalComidas += beneficio;
                this.numComidas++;
            } else {
                this.totalResto += beneficio;
                this.numResto++;
            }
        }

        @Override
        public String toString(){

            // valores iniciales que indican que no se han establecido
            byte catMaxVenta = -9;
            byte catMinVenta = -9;

            for ( byte i = 0; i < ventas.length; i++) {
                //evalualmos si es máxima venta
                if (catMaxVenta == -9 || ventas[i] >  ventas[abs(catMaxVenta)] ) {
                    catMaxVenta = i ;
                } else {
                    if ( ventas[i] == ventas[abs(catMaxVenta)] ) {
                        // el indice negativo indica que es empate
                        catMaxVenta = (byte) -i;

                    }
                }
                // igual para mínima venta...
                if ( catMinVenta == -9 || ventas[i] < ventas[abs(catMinVenta)]) {
                    catMinVenta = i;
                } else {
                    if ( ventas[i] == ventas[abs(catMinVenta)] ) {
                        catMinVenta = (byte) -i;
                    }
                }
            }
            StringBuilder cadena = new StringBuilder();
            // si el índice a categoría de Maxima venta es negativo es que existe empate
            if (catMaxVenta < 0)
                cadena.append("EMPATE");
            else
                cadena.append(denCategoria(CATEGORIAS.charAt(catMaxVenta)));
            cadena.append("#");
            if (catMinVenta < 0)
                cadena.append("EMPATE");
            else
                cadena.append(denCategoria(CATEGORIAS.charAt(catMinVenta)));
            cadena.append("#");
            if ( (this.totalComidas / this.numComidas) > (this.totalResto / this.numResto) )
                cadena.append("SI");
            else
                cadena.append("NO");

            return cadena.toString();
        }
    }

    public static void main(String[] args){
        String[] entrada;
        String categoria;
        float venta;
        Dia dia = new Dia();
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        sc.useLocale(Locale.UK);

        while( sc.hasNext() ){
            entrada = sc.next().split(" ");
            categoria = entrada[0].trim().toUpperCase();
            venta = Float.parseFloat(entrada[1].trim());

            if (categoria.equals("N")){
                System.out.println(dia);
                dia = new Dia();
            } else {
                dia.anotar(categoria, venta);
            }
        }
        sc.close();
        System.exit(0);

    }
}
