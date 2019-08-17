/*
 */
package com.lawyersys.pclsystembe.rest.usuarios;

import com.lawyersys.pclsystembe.abm.ABMManagerUsuarios;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Permisos;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
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
import com.lawyersys.pclsystembacke.entities.RolesUsuario;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;

/**
 *
 * @author tatoa
 */
@Stateless
@Path("rolesusuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RolesUsuarioREST {

    public RolesUsuarioREST() {
    }
    
    @EJB
    private ABMManagerUsuarios abmManager;

    @POST
    @Path("guardar-rol")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        ObjectMapper mapper = new ObjectMapper();
        RolesUsuario elem = mapper.readValue(entity, RolesUsuario.class);   
        if ( elem.getDescripcion()== null ) {
            throw new FaltaCargarElemento("Error. Cargar descripcion.");
        }
        abmManager.create(RolesUsuario.class, elem);
        return Response.ok().build();
    }

    @PUT
    @Path("actualizar-rol/{id}")
    public Response edit(@RequestBody() String entity) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RolesUsuario elem = mapper.readValue(entity, RolesUsuario.class);  
        abmManager.edit(RolesUsuario.class, elem);
        return Response.ok().build();
    }

//    @DELETE
//    @Path("eliminar-rol/{id}")
//    public void remove(@PathParam("id") Integer id) {
//        abmManager.remove(RolesUsuario.class, id);
//    }

    @GET
    @Path("traer-rol/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        RolesUsuario entity = null;
        entity = (RolesUsuario) abmManager.find("RolesUsuario", id);
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(entity);
        return Response.ok(resp).build();
    }

    @GET
    @Path("listar-roles")
    public Response findAll() throws JsonProcessingException {
        List<RolesUsuario> elem = (List<RolesUsuario>) (Object) abmManager.findAll("RolesUsuario");
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(elem);
        return Response.ok(resp).build();
    }
    
    @GET
    @Path("listar-permisos")
    public Response traerPermisos() throws JsonProcessingException {
        List<Permisos> elem = (List<Permisos>) (Object) abmManager.findAll("Permisos");
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(elem);
        return Response.ok(resp).build();
    }
    
}
