/*
 */
package com.lawyersys.pclsystembe.rest.actividades;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.EstadosActividades;
import com.lawyersys.pclsystembe.abm.ABMManagerActividades;
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
 * @author tatoa
 */
@Stateless
@Path("estadosactividades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadosActividadesFacadeREST {

    public EstadosActividadesFacadeREST() {
    }
    
    @EJB
    private ABMManagerActividades abmManager;

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            EstadosActividades elem = mapper.readValue(entity, EstadosActividades.class);   
            if ( elem.getTipoEstado()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            abmManager.create(EstadosActividades.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, EstadosActividades.class);
        }
    }

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            EstadosActividades elem = mapper.readValue(entity, EstadosActividades.class);
            if ( elem.getTipoEstado()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            abmManager.edit(EstadosActividades.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, EstadosActividades.class);
        }
        
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<EstadosActividades> elem = (List<EstadosActividades>) (Object) abmManager.find("EstadosActividades", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, EstadosActividades.class);
        }
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<EstadosActividades> elem = (List<EstadosActividades>) (Object) abmManager.findAll("EstadosActividades");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, EstadosActividades.class);
        }
    }
    
}
