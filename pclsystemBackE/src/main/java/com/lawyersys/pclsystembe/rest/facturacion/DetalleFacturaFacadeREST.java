/*
 */
package com.lawyersys.pclsystembe.rest.facturacion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.DetalleFactura;
import com.lawyersys.pclsystembacke.entities.DetalleFacturaPK;
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
 * @author Pablo Aguilar
 */
@Stateless
@Path("detallefactura")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DetalleFacturaFacadeREST {

    private DetalleFacturaPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;codDetalleFactura=codDetalleFacturaValue;codFactura=codFacturaValue;nroFactura=nroFacturaValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.lawyersys.pclsystembacke.entities.DetalleFacturaPK key = new com.lawyersys.pclsystembacke.entities.DetalleFacturaPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> codDetalleFactura = map.get("codDetalleFactura");
        if (codDetalleFactura != null && !codDetalleFactura.isEmpty()) {
            key.setCodDetalleFactura(new java.lang.Integer(codDetalleFactura.get(0)));
        }
        java.util.List<String> codFactura = map.get("codFactura");
        if (codFactura != null && !codFactura.isEmpty()) {
            key.setCodFactura(new java.lang.Integer(codFactura.get(0)));
        }
        java.util.List<String> nroFactura = map.get("nroFactura");
        if (nroFactura != null && !nroFactura.isEmpty()) {
            key.setNroFactura(nroFactura.get(0));
        }
        return key;
    }

    public DetalleFacturaFacadeREST() {
    }

    @EJB
    private ABMManagerFacturacion abmManager;

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DetalleFactura elem = mapper.readValue(entity, DetalleFactura.class);
            if ( elem.getDescripcion() == null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            if ( elem.getMonto() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar monto.");
            }
            if ( elem.getPorcentajeIva() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar porcentaje de IVA.");
            }
            abmManager.create(DetalleFactura.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DetalleFactura.class);
        }
    }

//    @PUT
//    @Path("actualizar/{id}")
//    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            DetalleFactura elem = mapper.readValue(entity, DetalleFactura.class);
//            if ( elem.getDescripcion() == null ) {
//                throw new FaltaCargarElemento("Error. Cargar descripcion.");
//            }
//            if ( elem.getMonto() == 0 ) {
//                throw new FaltaCargarElemento("Error. Cargar monto.");
//            }
//            if ( elem.getPorcentajeIva() == 0 ) {
//                throw new FaltaCargarElemento("Error. Cargar porcentaje de IVA.");
//            }
//            abmManager.edit(DetalleFactura.class, elem);
//            return Response.ok().build();
//        } catch (Exception e) {
//            return ErrorManager.manejarError(e, DetalleFactura.class);
//        }
//    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<DetalleFactura> elem = (List<DetalleFactura>) (Object) abmManager.find("DetalleFactura", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DetalleFactura.class);
        }
    }
    
    @GET
    @Path("traer-detalles-de-factura/{id}")
    public Response findByFactura(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<DetalleFactura> elem = (List<DetalleFactura>) (Object) abmManager.traerDetallesDeFactura(id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DetalleFactura.class);
        }
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<DetalleFactura> elem = (List<DetalleFactura>) (Object) abmManager.findAll("DetalleFactura");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DetalleFactura.class);
        }
    }
    
}
