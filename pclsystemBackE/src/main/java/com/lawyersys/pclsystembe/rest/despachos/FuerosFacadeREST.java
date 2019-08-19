/*
 */
package com.lawyersys.pclsystembe.rest.despachos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Fueros;
import com.lawyersys.pclsystembe.abm.ABMManagerDespachos;
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
 * @author Pablo Aguilar
 */
@Stateless
@Path("fueros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuerosFacadeREST {

    public FuerosFacadeREST() {
    }

    @EJB
    private ABMManagerDespachos abmManager;

    @POST
    @Path("guardar-fuero")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        ObjectMapper mapper = new ObjectMapper();
        Fueros elem = mapper.readValue(entity, Fueros.class);   
        if ( elem.getTipoFuero()== null ) {
            throw new FaltaCargarElemento("Error. Cargar descripcion.");
        }
        abmManager.create(Fueros.class, elem);
        return Response.ok().build();
    }

    @PUT
    @Path("actualizar-fuero/{id}")
    public Response edit(@RequestBody() String entity) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Fueros elem = mapper.readValue(entity, Fueros.class);  
        abmManager.edit(Fueros.class, elem);
        return Response.ok().build();
    }

    @GET
    @Path("traer-fuero/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        Fueros entity = null;
        entity = (Fueros) abmManager.find("Fueros", id);
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(entity);
        return Response.ok(resp).build();
    }

    @GET
    @Path("listar-fueros")
    public Response findAll() throws JsonProcessingException {
        List<Fueros> elem = (List<Fueros>) (Object) abmManager.findAll("Fueros");
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(elem);
        return Response.ok(resp).build();
    }
    
}
