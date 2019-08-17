/*
 */
package com.lawyersys.pclsystembe.error;

/**
 *
 * @author tatoa
 */
public class UsuarioConRolExistente extends Exception {

    /**
     * Creates a new instance of <code>UsuarioConRolExistente</code> without
     * detail message.
     */
    public UsuarioConRolExistente() {
    }

    /**
     * Constructs an instance of <code>UsuarioConRolExistente</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UsuarioConRolExistente(String msg) {
        super(msg);
    }
}
