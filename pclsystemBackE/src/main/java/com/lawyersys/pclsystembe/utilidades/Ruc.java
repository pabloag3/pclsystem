/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyersys.pclsystembe.utilidades;

/**
 *
 * @author carlo
 */
public class Ruc {
    static int p_basemax = 11;
        
    
    static public String Pa_Calcular_Dv_11_A(String p_numero) {
    int v_total, v_resto, k, v_numero_aux, v_digit;
    String v_numero_al = "";

    for (int i = 0; i < p_numero.length(); i++) {
            char c = p_numero.charAt(i);
            if(Character.isDigit(c)) {
                    v_numero_al += c;
            } else {
                    v_numero_al += (int) c;
            }
    }

    k = 2;
    v_total = 0;

    for(int i = v_numero_al.length() - 1; i >= 0; i--) {
            k = k > p_basemax ? 2 : k;
            v_numero_aux = v_numero_al.charAt(i) - 48;
            v_total += v_numero_aux * k++;
    }

    v_resto = v_total % 11;
    v_digit = v_resto > 1 ? 11 - v_resto : 0;

    return p_numero + "-" + v_digit;
    }
}
