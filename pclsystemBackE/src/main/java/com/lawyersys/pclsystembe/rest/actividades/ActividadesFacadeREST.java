/*
 */
package com.lawyersys.pclsystembe.rest.actividades;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Actividades;
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
        try {
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
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Actividades.class);
        }
    }

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
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
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Actividades.class);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Actividades> elem = (List<Actividades>) (Object) abmManager.find("Actividades", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Actividades.class);
        }
    }
    
    @GET
    @Path("traer-pendientes-caducado-del-empleado/{id}")
    public Response traerPendienteCaducadoDelEmpleado (@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Actividades> elem = (List<Actividades>) (Object) abmManager.traerPendienteCaducadoDelEmpleado(id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Actividades.class);
        }
    }
    
    @GET
    @Path("traer-pendientes-caducados")
    public Response traerPendientesCaducados () throws JsonProcessingException {
        try {
            List<Actividades> elem = (List<Actividades>) (Object) abmManager.traerPendientesCaducados();
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Actividades.class);
        }
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<Actividades> elem = (List<Actividades>) (Object) abmManager.findAll("Actividades");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Actividades.class);
        }
    }
    
}
