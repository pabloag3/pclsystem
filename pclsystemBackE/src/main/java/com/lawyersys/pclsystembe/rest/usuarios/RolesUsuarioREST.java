package com.lawyersys.pclsystembe.rest.usuarios;

import com.lawyersys.pclsystembe.abm.ABMManagerUsuarios;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Permisos;
import com.lawyersys.pclsystembacke.entities.RolesPermisos;
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
import com.lawyersys.pclsystembacke.entities.RolesUsuario;
import com.lawyersys.pclsystembe.dtos.RolesPermisosDTO;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;
import com.lawyersys.pclsystembe.utilidades.ErrorManager;

/**
 *
 * @author tatoa
 */
@Stateless
@Path("rolesusuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RolesUsuarioREST {

    public RolesUsuarioREST() {
    }
    
    @EJB
    private ABMManagerUsuarios abmManager;

    
    @POST
    @Path("guardar")
    public Response guardarRolesPermisos(@RequestBody() RolesPermisosDTO entity) {
        try {
            abmManager.create(RolesPermisosDTO.class, entity);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, RolesUsuario.class);
        }
    }

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() RolesPermisosDTO entity) throws IOException, FaltaCargarElemento {
        try {
            abmManager.edit(RolesPermisosDTO.class, entity);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, RolesUsuario.class);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<RolesUsuario> elem = (List<RolesUsuario>) (Object) abmManager.find("RolesUsuario", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, RolesUsuario.class);
        }
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<RolesUsuario> elem = (List<RolesUsuario>) (Object) abmManager.findAll("RolesUsuario");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, RolesUsuario.class);
        }
    }
    
    @GET
    @Path("listar-permisos")
    public Response traerPermisos() throws JsonProcessingException {
        try {
            List<Permisos> elem = (List<Permisos>) (Object) abmManager.findAll("Permisos");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, RolesUsuario.class);
        }
    }
    
    @GET
    @Path("listar-permisos-por-rol/{id}")
    public Response traerPermisosPorRol(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Permisos> elem = (List<Permisos>) (Object) abmManager.findPermisosByRol(Integer.parseInt(id));
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, RolesUsuario.class);
        }
    }   
    
}
