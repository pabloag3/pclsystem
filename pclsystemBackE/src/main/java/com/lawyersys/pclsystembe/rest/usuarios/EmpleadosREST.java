/*
 */
package com.lawyersys.pclsystembe.rest.usuarios;

import com.lawyersys.pclsystembe.abm.ABMManagerUsuarios;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.lawyersys.pclsystembacke.entities.Empleados;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;
import com.lawyersys.pclsystembe.utilidades.ErrorManager;
import com.lawyersys.pclsystembe.utilidades.Ruc;

/**
 *
 * @author tatoa
 */
@Stateless
@Path("empleados")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmpleadosREST {

    @EJB
    private ABMManagerUsuarios abmManager;
    
    public EmpleadosREST() {
    }

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Empleados elem = mapper.readValue(entity, Empleados.class);   
            if ( elem.getCedula() == null ) {
                throw new FaltaCargarElemento("Error. Cargar cedula.");
            }
            if ( elem.getNombre() == null ) {
                throw new FaltaCargarElemento("Error. Cargar nombre.");
            }
            if ( elem.getApellido() == null ) {
                throw new FaltaCargarElemento("Error. Cargar apellido.");
            }
            if ( elem.getRuc() == null ) {
                // Genera el ruc con el modulo 11
                elem.setRuc(Ruc.Pa_Calcular_Dv_11_A(elem.getCedula()));
            
            }
            if ( elem.getFechaNacimiento()== null ) {
                throw new FaltaCargarElemento("Error. Cargar fecha de nacimiento.");
            }
            if ( elem.getTelefono()== null ) {
                throw new FaltaCargarElemento("Error. Cargar telefono.");
            }
            if ( elem.getDireccion()== null ) {
                throw new FaltaCargarElemento("Error. Cargar direccion.");
            }
            abmManager.create(Empleados.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Empleados.class);
        }
        
    }

    @PUT
    @Path("actualizar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Empleados elem = mapper.readValue(entity, Empleados.class);  
            if ( elem.getCedula() == null ) {
                throw new FaltaCargarElemento("Error. Cargar cedula.");
            }
            if ( elem.getNombre() == null ) {
                throw new FaltaCargarElemento("Error. Cargar nombre.");
            }
            if ( elem.getApellido() == null ) {
                throw new FaltaCargarElemento("Error. Cargar apellido.");
            }
            if ( elem.getRuc() == null ) {
                throw new FaltaCargarElemento("Error. Cargar ruc.");
            }
            if ( elem.getFechaNacimiento()== null ) {
                throw new FaltaCargarElemento("Error. Cargar fecha de nacimiento.");
            }
            if ( elem.getTelefono()== null ) {
                throw new FaltaCargarElemento("Error. Cargar telefono.");
            }
            if ( elem.getDireccion()== null ) {
                throw new FaltaCargarElemento("Error. Cargar direccion.");
            }
            abmManager.edit(Empleados.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Empleados.class);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Empleados> elem = (List<Empleados>) (Object) abmManager.find("Empleados", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            System.out.println(resp);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Empleados.class);
        }
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<Empleados> elem = (List<Empleados>) (Object) abmManager.findAll("Empleados");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Empleados.class);
        }
    }
    
}
