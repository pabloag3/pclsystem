/*
 */
package com.lawyersys.pclsystembe.rest.actividades;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Actividades;
import com.lawyersys.pclsystembe.abm.ABMManagerActividades;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;
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

/**
 *
 * @author tatoa
 */
@Stateless
@Path("actividades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ActividadesFacadeREST {

    public ActividadesFacadeREST() {
    }
    
    @EJB
    private ABMManagerActividades abmManager;

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        ObjectMapper mapper = new ObjectMapper();
        Actividades elem = mapper.readValue(entity, Actividades.class);   
        if ( elem.getDescripcion()== null ) {
            throw new FaltaCargarElemento("Error. Cargar descripcion.");
        }
        if ( elem.getFecha()== null ) {
            throw new FaltaCargarElemento("Error. Cargar fecha.");
        }
        abmManager.create(Actividades.class, elem);
        return Response.ok().build();
    }

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        ObjectMapper mapper = new ObjectMapper();
        Actividades elem = mapper.readValue(entity, Actividades.class);  
        if ( elem.getDescripcion()== null ) {
            throw new FaltaCargarElemento("Error. Cargar descripcion.");
        }
        if ( elem.getFecha()== null ) {
            throw new FaltaCargarElemento("Error. Cargar fecha.");
        }
        abmManager.edit(Actividades.class, elem);
        return Response.ok().build();
    }

//    @DELETE
//    @Path("{id}")
//    public void remove(@PathParam("id") Integer id) {
//        super.remove(super.find(id));
//    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        List<Actividades> elem = (List<Actividades>) (Object) abmManager.find("Actividades", id);
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(elem);
        return Response.ok(resp).build();
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        List<Actividades> elem = (List<Actividades>) (Object) abmManager.findAll("Actividades");
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(elem);
        return Response.ok(resp).build();
    }
    
}