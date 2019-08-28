/*
 */
package com.lawyersys.pclsystembe.rest.usuarios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembe.abm.ABMManagerUsuarios;
import com.lawyersys.pclsystembacke.entities.Usuarios;
import com.lawyersys.pclsystembe.utilidades.ErrorManager;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;
import com.lawyersys.pclsystembe.utilidades.Seguridad;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuariosREST {
    
    @EJB
    private ABMManagerUsuarios abmManager;

    public UsuariosREST() {
    }
    
    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento{
        try {
            ObjectMapper mapper = new ObjectMapper();
            Usuarios elem = mapper.readValue(entity, Usuarios.class);   
            if ( elem.getCedula() == null ) {
                throw new FaltaCargarElemento("Error. Cargar cedula.");
            }
            if ( elem.getUsuario() == null ) {
                throw new FaltaCargarElemento("Error. Cargar usuario.");
            }
            if ( elem.getContrasenha() == null ) {
                throw new FaltaCargarElemento("Error. Cargar contraseña.");
            }
            if ( elem.getCorreoElectronico() == null ) {
                throw new FaltaCargarElemento("Error. Cargar correo electrónico.");
            }
            elem.setContrasenha(Seguridad.getMd5(elem.getContrasenha()));
            abmManager.create(Usuarios.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.tratarError(e);
        }
    }

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        ObjectMapper mapper = new ObjectMapper();
        Usuarios elem = mapper.readValue(entity, Usuarios.class);   
        if ( elem.getCedula() == null ) {
            throw new FaltaCargarElemento("Error. Cargar cedula.");
        }
        if ( elem.getUsuario() == null ) {
            throw new FaltaCargarElemento("Error. Cargar usuario.");
        }
        if ( elem.getContrasenha() == null ) {
            throw new FaltaCargarElemento("Error. Cargar contraseña.");
        }
        if ( elem.getCorreoElectronico() == null ) {
            throw new FaltaCargarElemento("Error. Cargar correo electrónico.");
        }
        elem.setContrasenha(Seguridad.getMd5(elem.getContrasenha()));
        abmManager.edit(Usuarios.class, elem);
        return Response.ok().build();
    }

    @GET
    @Path("traer/{id}")
    public Response traer(@PathParam("id") String id) throws JsonProcessingException {
        List<Usuarios> elem = (List<Usuarios>) (Object) abmManager.find("Usuarios", id);
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(elem);
        return Response.ok(resp).build();
    }

    @GET
    @Path("listar")
    public Response traerTodos() throws JsonProcessingException {
        List<Usuarios> elem = (List<Usuarios>) (Object) abmManager.findAll("Usuarios");
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(elem);
        return Response.ok(resp).build();
    }

}
