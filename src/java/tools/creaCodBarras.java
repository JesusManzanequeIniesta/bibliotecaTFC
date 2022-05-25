/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.util.Formatter;

/**
 *
 * @author yisus
 */
  public class creaCodBarras {

    private static final char[] LETRAS_NIF = { 'T', 'R', 'W', 'A', 'G', 'M',
            'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
            'L', 'C', 'K', 'E' };
    public String generaNif(int numero) {
        
        String fullDNI = null;
        Formatter fmt = new Formatter();
        fmt.format("%06d", numero);
        fullDNI = fmt.toString() + LETRAS_NIF[numero % 23];
        return fullDNI;
    }

}

