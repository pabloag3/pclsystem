/*
 */
package com.lawyersys.pclsystembe.rest.despachos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Ciudades;
import com.lawyersys.pclsystembe.abm.ABMManagerDespachos;
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
@Path("ciudades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CiudadesFacadeREST {

    public CiudadesFacadeREST() {
    }

    @EJB
    private ABMManagerDespachos abmManager;

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Ciudades elem = mapper.readValue(entity, Ciudades.class);   
            if ( elem.getNombre()== null ) {
                throw new FaltaCargarElemento("Error. Cargar nombre de ciudad.");
            }
            abmManager.create(Ciudades.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.tratarError(e);
        }
    }

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Ciudades elem = mapper.readValue(entity, Ciudades.class);
            if ( elem.getNombre()== null ) {
                throw new FaltaCargarElemento("Error. Cargar nombre de ciudad.");
            }
            abmManager.edit(Ciudades.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.tratarError(e);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Ciudades> elem = (List<Ciudades>) (Object) abmManager.find("Ciudades", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.tratarError(e);
        }
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<Ciudades> elem = (List<Ciudades>) (Object) abmManager.findAll("Ciudades");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.tratarError(e);
        }
    }
    
}
