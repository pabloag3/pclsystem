package com.lawyersys.pclsystembe.rest.facturacion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.DetalleCuenta;
import com.lawyersys.pclsystembacke.entities.DetalleCuentaPK;
import com.lawyersys.pclsystembe.abm.ABMManagerFacturacion;
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
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author tatoa
 */
@Stateless
@Path("detallecuenta")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DetalleCuentaFacadeREST {

    private DetalleCuentaPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;codDetalleCuenta=codDetalleCuentaValue;codCuenta=codCuentaValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.lawyersys.pclsystembacke.entities.DetalleCuentaPK key = new com.lawyersys.pclsystembacke.entities.DetalleCuentaPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> codDetalleCuenta = map.get("codDetalleCuenta");
        if (codDetalleCuenta != null && !codDetalleCuenta.isEmpty()) {
            key.setCodDetalleCuenta(new java.lang.Integer(codDetalleCuenta.get(0)));
        }
        java.util.List<String> codCuenta = map.get("codCuenta");
        if (codCuenta != null && !codCuenta.isEmpty()) {
            key.setCodCuenta(new java.lang.Integer(codCuenta.get(0)));
        }
        return key;
    }

    public DetalleCuentaFacadeREST() {
    }

    @EJB
    private ABMManagerFacturacion abmManager;

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DetalleCuenta elem = mapper.readValue(entity, DetalleCuenta.class);
            if ( elem.getDescripcion() == null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            if ( elem.getMonto() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar monto.");
            }
            abmManager.create(DetalleCuenta.class, elem);
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
            DetalleCuenta elem = mapper.readValue(entity, DetalleCuenta.class);
            if ( elem.getDescripcion() == null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            if ( elem.getMonto() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar monto.");
            }
            abmManager.edit(DetalleCuenta.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.tratarError(e);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<DetalleCuenta> elem = (List<DetalleCuenta>) (Object) abmManager.find("DetalleCuenta", id);
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
            List<DetalleCuenta> elem = (List<DetalleCuenta>) (Object) abmManager.findAll("DetalleCuenta");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.tratarError(e);
        }
    }
    
}
