/*
 */
package com.lawyersys.pclsystembe.rest.expedientes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.EstadosCaso;
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
@Path("estadoscaso")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadosCasoFacadeREST {

    public EstadosCasoFacadeREST() {
    }
    
    @EJB
    private ABMManagerExpedientes abmManager;

    @POST
    @Path("guardar-estado-caso")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        ObjectMapper mapper = new ObjectMapper();
        EstadosCaso elem = mapper.readValue(entity, EstadosCaso.class);   
        if ( elem.getDescripcion()== null ) {
            throw new FaltaCargarElemento("Error. Cargar descripcion.");
        }
        abmManager.create(EstadosCaso.class, elem);
        return Response.ok().build();
    }

    @PUT
    @Path("actualizar-estado-caso/{id}")
    public Response edit(@RequestBody() String entity) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        EstadosCaso elem = mapper.readValue(entity, EstadosCaso.class);  
        abmManager.edit(EstadosCaso.class, elem);
        return Response.ok().build();
    }

    @GET
    @Path("traer-estado-caso/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        EstadosCaso entity = null;
        entity = (EstadosCaso) abmManager.find("EstadosCaso", id);
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(entity);
        return Response.ok(resp).build();
    }

    @GET
    @Path("listar-estados-casos")
    public Response findAll() throws JsonProcessingException {
        List<EstadosCaso> elem = (List<EstadosCaso>) (Object) abmManager.findAll("EstadosCaso");
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(elem);
        return Response.ok(resp).build();
    }
    
    
}
