/*
 */
package com.lawyersys.pclsystembe.error;

/**
 *
 * @author tatoa
 */
public class FaltaCargarElemento extends Exception {

    /**
     * Creates a new instance of <code>FaltaCargarElemento</code> without detail
     * message.
     */
    public FaltaCargarElemento() {
    }

    /**
     * Constructs an instance of <code>FaltaCargarElemento</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FaltaCargarElemento(String msg) {
        super(msg);
    }
}
