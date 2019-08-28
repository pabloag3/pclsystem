/*
 */
package com.lawyersys.pclsystembe.utilidades;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Response;

/**
 *
 * @author Pablo Aguilar
 */
public class ErrorManager {

    public static Response tratarError(Exception e) {
        if (e instanceof SQLException) {
            return Response.status(Response.Status.BAD_REQUEST).header("Content-Type: text/html; charset=utf-8", "*").build();
        } else if (e instanceof NullPointerException) {
            return Response.status(Response.Status.CONFLICT).header("Content-Type: text/html; charset=utf-8", "*").build();
        }
        
        return null;
    }

}
