/*
 */
package com.lawyersys.pclsystembe.rest.expedientes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.DetalleExpediente;
import com.lawyersys.pclsystembacke.entities.DetalleExpedientePK;
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
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Pablo Aguilar
 */
@Stateless
@Path("detalleexpediente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DetalleExpedienteFacadeREST {

    private DetalleExpedientePK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;codDetalleExpediente=codDetalleExpedienteValue;codExpediente=codExpedienteValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.lawyersys.pclsystembacke.entities.DetalleExpedientePK key = new com.lawyersys.pclsystembacke.entities.DetalleExpedientePK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> codDetalleExpediente = map.get("codDetalleExpediente");
        if (codDetalleExpediente != null && !codDetalleExpediente.isEmpty()) {
            key.setCodDetalleExpediente(new java.lang.Integer(codDetalleExpediente.get(0)));
        }
        java.util.List<String> codExpediente = map.get("codExpediente");
        if (codExpediente != null && !codExpediente.isEmpty()) {
            key.setCodExpediente(new java.lang.Integer(codExpediente.get(0)));
        }
        return key;
    }

    public DetalleExpedienteFacadeREST() {
    }

    @EJB
    private ABMManagerExpedientes abmManager;

    @POST
    @Path("guardar-detalle-expediente")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        ObjectMapper mapper = new ObjectMapper();
        DetalleExpediente elem = mapper.readValue(entity, DetalleExpediente.class);   
        if ( elem.getDescripcion()== null ) {
            throw new FaltaCargarElemento("Error. Cargar descripcion.");
        }
        abmManager.create(DetalleExpediente.class, elem);
        return Response.ok().build();
    }

    @PUT
    @Path("actualizar-detalle-expediente/{id}")
    public Response edit(@RequestBody() String entity) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DetalleExpediente elem = mapper.readValue(entity, DetalleExpediente.class);  
        abmManager.edit(DetalleExpediente.class, elem);
        return Response.ok().build();
    }

    @GET
    @Path("traer-detalle-expediente/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        DetalleExpediente entity = null;
        entity = (DetalleExpediente) abmManager.find("DetalleExpediente", id);
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(entity);
        return Response.ok(resp).build();
    }

    @GET
    @Path("listar-detalle-expediente")
    public Response findAll() throws JsonProcessingException {
        List<DetalleExpediente> elem = (List<DetalleExpediente>) (Object) abmManager.findAll("DetalleExpediente");
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(elem);
        return Response.ok(resp).build();
    }
    
    
}
