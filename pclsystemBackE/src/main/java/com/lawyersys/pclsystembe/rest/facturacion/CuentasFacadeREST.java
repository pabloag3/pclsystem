package com.lawyersys.pclsystembe.rest.facturacion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Cuentas;
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
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author tatoa
 */
@Stateless
@Path("cuentas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CuentasFacadeREST {

    public CuentasFacadeREST() {
    }

    @EJB
    private ABMManagerFacturacion abmManager;

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Cuentas elem = mapper.readValue(entity, Cuentas.class);   
            if ( elem.getCodCliente().getCodCliente() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar cliente.");
            }
            if ( elem.getCodCaso().getCodCaso() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar caso.");
            }
            abmManager.create(Cuentas.class, elem);
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
            Cuentas elem = mapper.readValue(entity, Cuentas.class);
            if ( elem.getCodCliente().getCodCliente() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar cliente.");
            }
            if ( elem.getCodCaso().getCodCaso() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar caso.");
            }
            abmManager.edit(Cuentas.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.tratarError(e);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Cuentas> elem = (List<Cuentas>) (Object) abmManager.find("Cuentas", id);
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
            List<Cuentas> elem = (List<Cuentas>) (Object) abmManager.findAll("Cuentas");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.tratarError(e);
        }
    }
    
}
