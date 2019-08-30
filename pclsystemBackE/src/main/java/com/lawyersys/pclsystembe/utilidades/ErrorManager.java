/*
 */
package com.lawyersys.pclsystembe.utilidades;

import java.io.UncheckedIOException;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.spi.UnhandledException;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Pablo Aguilar
 */
public class ErrorManager {

    public static Response tratarError(Exception e) {
        if (e instanceof PSQLException) {
            return Response.status(Response.Status.BAD_REQUEST).header("Content-Type: text/html; charset=utf-8", "*").build();
        } else if (e instanceof UncheckedIOException) {
            return Response.status(Response.Status.BAD_REQUEST).header("Content-Type: text/html; charset=utf-8", "*").build();
        } else if (e instanceof UnhandledException) {
            return Response.status(Response.Status.BAD_REQUEST).header("Content-Type: text/html; charset=utf-8", "*").build();
        }
        
        return null;
    }

}
