/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema_100;

import comun.Consola;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

/**
 *
 * @author juanluis.garciar
 */
class Kaprekar_100Test {

// https://www.logicbig.com/how-to/junit/java-test-user-command-line-input.html
    /**
     * Test of main method, of class Kaprekar_100.
     */
    @Test
    void testMain() {

        Consola consola = new Consola();
        consola.setEntrada(new String[]{"5", "3524", "1111", "1121", "6174", "1893"});
        // INVOCACIÓN
        Kaprekar_100.main(null);

        String[] output = consola.getSalida();
        String[] expect = {"3", "8", "5", "0", "7"};
        assertArrayEquals(expect, output);

    }

}
