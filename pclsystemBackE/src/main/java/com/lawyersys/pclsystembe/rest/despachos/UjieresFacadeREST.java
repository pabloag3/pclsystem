/*
 */
package com.lawyersys.pclsystembe.rest.despachos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Ujieres;
import com.lawyersys.pclsystembe.abm.ABMManagerDespachos;
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
@Path("ujieres")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UjieresFacadeREST {

    public UjieresFacadeREST() {
    }
    
    @EJB
    private ABMManagerDespachos abmManager;

    @POST
    @Path("guardar-ujier")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        ObjectMapper mapper = new ObjectMapper();
        Ujieres elem = mapper.readValue(entity, Ujieres.class);   
        if ( elem.getNombre()== null ) {
            throw new FaltaCargarElemento("Error. Cargar descripcion.");
        }
        abmManager.create(Ujieres.class, elem);
        return Response.ok().build();
    }

    @PUT
    @Path("actualizar-ujier/{id}")
    public Response edit(@RequestBody() String entity) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Ujieres elem = mapper.readValue(entity, Ujieres.class);  
        abmManager.edit(Ujieres.class, elem);
        return Response.ok().build();
    }

    @GET
    @Path("traer-ujier/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        Ujieres entity = null;
        entity = (Ujieres) abmManager.find("Ujieres", id);
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(entity);
        return Response.ok(resp).build();
    }

    @GET
    @Path("listar-ujier")
    public Response findAll() throws JsonProcessingException {
        List<Ujieres> elem = (List<Ujieres>) (Object) abmManager.findAll("Ujieres");
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(elem);
        return Response.ok(resp).build();
    }
    
}
