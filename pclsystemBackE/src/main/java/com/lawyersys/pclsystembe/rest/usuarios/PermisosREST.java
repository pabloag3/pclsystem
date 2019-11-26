/*
 */
package com.lawyersys.pclsystembe.rest.usuarios;

import com.lawyersys.pclsystembe.abm.ABMManagerUsuarios;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tatoa
 */
@Stateless
@Path("permisos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PermisosREST {

    public PermisosREST() {
    }

    private ABMManagerUsuarios abmManager;
    
//    @GET
//    @Path("traer-permiso/{id}")
//    public Response traerPermiso(@PathParam("id") String id) throws JsonProcessingException {
//        Permisos entity = null;
//        entity = (Permisos) abmManager.find("Permisos", id);
//        ObjectMapper mapper = new ObjectMapper();
//        String resp = mapper.writeValueAsString(entity);
//        return Response.ok(resp).build();
//    }
//
//    @GET
//    @Path("listar-permisos")
//    public Response traerPermisos() throws JsonProcessingException {
//        List<Permisos> elem = (List<Permisos>) (Object) abmManager.findAll("Permisos");
//        ObjectMapper mapper = new ObjectMapper();
//        String resp = mapper.writeValueAsString(elem);
//        return Response.ok(resp).build();
//    }
    
}
