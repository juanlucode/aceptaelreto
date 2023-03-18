/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comun;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 *
 * @author juanluis.garciar
 */
public class Consola {

    private InputStream stdin;
    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream ps;
    private PrintStream stdout;

    public Consola() {
        stdin = System.in;
        byteArrayOutputStream = new ByteArrayOutputStream();
        ps = new PrintStream(byteArrayOutputStream);
        stdout = System.out;
        System.setOut(ps);
    }

    public void setEntrada(String[] _entrada) {
        //String cadena = String.join("\n", _entrada);
        StringBuilder cadena = new StringBuilder();
        for (String linea : _entrada)
            cadena.append(linea.concat("\n"));
        System.setIn(new ByteArrayInputStream(cadena.toString().getBytes()));
        
    }

    public String[] getSalida() {
        System.setIn(stdin);
        System.setOut(stdout);

        return byteArrayOutputStream.toString().split("\\r?\\n");
    }

}
