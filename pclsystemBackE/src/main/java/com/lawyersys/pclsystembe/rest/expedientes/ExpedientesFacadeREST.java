/*
 */
package com.lawyersys.pclsystembe.rest.expedientes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Expedientes;
import com.lawyersys.pclsystembe.abm.ABMManagerExpedientes;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;
import com.lawyersys.pclsystembe.utilidades.ErrorManager;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
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
@Path("expedientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExpedientesFacadeREST {

    public ExpedientesFacadeREST() {
    }

    @EJB
    private ABMManagerExpedientes abmManager;

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Expedientes elem = mapper.readValue(entity, Expedientes.class);   
            if ( elem.getDescripcion()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            if ( elem.getCaratula()== null ) {
                throw new FaltaCargarElemento("Error. Cargar caratula.");
            }
            if ( elem.getFecha()== null ) {
                throw new FaltaCargarElemento("Error. Cargar fecha.");
            }
            if ( elem.getNroExpediente() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            abmManager.create(Expedientes.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Expedientes.class);
        }
    }

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() String entity) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Expedientes elem = mapper.readValue(entity, Expedientes.class);  
            abmManager.edit(Expedientes.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Expedientes.class);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Expedientes> elem = (List<Expedientes>) (Object) abmManager.find("Expedientes", id);
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("List: " + elem.toString());
            String resp = mapper.writeValueAsString(elem);
            System.out.println("Despues de mapear");            
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Expedientes.class);
        }
    }
    
    @GET
    @Path("listar-por-caso/{id}")
    public Response findPorCaso(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Expedientes> elem = (List<Expedientes>) (Object) abmManager.traerExpedientesPorCaso(id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Expedientes.class);
        }
    }
    
    @GET
    @Path("listar-por-despachos/{id}")
    public Response findPorDespachos(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Expedientes> elem = (List<Expedientes>) (Object) abmManager.traerExpedientePorDespacho(id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Expedientes.class);
        }
    }
    
    @GET
    @Path("listar-hijos-de-expediente/{id}")
    public Response findHijosDeExpediente(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Expedientes> elem = (List<Expedientes>) (Object) abmManager.findHijosDeExpediente(id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Expedientes.class);
        }
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<Expedientes> elem = (List<Expedientes>) (Object) abmManager.findAll("Expedientes");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Expedientes.class);
        }
    }
    
}
