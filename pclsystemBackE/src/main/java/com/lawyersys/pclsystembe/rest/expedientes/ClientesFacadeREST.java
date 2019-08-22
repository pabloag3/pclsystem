/*
 */
package com.lawyersys.pclsystembe.rest.expedientes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Clientes;
import com.lawyersys.pclsystembe.abm.ABMManagerExpedientes;
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
@Path("clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientesFacadeREST {

    public ClientesFacadeREST() {
    }

    @EJB
    private ABMManagerExpedientes abmManager;

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        ObjectMapper mapper = new ObjectMapper();
        Clientes elem = mapper.readValue(entity, Clientes.class);   
        if ( elem.getNombre()== null ) {
            throw new FaltaCargarElemento("Error. Cargar descripcion.");
        }
        if ( elem.getTipoCliente() == null ) {
            throw new FaltaCargarElemento("Error. Cargar tipo de cliente.");
        }
        abmManager.create(Clientes.class, elem);
        return Response.ok().build();
    }

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        ObjectMapper mapper = new ObjectMapper();
        Clientes elem = mapper.readValue(entity, Clientes.class);
        if ( elem.getNombre()== null ) {
            throw new FaltaCargarElemento("Error. Cargar descripcion.");
        }
        if ( elem.getTipoCliente() == null ) {
            throw new FaltaCargarElemento("Error. Cargar tipo de cliente.");
        }
        abmManager.edit(Clientes.class, elem);
        return Response.ok().build();
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        List<Clientes> elem = (List<Clientes>) (Object) abmManager.find("Clientes", id);
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(elem);
        return Response.ok(resp).build();
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        List<Clientes> elem = (List<Clientes>) (Object) abmManager.findAll("Clientes");
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(elem);
        return Response.ok(resp).build();
    }
    
    
}