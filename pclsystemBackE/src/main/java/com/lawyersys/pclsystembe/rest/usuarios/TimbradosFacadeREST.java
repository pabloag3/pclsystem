package com.lawyersys.pclsystembe.rest.usuarios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Timbrados;
import com.lawyersys.pclsystembe.abm.ABMManagerUsuarios;
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
@Path("timbrados")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TimbradosFacadeREST {

    @EJB
    private ABMManagerUsuarios abmManager;
    
    public TimbradosFacadeREST() {
    }

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Timbrados elem = mapper.readValue(entity, Timbrados.class);
            if ( elem.getNroTimbrado()== 0 ) {
                throw new FaltaCargarElemento("Error. Cargar numero de timbrado.");
            }
            if ( elem.getCedula() == null ) {
                throw new FaltaCargarElemento("Error. Cargar cedula.");
            }
            if ( elem.getFechaInicio() == null ) {
                throw new FaltaCargarElemento("Error. Cargar fecha de inicio del timbrado.");
            }
            if ( elem.getFechaFin() == null ) {
                throw new FaltaCargarElemento("Error. Cargar fecha de fin del timbrado.");
            }
            if ( elem.getNroEstablecimiento() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar numero de establecimiento.");
            }
            if ( elem.getNroPuntoExpedicion() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar numero de punto de expedicion.");
            }
            if ( elem.getNroSecInicio() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar numero de inicio de secuencia.");
            }
            if ( elem.getNroSecFinal() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar numero final de secuencia.");
            }
            abmManager.create(Timbrados.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Timbrados.class);
        }
        
    }

    @PUT
    @Path("actualizar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Timbrados elem = mapper.readValue(entity, Timbrados.class);  
            if ( elem.getNroTimbrado()== 0 ) {
                throw new FaltaCargarElemento("Error. Cargar numero de timbrado.");
            }
            if ( elem.getCedula() == null ) {
                throw new FaltaCargarElemento("Error. Cargar cedula.");
            }
            if ( elem.getFechaInicio() == null ) {
                throw new FaltaCargarElemento("Error. Cargar fecha de inicio del timbrado.");
            }
            if ( elem.getFechaFin() == null ) {
                throw new FaltaCargarElemento("Error. Cargar fecha de fin del timbrado.");
            }
            if ( elem.getNroEstablecimiento() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar numero de establecimiento.");
            }
            if ( elem.getNroPuntoExpedicion() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar numero de punto de expedicion.");
            }
            if ( elem.getNroSecInicio() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar numero de inicio de secuencia.");
            }
            if ( elem.getNroSecFinal() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar numero final de secuencia.");
            }
            abmManager.edit(Timbrados.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Timbrados.class);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Timbrados> elem = (List<Timbrados>) (Object) abmManager.find("Timbrados", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            System.out.println(resp);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Timbrados.class);
        }
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<Timbrados> elem = (List<Timbrados>) (Object) abmManager.findAll("Timbrados");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Timbrados.class);
        }
    }
    
}
