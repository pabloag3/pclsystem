package com.lawyersyspclsystembe.rest.manuales;

import java.io.File;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author tatoa
 */
@Stateless
@Path("manuales")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ManualesFacadeREST {

    public ManualesFacadeREST() {
    }
    
    private static String file = "C:\\pclSystemFiles\\manualesDeUsuario\\manualGeneral.pdf";

    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFileWithGet() {
        
        try {
            System.out.println(file);
            File fileDownload = new File(file);
            Response.ResponseBuilder response = Response.ok((Object) fileDownload);
            response.header("Content-Disposition", "attachment;filename=" + file);
            return response.build();
        } catch (NullPointerException e) {
            return Response.noContent().build();
        }

    }
    
}
