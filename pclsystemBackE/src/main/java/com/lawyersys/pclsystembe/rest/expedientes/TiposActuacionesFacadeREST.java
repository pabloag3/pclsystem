/*
 */
package com.lawyersys.pclsystembe.rest.expedientes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.TiposActuaciones;
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
@Path("tiposactuaciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TiposActuacionesFacadeREST {


    public TiposActuacionesFacadeREST() {
    }

    @EJB
    private ABMManagerExpedientes abmManager;

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TiposActuaciones elem = mapper.readValue(entity, TiposActuaciones.class);   
            if ( elem.getDescripcion()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            abmManager.create(TiposActuaciones.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, TiposActuaciones.class);
        }
    }

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TiposActuaciones elem = mapper.readValue(entity, TiposActuaciones.class);
            if ( elem.getDescripcion()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            abmManager.edit(TiposActuaciones.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, TiposActuaciones.class);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<TiposActuaciones> elem = (List<TiposActuaciones>) (Object) abmManager.find("TiposActuaciones", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, TiposActuaciones.class);
        }
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<TiposActuaciones> elem = (List<TiposActuaciones>) (Object) abmManager.findAll("TiposActuaciones");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, TiposActuaciones.class);
        }
    }
    
}
