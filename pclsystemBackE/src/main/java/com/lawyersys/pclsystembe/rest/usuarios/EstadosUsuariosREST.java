/*
 */
package com.lawyersys.pclsystembe.rest.usuarios;

import com.lawyersys.pclsystembe.abm.ABMManagerUsuarios;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
import com.lawyersys.pclsystembacke.entities.EstadosUsuarios;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;

/**
 *
 * @author tatoa
 */
@Stateless
@Path("estadosusuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadosUsuariosREST{
    
    @EJB
    private ABMManagerUsuarios abmManager;

    public EstadosUsuariosREST() {
    }

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        ObjectMapper mapper = new ObjectMapper();
        EstadosUsuarios elem = mapper.readValue(entity, EstadosUsuarios.class);   
        if ( elem.getDescripcion()== null ) {
            throw new FaltaCargarElemento("Error. Cargar descripcion.");
        }
        abmManager.create(EstadosUsuarios.class, elem);
        return Response.ok().build();
    }

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        ObjectMapper mapper = new ObjectMapper();
        EstadosUsuarios elem = mapper.readValue(entity, EstadosUsuarios.class);
        if ( elem.getDescripcion()== null ) {
            throw new FaltaCargarElemento("Error. Cargar descripcion.");
        }
        abmManager.edit(EstadosUsuarios.class, elem);
        return Response.ok().build();
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        EstadosUsuarios entity = null;
        entity = (EstadosUsuarios) abmManager.find("EstadosUsuarios", id);
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(entity);
        return Response.ok(resp).build();
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        List<EstadosUsuarios> elem;
        elem = (List<EstadosUsuarios>) (Object) abmManager.findAll("EstadosUsuarios");
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(elem);
        return Response.ok(resp).build();
    }
    
}
