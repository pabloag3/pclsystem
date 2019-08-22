/*
 */
package com.lawyersys.pclsystembe.rest.usuarios;

import com.lawyersys.pclsystembe.abm.ABMManagerUsuarios;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;
import com.lawyersys.pclsystembacke.entities.Permisos;

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
