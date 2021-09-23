package problema_107;

import java.util.*;

public class AproximacionGauss_107 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        sc.useLocale(java.util.Locale.UK);

        String[] entrada;
        int numero;
        int factorError;
        double error;
        float[] maxError = {1f, 0.1f, 0.01f, 0.001f, 0.0001f, 0.00001f};
        int primos;
        TreeMap<Integer, Integer> primosMap = new TreeMap<>();
        while (sc.hasNext()){
            entrada = sc.next().split(" ");

            numero = Integer.parseInt(entrada[0].trim());
            factorError = Integer.parseInt(entrada[1].trim());

            if (numero == 0 && factorError == 0) break;

            if (numero == 1) {
                System.out.println("Menor");
                continue;
            }

            if (numero == 0) continue;

            // calcular cantidad numeros primos precedentes

            // comprobamos si ya está
            if (primosMap.containsKey(numero))
                primos = primosMap.get(numero);
            else {
                // si no está buscamos el inmediatamente anterior almacenado, si lo hubiera
                Map.Entry<Integer, Integer> anterior = null;
                int inicio;
                for (Map.Entry<Integer, Integer> num : primosMap.entrySet()){
                    if ( num.getKey() < numero) anterior = num; else break;
                }
                if ( anterior == null) {
                    inicio = 2;
                    primos = 0;
                } else {
                    inicio = anterior.getKey() + 1;
                    primos = anterior.getValue();
                }
                for (int i = inicio; i <= numero; i++){
                    if (esPrimo(i)) primos++;
                }
                // lo almacenamos en el map para posteriores usos
                primosMap.put(numero, primos);
            }


            // calcular error aproximacion
            error = Math.abs((double)primos / numero - (double) 1 / Math.log(numero));

            if (error > maxError[factorError])
                System.out.println("Mayor");
            else
                if ( error < maxError[factorError])
                    System.out.println("Menor");
                else
                    System.out.println("Igual");

        }

        sc.close();
        System.exit(0);
    }

    private static boolean esPrimo(int numero){
        boolean ok = true;
        if (numero < 2)
            ok = false;
        else
            for (int i = 2; i * i <= numero; i++) {
                if (numero % i == 0) {
                    ok = false;
                    break;
                }
            }
        return ok;
    }
}

