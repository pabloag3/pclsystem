/*
 */
package com.lawyersys.pclsystembe.rest.expedientes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.TiposActuaciones;
import com.lawyersys.pclsystembe.abm.ABMManagerExpedientes;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;
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

/**
 *
 * @author Pablo Aguilar
 */
@Stateless
@Path("tiposactuaciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TiposActuacionesFacadeREST {


    public TiposActuacionesFacadeREST() {
    }

    @EJB
    private ABMManagerExpedientes abmManager;

    @POST
    @Path("guardar-tipo-actuacion")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        ObjectMapper mapper = new ObjectMapper();
        TiposActuaciones elem = mapper.readValue(entity, TiposActuaciones.class);   
        if ( elem.getDescripcion()== null ) {
            throw new FaltaCargarElemento("Error. Cargar descripcion.");
        }
        abmManager.create(TiposActuaciones.class, elem);
        return Response.ok().build();
    }

    @PUT
    @Path("actualizar-tipo-actuacion/{id}")
    public Response edit(@RequestBody() String entity) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TiposActuaciones elem = mapper.readValue(entity, TiposActuaciones.class);  
        abmManager.edit(TiposActuaciones.class, elem);
        return Response.ok().build();
    }

    @GET
    @Path("traer-tipo-actuacion/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        TiposActuaciones entity = null;
        entity = (TiposActuaciones) abmManager.find("TiposActuaciones", id);
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(entity);
        return Response.ok(resp).build();
    }

    @GET
    @Path("listar-tipos-actuaciones")
    public Response findAll() throws JsonProcessingException {
        List<TiposActuaciones> elem = (List<TiposActuaciones>) (Object) abmManager.findAll("TiposActuaciones");
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(elem);
        return Response.ok(resp).build();
    }
    
    
}
